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
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Category;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddCategoryController", urlPatterns = {"/addcategory"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class AddCategoryController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String nameCategory = request.getParameter("nameCategory");
        String sortDescription = request.getParameter("sortDescription");
        Part part = request.getPart("image");
        String imagePath = "";

        // Kiểm tra nếu có file mới được tải lên
        if (part != null && part.getSubmittedFileName() != null && !part.getSubmittedFileName().trim().isEmpty()) {

            // Đường dẫn build/web/images/users/ và Tạo đối tượng File đại diện cho thư mục đó.
            String uploadPath = request.getServletContext().getRealPath("images/index-1/categories");
            File uploadDir = new File(uploadPath);

            // Đảm bảo thư mục tồn tại
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Tạo tên file duy nhất để tránh ghi đè => thời gian d/m/y/h Epoch Time + tên ảnh
            String fileName = System.currentTimeMillis() + "_" + part.getSubmittedFileName();

            // Tạo đường dẫn file đầy đủ và  Lưu file vào thư mục
            File imageFile = new File(uploadDir, fileName);
            part.write(imageFile.getAbsolutePath());

            // Cập nhật đường dẫn ảnh mới
            imagePath = "images/index-1/categories/" + fileName;
        }

        CategoryDAO categoryDAO = new CategoryDAO();
        Category category = new Category(nameCategory, sortDescription, imagePath);
        if (categoryDAO.insertCategory(category)) {
            List<Category> categorLists = categoryDAO.getCategorysAll();
            request.setAttribute("mainCategory", categorLists);
            
            request.getRequestDispatcher("./views/admin/item-page/maincategory.jsp").forward(request, response);
        }
    }

}
