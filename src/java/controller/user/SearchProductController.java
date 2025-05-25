/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.CategoryDAO;
import dal.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import jakarta.servlet.http.HttpSession;
import model.Category;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SearchProductController", urlPatterns = {"/search"})
public class SearchProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");

        // Category list
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categoryLists = new ArrayList<>();
        categoryLists = categoryDAO.getCategorysAll();
        request.setAttribute("listCategory", categoryLists);

        // Product list
        List<Product> productLists = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        productLists = productDAO.getProducts(keyword);

        request.setAttribute("listProduct", productLists);
        request.setAttribute("keyword", keyword);
        request.getRequestDispatcher("./views/user/item-page/shopdetail.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
