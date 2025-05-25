/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.CategoryDAO;
import dal.ProductDAO;
import dto.FilterProductDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import model.Category;
import model.Page;
import model.Product;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "FilterProductController", urlPatterns = {"/filterproduct"})
public class FilterProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy các tham số từ request
        HttpSession session = request.getSession();

        // Category
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categoryLists = new ArrayList<>();
        categoryLists = categoryDAO.getCategorysAll();
        request.setAttribute("listCategory", categoryLists);

        try {
            String[] categories = request.getParameterValues("category");
            float maxPrice = Float.parseFloat(request.getParameter("maxPrice"));
            float minPrice = Float.parseFloat(request.getParameter("minPrice"));
            int orderBy = Integer.parseInt(request.getParameter("orderBy"));

            String[] check = {"0"};
            if (categories == null) {
                categories = check;
            } else {
                Arrays.sort(categories);
            }

            ProductDAO productDAO = new ProductDAO();
            FilterProductDTO filterProductDTO = new FilterProductDTO(categories, maxPrice, minPrice, orderBy);
            List<Product> products = productDAO.getFilterProduct(filterProductDTO);
            request.setAttribute("listProduct", products);

            request.getRequestDispatcher("./views/user/item-page/shopdetail.jsp").forward(request, response);

        } catch (Exception e) {
            List<Product> productLists = new ArrayList<>();
            ProductDAO productDAO = new ProductDAO();
            
            // Create objet pageControl
            Page pageControl = new Page();

            // Get page and Validate page
            String pageRaw = request.getParameter("page");

            int page;
            try {
                page = Integer.parseInt(pageRaw);
            } catch (NumberFormatException ex) {
                page = 1;
            }

            // totalRecords
            int totalRecords = productDAO.findTotalRecord();
            
            // Total pages
            int totalPage = (totalRecords % 9) == 0
                    ? (totalRecords / 9)
                    : (totalRecords / 9) + 1;
            request.setAttribute("totalPage", totalPage);
            
            // List product of page
            List<Product> listProduct = productDAO.findByPage(page);
            session.setAttribute("listProduct", listProduct);
            
            // Get Link
            String requestURL = request.getRequestURL().toString(); // http:/localhost:9999/360-fashion-men-vn/filterproduct?
            request.setAttribute("requestURL", requestURL+"?");
            
            // Total page
            pageControl.setTotalPage(totalPage);
            pageControl.setPage(page);
            pageControl.setUrlPattern(requestURL + "?");
            request.setAttribute("pageControl", pageControl);
            System.out.println(page);
            request.getRequestDispatcher("./views/user/item-page/shopdetail.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
