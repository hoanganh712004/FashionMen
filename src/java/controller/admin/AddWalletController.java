/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.WalletDAO;
import dto.WalletAdminDTO;
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

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddWalletController", urlPatterns = {"/addwallet"})
public class AddWalletController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String walletId_raw = request.getParameter("walletId");
        int walletId = Integer.parseInt(walletId_raw);

        String balance_raw = request.getParameter("balance");
        System.out.println(balance_raw);
        try {
             WalletDAO walletDAO = new WalletDAO();
            float balance = Float.parseFloat(balance_raw);
            if (walletDAO.updateBalance(walletId, balance)) {
                List<WalletAdminDTO> walletAdminLists = new ArrayList<>();
                walletAdminLists = walletDAO.getWalletList();
                request.setAttribute("walletAll", walletAdminLists);

                request.getRequestDispatcher("views/admin/item-page/wallets.jsp").forward(request, response);
            }

        } catch (IOException | NumberFormatException e) {
            response.sendRedirect("./views/admin/item-page/wallets.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
