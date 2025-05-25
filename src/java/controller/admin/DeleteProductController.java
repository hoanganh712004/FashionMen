/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.CommentDAO;
import dal.GalleryDAO;
import dal.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DeleteProductController", urlPatterns = {"/deleteProduct"})
public class DeleteProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        CommentDAO commentDAO = new CommentDAO();
        commentDAO.deleteCommentByProductId(productId);
        
        GalleryDAO galleryDAO = new GalleryDAO();
        galleryDAO.deleteByProductid(productId);
        
        ProductDAO productDAO = new ProductDAO();
        productDAO.deleteProduct(productId);
        
        response.sendRedirect("productListAdmin");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
