/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.OrderDAO;
import dal.OrderDetailDAO;
import dto.RecentOrdersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DeleteStatusController", urlPatterns = {"/deleteStatus"})
public class DeleteStatusController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderId_raw = request.getParameter("orderId");
        String status_raw = request.getParameter("status");

        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        OrderDAO orderDAO = new OrderDAO();
        if (orderDetailDAO.deleteOrderDetailById(Integer.parseInt(orderId_raw))) {
            if (orderDAO.deleteOrder(Integer.parseInt(orderId_raw), Integer.parseInt(status_raw))) {

                List<RecentOrdersDTO> ListAllOrderAndOrderDetail = orderDAO.getOrderHistoryAlls();

                request.setAttribute("ListAllOrderAndOrderDetail", ListAllOrderAndOrderDetail);
                request.getRequestDispatcher("./views/admin/item-page/orderhistory.jsp").forward(request, response);
            }
        }

//        System.out.println(orderId_raw);
//        System.out.println(status_raw);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
