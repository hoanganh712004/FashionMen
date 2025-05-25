/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.FeedbackDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.FeedBack;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "EditFeedbackController", urlPatterns = {"/editFeedback"})
public class EditFeedbackController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        FeedBack feedBackAll = feedbackDAO.getFeedBack(feedbackId);

        request.setAttribute("feedBackAll", feedBackAll);
        request.getRequestDispatcher("./views/admin/item-page/contactus.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        if (feedbackDAO.updateFeedBack(feedbackId, subject, message)) {
            response.sendRedirect("feedbackUser");
        }
    }
}
