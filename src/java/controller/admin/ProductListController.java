/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.CategoryDAO;
import dal.ProductDAO;
import dal.SupperlierDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Category;
import model.Product;
import model.Supperlier;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ProductListController", urlPatterns = {"/productListAdmin"})
public class ProductListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProductDAO productDAO = new ProductDAO();
        
        List<Product> productList = productDAO.getProductsAll();
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categorysAdminList = categoryDAO.getCategorysAll();
        session.setAttribute("categorysAdminList", categorysAdminList);

        SupperlierDAO supperlierDAO = new SupperlierDAO();
        List<Supperlier> supperlierAdminList = supperlierDAO.getSupperlierAll();
        session.setAttribute("supperlierAdminList", supperlierAdminList);

        request.setAttribute("productAll", productList);
        request.getRequestDispatcher("./views/admin/item-page/productlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
