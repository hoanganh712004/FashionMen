/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Category;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "UpdateCategoryController", urlPatterns = {"/updatecategory"})
public class UpdateCategoryController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        CategoryDAO categoryDAO = new CategoryDAO();
        System.out.println(action);
        switch (action) {
            case "checkedit":
                String categoryId_raw = request.getParameter("categoryId");
                int categoryId = Integer.parseInt(categoryId_raw);
                List<Category> categorLists = categoryDAO.getCategorysAll();
                request.setAttribute("mainCategory", categorLists);

                Category checkEditCategory = categoryDAO.getCategorys(categoryId);
                request.setAttribute("checkEditCategory", checkEditCategory);
                request.getRequestDispatcher("./views/admin/item-page/maincategory.jsp").forward(request, response);
                break;

            case "delete":
                int categoryId2 = Integer.parseInt(request.getParameter("categoryId"));

                if (categoryDAO.deleteCategory(categoryId2)) {
                    
                    response.sendRedirect("categoryAdmin");
                 
                }
                break;
            default:
                response.sendRedirect("categoryAdmin");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int categoryId1 = Integer.parseInt(request.getParameter("categoryId"));
        String updateNameCategory = request.getParameter("updateNameCategory");
        String updateSortDescription = request.getParameter("updateSortDescription");
        String image = request.getParameter("image");
        CategoryDAO categoryDAO = new CategoryDAO();
        Category category = new Category(categoryId1, updateNameCategory, updateSortDescription, image);
        if (categoryDAO.updateCategory(category)) {
            List<Category> categorLists1 = categoryDAO.getCategorysAll();
            request.setAttribute("mainCategory", categorLists1);

            request.getRequestDispatcher("./views/admin/item-page/maincategory.jsp").forward(request, response);
        }
    }

}
