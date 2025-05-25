/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.FeedbackDAO;
import dto.ContactusDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddContactusController", urlPatterns = {"/addcontactus"})
public class AddContactusController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String UserName = request.getParameter("name");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        // Lấy ngày hiện tại làm feedbackDate (nếu cần)
        Date feedbackDate = new Date();

        User user = new User();
        user.setUserId(userId);
        user.setUserName(UserName);
        ContactusDTO contactusDTO = new ContactusDTO(user, subject, feedbackDate, message);
        
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        if (feedbackDAO.insertFeedback(contactusDTO)) {
            request.setAttribute("feedbackSuccess", "Thank you for your feedback. Have a nice day!");
            request.getRequestDispatcher("./views/user/item-page/contactus.jsp").forward(request, response);
//            response.sendRedirect("./views/user/item-page/contactus.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
