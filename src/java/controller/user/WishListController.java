/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import model.Product;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "WishListControllor", urlPatterns = {"/wishlist"})
public class WishListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User userAccount = (User) session.getAttribute("account");

        if (userAccount != null) {
            String action = request.getParameter("action");

            switch (action) {
                case "add":
                    addWishList(request, response);
                    break;
                case "delete":
                    deleteWishList(request, response);
                    break;
                default:
                    response.sendRedirect("./views/user/item-page/wishlist.jsp");
            }

        } else {
            response.sendRedirect("home");
        }
    }

    private void addWishList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String pid_raw = request.getParameter("pid");
        int pid = Integer.parseInt(pid_raw);
        System.out.println(pid);

        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductId(pid);

        ArrayList<Product> wishList = (ArrayList<Product>) session.getAttribute("wishList");
        if (wishList == null) {
            wishList = new ArrayList<>();
        }

        // if exists -> not add
        boolean exists = false;
        for (Product p : wishList) {
            if (p.getProductId() == pid) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            wishList.add(product);
        }

        session.setAttribute("wishList", wishList);

        response.sendRedirect("./views/user/item-page/wishlist.jsp");

    }

    private void deleteWishList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        ArrayList<Product> wishList = (ArrayList<Product>) session.getAttribute("wishList");
        if (wishList == null) {
            wishList = new ArrayList<>();
        }

        String pid_raw = request.getParameter("pid");
        int pid = Integer.parseInt(pid_raw);

        Iterator<Product> iterator = wishList.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductId() == pid) {
                iterator.remove();
                break;
            }
        }

        session.setAttribute("wishList", wishList);

        response.sendRedirect("./views/user/item-page/wishlist.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
