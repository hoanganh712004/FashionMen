/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.CategoryDAO;
import dal.GalleryDAO;
import dal.ProductDAO;
import dal.SupperlierDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Category;
import model.Product;
import model.Supperlier;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "EditProductController", urlPatterns = {"/editproduct"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class EditProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductById(productId);
        
        request.setAttribute("editProduct", product);
        request.getRequestDispatcher("./views/admin/item-page/editproduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part part = request.getPart("image");
        String imagePath = ""; // Mặc định giữ ảnh cũ nếu không upload ảnh mới

        // Kiểm tra nếu có file mới được tải lên
        if (part != null && part.getSubmittedFileName() != null && !part.getSubmittedFileName().trim().isEmpty()) {

            // Đường dẫn build/web/images/users/ và Tạo đối tượng File đại diện cho thư mục đó.
            String uploadPath = request.getServletContext().getRealPath("images/products/updateproduct");
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
            imagePath = "images/products/updateproduct/" + fileName;
        }

        int categoryId = Integer.parseInt(request.getParameter("category"));
        CategoryDAO categoryDAO = new CategoryDAO();
        Category category = categoryDAO.getCategorys(categoryId);

        int supperlierId = Integer.parseInt(request.getParameter("supperlier"));
        SupperlierDAO supperlierDAO = new SupperlierDAO();
        Supperlier supperlier = supperlierDAO.getSupperlier(supperlierId);

        String productName = request.getParameter("productName");
        String sortDescription = request.getParameter("sortDescription");
        int discount = Integer.parseInt(request.getParameter("discount"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        float price = Float.parseFloat(request.getParameter("price"));
        int productId = Integer.parseInt(request.getParameter("productId"));

        Product product = new Product(productId, category, supperlier, productName, quantity, 0, price, sortDescription, imagePath, discount, quantity);
        ProductDAO productDAO = new ProductDAO();
        productDAO.updateProduct(product);
        
        System.out.println(productId);
        GalleryDAO galleryDAO = new GalleryDAO();
        galleryDAO.updateGallery(product);
        
        response.sendRedirect("productListAdmin");
        
    }


}
