/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.OrderDAO;
import dto.RecentOrdersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Order;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "OrderhistoryController", urlPatterns = {"/orderHistory"})
public class OrderhistoryController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderDAO orderDAO = new OrderDAO();
        List<RecentOrdersDTO> ListAllOrderAndOrderDetail = orderDAO.getOrderHistoryAlls();
        
        request.setAttribute("ListAllOrderAndOrderDetail", ListAllOrderAndOrderDetail);
        request.getRequestDispatcher("./views/admin/item-page/orderhistory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
