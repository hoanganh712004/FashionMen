/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.ProductDAO;
import dal.ProductDetailDAO;
import dal.UserDAO;
import dal.WalletDAO;
import dto.CartItemDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import model.Order;
import model.OrderDetail;
import model.Product;
import model.ProductDetail;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CheckOutController", urlPatterns = {"/checkout"})
public class CheckOutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<CartItemDTO> cartItemList = (List<CartItemDTO>) session.getAttribute("cartItemList");

        if (cartItemList == null) {
            response.sendRedirect("filterproduct");
        } else {
            response.sendRedirect("./views/user/item-page/checkout.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get session
        HttpSession session = request.getSession();
        User userAccount = (User) session.getAttribute("account");
        String wallet_raw = (String) session.getAttribute("balance");
        Object totalAllPriceObj = session.getAttribute("totalAllPrice");
        String totalAllPrice_raw = totalAllPriceObj != null ? totalAllPriceObj.toString() : null;
        List<CartItemDTO> cartItemLists = (List<CartItemDTO>) session.getAttribute("cartItemList");

        // Get request order
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String note = request.getParameter("note");
        int pay = Integer.parseInt(request.getParameter("pay"));
        Date date = new Date();

        // 0 -> Pending
        // 1 -> Completed
        // 2 -> Cancel
        if (pay == 1) {
            float wallet = Float.parseFloat(wallet_raw);
            float totalAllPrice = Float.parseFloat(totalAllPrice_raw);

            if (wallet < totalAllPrice) {
                request.setAttribute("errorPayment", "Số tiền của bạn không đủ");
                request.getRequestDispatcher("./views/user/item-page/checkout.jsp").forward(request, response);
            } else {

                OrderDAO orderDAO = new OrderDAO();
                Order order = new Order(userAccount.getUserId(), name, phone, email, address, date, note, pay, 1, totalAllPrice);

                // Insert Order
                if (orderDAO.insertOrder(order)) {

                    // Inser orderDetail
                    int orderLast = orderDAO.getOrderLast();
                    OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                    for (CartItemDTO cartItemList : cartItemLists) {
                        OrderDetail orderDetail = new OrderDetail(orderLast, cartItemList.getProductId(), cartItemList.getColor().getColorId(), cartItemList.getSize().getSizeId(), cartItemList.getPrice(), cartItemList.getQuantity());
                        orderDetailDAO.insertOrderDetails(orderDetail);

                    }

                    // Get product_detail 
                    ProductDetailDAO productDetailDAO = new ProductDetailDAO();
                    List<ProductDetail> productDetailLists = new ArrayList<>();
                    for (CartItemDTO cartItemList : cartItemLists) {
                        ProductDetail productDetail = new ProductDetail();
                        productDetail = productDetailDAO.getProductDetail(cartItemList.getProductId(), cartItemList.getColor().getColorId(), cartItemList.getSize().getSizeId());
                        productDetailLists.add(productDetail);
                    }

                    // Update product_detail -> quantity
                    for (ProductDetail productDetailList : productDetailLists) {
                        int totalQuantityUpdate = 0;

                        for (CartItemDTO cartItemList : cartItemLists) {
                            if (productDetailList.getProductId() == cartItemList.getProductId()
                                    && productDetailList.getColor().getColorId() == cartItemList.getColor().getColorId()
                                    && productDetailList.getSize().getSizeId() == cartItemList.getSize().getSizeId()) {
                                totalQuantityUpdate += cartItemList.getQuantity();

                            }
                        }

                        if (totalQuantityUpdate > 0) {
                            int quantityUpdate = productDetailList.getQuantity() - totalQuantityUpdate;
                            productDetailDAO.updateProductDetail(productDetailList, quantityUpdate);
                        }
                    }

                    // Sum quantity of item by productId
                    Map<Integer, Integer> productQuantityMap = new TreeMap<>();

                    for (CartItemDTO cartItem : cartItemLists) {
                        int productId = cartItem.getProductId();
                        int quantity = cartItem.getQuantity();

                        if (productQuantityMap.containsKey(productId)) {
                            productQuantityMap.put(productId, productQuantityMap.get(productId) + quantity);
                        } else {
                            productQuantityMap.put(productId, quantity);
                        }
                    }

                    // Get product from database
                    ProductDAO productDAO = new ProductDAO();

                    for (Map.Entry<Integer, Integer> entry : productQuantityMap.entrySet()) {
                        int productId = entry.getKey();
                        int quantitySold = entry.getValue();

                        // Lấy thông tin sản phẩm từ ProductDAO
                        Product product = productDAO.getProductId(productId);

                        if (product != null) {
                            // Giảm quantity_stock và tăng quantity_sold
                            int newQuantityStock = product.getQuantityStock() - quantitySold;
                            int newQuantitySold = product.getQuantitySold() + quantitySold;

                            product.setQuantityStock(newQuantityStock);
                            product.setQuantitySold(newQuantitySold);

                            productDAO.updateProductQuantity(product);
                        }
                    }

                }

                float walletAfter = wallet - totalAllPrice;
                WalletDAO walletDAO = new WalletDAO();
                walletDAO.updateWalletOfUser(userAccount.getUserId(), walletAfter);

                session.removeAttribute("cartItemList");
                response.sendRedirect("home");
            }

        } else if (pay == 2) {
            // 0 -> Pending
            // 1 -> Completed
            // 2 -> Cancel
            float totalAllPrice = Float.parseFloat(totalAllPrice_raw);
            OrderDAO orderDAO = new OrderDAO();
            Order order = new Order(userAccount.getUserId(), name, phone, email, address, date, note, pay, 0, totalAllPrice);

            // Insert Order
            if (orderDAO.insertOrder(order)) {

                // Inser orderDetail
                int orderLast = orderDAO.getOrderLast();
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                for (CartItemDTO cartItemList : cartItemLists) {
                    OrderDetail orderDetail = new OrderDetail(orderLast, cartItemList.getProductId(), cartItemList.getColor().getColorId(), cartItemList.getSize().getSizeId(), cartItemList.getPrice(), cartItemList.getQuantity());
                    orderDetailDAO.insertOrderDetails(orderDetail);

                }

                // Get product_detail 
                ProductDetailDAO productDetailDAO = new ProductDetailDAO();
                List<ProductDetail> productDetailLists = new ArrayList<>();
                for (CartItemDTO cartItemList : cartItemLists) {
                    ProductDetail productDetail = new ProductDetail();
                    productDetail = productDetailDAO.getProductDetail(cartItemList.getProductId(), cartItemList.getColor().getColorId(), cartItemList.getSize().getSizeId());
                    productDetailLists.add(productDetail);
                }

                // Update product_detail -> quantity
                for (ProductDetail productDetailList : productDetailLists) {
                    int totalQuantityUpdate = 0;

                    for (CartItemDTO cartItemList : cartItemLists) {
                        if (productDetailList.getProductId() == cartItemList.getProductId()
                                && productDetailList.getColor().getColorId() == cartItemList.getColor().getColorId()
                                && productDetailList.getSize().getSizeId() == cartItemList.getSize().getSizeId()) {
                            totalQuantityUpdate += cartItemList.getQuantity();

                        }
                    }

                    if (totalQuantityUpdate > 0) {
                        int quantityUpdate = productDetailList.getQuantity() - totalQuantityUpdate;
                        productDetailDAO.updateProductDetail(productDetailList, quantityUpdate);
                    }
                }

                // Sum quantity of item by productId
                Map<Integer, Integer> productQuantityMap = new TreeMap<>();

                for (CartItemDTO cartItem : cartItemLists) {
                    int productId = cartItem.getProductId();
                    int quantity = cartItem.getQuantity();

                    if (productQuantityMap.containsKey(productId)) {
                        productQuantityMap.put(productId, productQuantityMap.get(productId) + quantity);
                    } else {
                        productQuantityMap.put(productId, quantity);
                    }
                }

                // Get product from database
                ProductDAO productDAO = new ProductDAO();

                for (Map.Entry<Integer, Integer> entry : productQuantityMap.entrySet()) {
                    int productId = entry.getKey();
                    int quantitySold = entry.getValue();

                    // Lấy thông tin sản phẩm từ ProductDAO
                    Product product = productDAO.getProductId(productId);

                    if (product != null) {
                        // Giảm quantity_stock và tăng quantity_sold
                        int newQuantityStock = product.getQuantityStock() - quantitySold;
                        int newQuantitySold = product.getQuantitySold() + quantitySold;

                        product.setQuantityStock(newQuantityStock);
                        product.setQuantitySold(newQuantitySold);

                        productDAO.updateProductQuantity(product);
                    }
                }

            }

            session.removeAttribute("cartItemList");
            response.sendRedirect("home");
        }

    }

}
