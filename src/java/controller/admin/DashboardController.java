/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.CategoryDAO;
import dal.OrderDAO;
import dal.ProductDAO;
import dal.SupperlierDAO;
import dal.UserAdminDAO;
import dal.UserDAO;
import dal.WalletDAO;
import dto.RecentOrdersAdminDTO;
import dto.RecentOrdersDTO;
import dto.TopCustomAdminDTO;
import dto.UserListAdminDTO;
import dto.WalletAdminDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Order;
import model.Product;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DashboardController", urlPatterns = {"/dashboard"})
public class DashboardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Get userList in admin
        UserAdminDAO userAdminDAO = new UserAdminDAO();
        List<UserListAdminDTO> userAdminLists = new ArrayList<>();
        userAdminLists = userAdminDAO.getUserList();
        session.setAttribute("totalBuyByUser", userAdminLists);
    
        // Top statistic -> totalUser
        int totalUser = userAdminDAO.getTotalUser();
        request.setAttribute("totalUser", totalUser);

        // Top statistic -> totalSupperliers 
        SupperlierDAO supperlierDAO = new SupperlierDAO();
        int totalSupperliers = supperlierDAO.getTotalSupperliers();
        request.setAttribute("totalSupperliers", totalSupperliers);

        // Top statistic -> totalCategory
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categoryAll = categoryDAO.getCategorysAll();
        request.setAttribute("categoryAll", categoryAll);

        // Top statistic -> totalOrder
        OrderDAO orderDAO = new OrderDAO();
        int totalOrder = orderDAO.getTotalOrder();
        request.setAttribute("totalOrder", totalOrder);

        // Top statistic -> totalProduct
        ProductDAO productDAO = new ProductDAO();
        List<Product> totalProduct = productDAO.getProductsAll();
        request.setAttribute("totalProduct", totalProduct.size());

        // Top statistic -> totalProductSold
        int totalProductSold = 0;
        for (Product product : totalProduct) {
            totalProductSold += product.getQuantitySold();
        }
        request.setAttribute("totalProductSold", totalProductSold);

        // Top statistic -> totalRevenue
        double totalRevenue = orderDAO.getTotalRevenue();
        request.setAttribute("totalRevenue", totalRevenue);

        // Top 10 Recent orders
        List<RecentOrdersAdminDTO> recentOrdersDTOs = orderDAO.getRecentOrdersAdmin();
        request.setAttribute("top1OrderRecent", recentOrdersDTOs);

        // Orders overview
        int totalPending = 0; // 0
        int totalComplated = 0; // 1
        int totalCancel = 0; // 2
        for (RecentOrdersAdminDTO recentOrdersDTO : recentOrdersDTOs) {
            switch (recentOrdersDTO.getStatus()) {
                case 0:
                    totalPending += 1;
                    break;
                case 1:
                    totalComplated += 1;
                    break;
                case 2:
                    totalCancel += 1;
                    break;
                default:
                    break;
            }
        }
        request.setAttribute("totalPending", totalPending);
        request.setAttribute("totalComplated", totalComplated);
        request.setAttribute("totalCancel", totalCancel);

        // Top 10 Customers
        List<TopCustomAdminDTO> list = userAdminDAO.getTopCustomer();
        request.setAttribute("topUserByTotalMoney", list);

        // Top 10 Product
        List<Product> productLists = productDAO.getTop10ProductBestSelling();
        request.setAttribute("top10ProductBestSelling", productLists);

        request.getRequestDispatcher("./views/admin/dashboard/dashboard.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
