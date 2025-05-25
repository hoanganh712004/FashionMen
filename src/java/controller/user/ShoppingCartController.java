/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.ColorDAO;
import dal.ProductDAO;
import dal.SizeDAO;
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
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import model.Color;
import model.Product;
import model.Size;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ShoppingCartController", urlPatterns = {"/shoppingcart"})
public class ShoppingCartController extends HttpServlet {
//
//    private List<CartItemDTO> cartItemList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<CartItemDTO> cartItemList = (List<CartItemDTO>) session.getAttribute("cartItemList");
        if (session.getAttribute("colorid") == null || session.getAttribute("sizeid") == null) {
            int productId = (int) session.getAttribute("productid");

            // Case color or size null
            request.setAttribute("errorAddToCart", "Bạn phải chọn color và size");
            request.getRequestDispatcher("productdetail?pid=" + productId).forward(request, response);
        } else {

            int colorId = (int) session.getAttribute("colorid");
            int sizeId = (int) session.getAttribute("sizeid");
            int productId = (int) session.getAttribute("productid");
            int quantityStock = (int) session.getAttribute("quantityStock");
            int quantityUser = Integer.parseInt(request.getParameter("quantity"));

            // quantityUser > 0, quantityUser = 0, quantityUser < 0
            if (quantityUser > 0) {

                // quantityUser <= quantityStock
                if (quantityUser <= quantityStock) {

                    // Creat product to take product image
                    ProductDAO productDAO = new ProductDAO();
                    Product product = new Product();
                    product = productDAO.getProductId(productId);

                    // checkAllItemSame when customer add to cart same item
                    int checkAllItemSame = quantityUser;
                    if (cartItemList == null) {
                        cartItemList = new ArrayList<>();
                    }
                    for (CartItemDTO cartItemDTO1 : cartItemList) {
                        if (cartItemDTO1.getProductId() == productId
                                && cartItemDTO1.getColor().getColorId() == colorId
                                && cartItemDTO1.getSize().getSizeId() == sizeId) {
                            checkAllItemSame += cartItemDTO1.getQuantity();
                        }

                    }

                    // checkAllItemSame and quantityStock
                    if (checkAllItemSame > quantityStock) {
                        request.setAttribute("errorAddToCart", "Số lượng quantity không đủ");
                        request.getRequestDispatcher("productdetail?pid=" + productId).forward(request, response);
                    } else {
                        // Create dto CartItem and list CartItem
                        SizeDAO sizeDAO = new SizeDAO();
                        Size size = sizeDAO.getSizes(sizeId);
                        ColorDAO colorDAO = new ColorDAO();
                        Color color = colorDAO.getColors(colorId);
                        CartItemDTO cartItemDTO = new CartItemDTO(product.getProductId(), product.getProductName(), product.getImage(), size, color, product.getPrice(), quantityUser, quantityUser * product.getPrice());
                        cartItemList.add(cartItemDTO);

                        float totalAllPrice = 0;
                        DecimalFormat df = new DecimalFormat("0.00");

                        for (CartItemDTO cartItemDTO2 : cartItemList) {
                            totalAllPrice += (cartItemDTO2.getQuantity() * cartItemDTO2.getPrice());
                        }

                        
                        session.setAttribute("cartItemList", cartItemList);
                        session.setAttribute("totalAllPrice", df.format(totalAllPrice));
                        
                        response.sendRedirect("./views/user/item-page/shoppingcart.jsp");
                    }

                } else if (quantityUser > quantityStock) {
                    request.setAttribute("errorAddToCart", "Số lượng quantity không đủ");
                    request.getRequestDispatcher("productdetail?pid=" + productId).forward(request, response);
                }
            } else if (quantityUser == 0) {
                request.setAttribute("errorAddToCart", "Bạn hãy chọn số lượng quantity");
                request.getRequestDispatcher("productdetail?pid=" + productId).forward(request, response);
            } else {
                request.setAttribute("errorAddToCart", "Bạn không thể chọn quantity âm");
                request.getRequestDispatcher("productdetail?pid=" + productId).forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession();
        List<CartItemDTO> cartItemList = (List<CartItemDTO>) session.getAttribute("cartItemList");

        // UpdateShoppingcart
        int pid = Integer.parseInt(request.getParameter("pid"));

        // pid == 0 -> clear all
        if (pid == 0) {

            cartItemList.clear(); // delete all

            session.setAttribute("cartItemList", cartItemList);
            session.setAttribute("totalAllPrice", 0);
        } else {
            int qid = Integer.parseInt(request.getParameter("qid"));
            int sttid = Integer.parseInt(request.getParameter("sttid"));

            float totalAllPrice = 0;
            int stt = 0;
            Iterator<CartItemDTO> iterator = cartItemList.iterator();// not change  index when delete item

            while (iterator.hasNext()) {
                CartItemDTO cartItemDTO = iterator.next();
                stt++;

                if (cartItemDTO.getProductId() == pid
                        && cartItemDTO.getQuantity() == qid
                        && stt == sttid) {
                    iterator.remove();
                    break;
                }

            }

            for (CartItemDTO cartItemDTO : cartItemList) {
                totalAllPrice += cartItemDTO.getTotalMoney();
            }

            session.setAttribute("cartItemList", cartItemList);
            session.setAttribute("totalAllPrice", totalAllPrice);
        }

        response.sendRedirect("./views/user/item-page/shoppingcart.jsp");

    }

}
