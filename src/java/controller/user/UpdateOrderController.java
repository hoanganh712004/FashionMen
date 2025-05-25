/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.ProductDAO;
import dal.ProductDetailDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.OrderDetail;
import model.Product;
import model.ProductDetail;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "UpdateOrderController", urlPatterns = {"/updateorder"})
public class UpdateOrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderid_raw = request.getParameter("orderId");
        //orderHistory
        String link = request.getParameter("link");
        String status_raw = request.getParameter("status");
        int orderId = Integer.parseInt(orderid_raw);
        int status = Integer.parseInt(status_raw);
        OrderDAO orderDAO = new OrderDAO();

        System.out.println(orderId);
        System.out.println(status);
        // 0 -> Pending
        // 1 -> Completed
        // 2 -> Cancel
        // PAYMENT BY BALANCE
        // PAYMENT ON DELIVERY
        if (status == 2) {

            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
            List<OrderDetail> orderDetailLists = orderDetailDAO.getOrderDetailsById(orderId);

            ProductDetailDAO productDetailDAO = new ProductDetailDAO();

            // Update product detail
            for (OrderDetail orderDetailList : orderDetailLists) {
                List<ProductDetail> productDetailLists1 = productDetailDAO.getAllProductDetails();
                for (ProductDetail productDetailList : productDetailLists1) {
                    if (productDetailList.getProductId() == orderDetailList.getProductId()
                            && productDetailList.getColor().getColorId() == orderDetailList.getColorId()
                            && productDetailList.getSize().getSizeId() == orderDetailList.getSizeId()) {
                        productDetailDAO.updateProductDetail2(productDetailList, orderDetailList.getQuantity()); // new product_detail + odd product_detail of order_detail
                    }
                }
            }

            // Map to save product
            Map<Integer, Integer> productDetailMap = new LinkedHashMap<>();
            List<ProductDetail> productDetailLists = productDetailDAO.getAllProductDetails();
            for (OrderDetail orderDetailList : orderDetailLists) {
                for (ProductDetail productDetailList : productDetailLists) {
                    if (productDetailList.getProductId() == orderDetailList.getProductId()
                            && productDetailList.getColor().getColorId() == orderDetailList.getColorId()
                            && productDetailList.getSize().getSizeId() == orderDetailList.getSizeId()) {

                        // Cập nhật tổng số lượng bị trả lại vào Map -> getOrDefault -> mỗi key khi bắt đầu sẽ + 0
                        productDetailMap.put(orderDetailList.getProductId(),
                                productDetailMap.getOrDefault(orderDetailList.getProductId(), 0) + orderDetailList.getQuantity());
                    }
                }
            }

            // Update product
            ProductDAO productDAO = new ProductDAO();
            for (Map.Entry<Integer, Integer> entry : productDetailMap.entrySet()) {
                int productId = entry.getKey();
                int quantitySold = entry.getValue();

                Product product = productDAO.getProductById(productId);

                if (product != null) {
                    product.setQuantityStock(product.getQuantityStock() + quantitySold);
                    product.setQuantitySold(product.getQuantitySold() - quantitySold);
                    productDAO.updateProductQuantity(product);
                }
            }

            if (link != null) {
                if ((orderDAO.updateStatus(orderId, status))) {
                    response.sendRedirect("orderHistory");
                }
            } else {
                if ((orderDAO.updateStatus(orderId, status))) {
                    response.sendRedirect("home");

                }
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
