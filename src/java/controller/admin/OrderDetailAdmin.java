/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.PaymentDAO;
import dto.OrderDetailProductSummaryDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.Payment;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "OrderDetailAdmin", urlPatterns = {"/orderdetailadmin"})
public class OrderDetailAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderDetailDAO orderDetail = new OrderDetailDAO();
        List<OrderDetailProductSummaryDTO> orderDetailList = orderDetail.getOrderDetails(orderId);

        OrderDAO orderDAO = new OrderDAO();
        Order order = orderDAO.getOrder(orderId);

        PaymentDAO paymentDAO = new PaymentDAO();
        Payment payment = paymentDAO.getPayment(order.getPaymentId());

        request.setAttribute("order", order);
        request.setAttribute("payment", payment);
        request.setAttribute("orderDetailList", orderDetailList);
        request.getRequestDispatcher("./views/admin/item-page/orderdetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
