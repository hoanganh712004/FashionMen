/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.UserAdminDAO;
import dal.WalletDAO;
import dto.UserListAdminDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DeleteUserAdmin", urlPatterns = {"/deleteuser"})
public class DeleteUserAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId_raw = request.getParameter("userId");
        int userId = Integer.parseInt(userId_raw);
        System.out.println(userId);

        // Delete Wallet
        WalletDAO walletDAO = new WalletDAO();
        walletDAO.deleteWallet(userId);

        UserAdminDAO userAdminDAO = new UserAdminDAO();
        if (userAdminDAO.deleteUser(userId)) {
            HttpSession session = request.getSession();

            // Get userList in admin
            List<UserListAdminDTO> userAdminLists = new ArrayList<>();
            userAdminLists = userAdminDAO.getUserList();
            session.setAttribute("totalBuyByUser", userAdminLists);

            response.sendRedirect("./views/admin/item-page/userlist.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
