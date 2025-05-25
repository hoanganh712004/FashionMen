/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package dix.dal.controller.user;

import dal.CategoryDAO;
import dal.ColorDAO;
import dal.FeedbackDAO;
import dal.OrderDAO;
import dal.ProductDAO;
import dal.SizeDAO;
import dal.WalletDAO;
import dto.FeedbackDTO;
import dto.ProductItem;
import dto.RecentOrdersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.Category;
import model.Color;
import model.FeedBack;
import model.Order;
import model.Product;
import model.Size;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "HomeController", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User userAccount = (User) session.getAttribute("account");

        // Recent Order
        if (userAccount != null) {
            OrderDAO orderDAO = new OrderDAO();
            List<RecentOrdersDTO> recentOrdersOfUser = new ArrayList<>();

            recentOrdersOfUser = orderDAO.getRecentOrders(userAccount.getUserId());
            if (recentOrdersOfUser != null) {

                // 0 -> Pending
                // 1 -> Completed
                // 2 -> Cancel
                // total Money
                int pending = 0;
                int completed = 0;
                int cancel = 0;
                float totalMoneyOrder = 0;
                for (RecentOrdersDTO order : recentOrdersOfUser) {
                    switch (order.getStatus()) {
                        case 0:
                            pending += 1;
                            break;
                        case 1:
                            completed += 1;
                            break;
                        case 2:
                            cancel += 1;
                            break;
                        default:
                            break;
                    }

                    if (order.getStatus() != 2) {
                        totalMoneyOrder += order.getTotalMoney();
                    }

                }
                session.setAttribute("pending", pending);
                session.setAttribute("completed", completed);
                session.setAttribute("cancel", cancel);
                session.setAttribute("totalMoneyOrder", totalMoneyOrder);
                session.setAttribute("recentOrdersOfUser", recentOrdersOfUser);

                // Wallet
                WalletDAO walletDAO = new WalletDAO();
                DecimalFormat df = new DecimalFormat("0.00");  // Cấu trúc định dạng
                float wallet = walletDAO.getBalanceOfAccount(userAccount.getUserId());
                session.setAttribute("balance", df.format(wallet));
            }

        }

        // Price max, min
        ProductDAO productDAO = new ProductDAO();
        List<Product> productLists = new ArrayList<>();
        productLists = productDAO.getProductsAll();
        double minPrice = Double.MAX_VALUE, maxPrice = Double.MIN_VALUE;
        for (Product p : productLists) {
            if (minPrice > p.getPrice()) {
                minPrice = p.getPrice();
            }

            if (maxPrice < p.getPrice()) {
                maxPrice = p.getPrice();
            }
        }
        session.setAttribute("minPrice", minPrice);
        session.setAttribute("maxPrice", maxPrice);

        // Feedback and contactus
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        List<FeedbackDTO> feedbackLists = new ArrayList<>();
        feedbackLists = feedbackDAO.getFeedback();
        request.setAttribute("feedBackAll", feedbackLists);

        // Top 3 product by category by quantity_sold desc and category_id asc
        Map<Category, List<Product>> top3ProductByCategory = new LinkedHashMap<>();
        top3ProductByCategory = productDAO.getTop3ProductByCategory();
        session.setAttribute("top3ProductByCategory", top3ProductByCategory);

        // NumberOfProductByCategory
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> numberOfProductByCategory = new ArrayList<>();
        numberOfProductByCategory = categoryDAO.getCategorysAll();
        request.setAttribute("numberOfProductByCategory", numberOfProductByCategory);

        // Product items
        List<ProductItem> productItems = new ArrayList<>();
        productItems = categoryDAO.getProductItems();
        request.setAttribute("productItems", productItems);

        // top6NewProductByCategory and listTop6NewProductAll
        Map<Category, List<Product>> top6NewProductByCategory = new LinkedHashMap<>();
        top6NewProductByCategory = productDAO.top6NewProductByCategory();
        request.setAttribute("top6NewProductByCategory", top6NewProductByCategory);

        List<Product> listTop6NewProductAll = new ArrayList<>();
        listTop6NewProductAll = productDAO.getTop6NewProduct();
        request.setAttribute("listTop6NewProductAll", listTop6NewProductAll);

        if (userAccount != null) {
            // Top 10 product best selling by quantity_sold desc
            List<Product> top10ProductBestSelling = new ArrayList<>();
            top10ProductBestSelling = productDAO.getTop10ProductBestSelling();
            request.setAttribute("top10Bestseller", top10ProductBestSelling);
        }

        request.getRequestDispatcher("./views/user/home-page/homepage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
