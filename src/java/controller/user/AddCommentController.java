/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.CommentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import model.Comment;
import model.Product;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddCommentController", urlPatterns = {"/addcomment"})
public class AddCommentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User userAccount = (User) session.getAttribute("account");

        int pid = Integer.parseInt(request.getParameter("pid"));
        String content = request.getParameter("content");
        Date conmment_date = new Date();
        int rating = Integer.parseInt(request.getParameter("rating"));

        Product product = new Product();
        product.setProductId(pid);

        Comment comment = new Comment(product, userAccount, content, conmment_date, rating);
        System.out.println(comment.toString());

        CommentDAO commentDAO = new CommentDAO();
        if (commentDAO.insertComment(comment)) {

            response.sendRedirect("productdetail?pid=" + pid);
        }

    }

}
