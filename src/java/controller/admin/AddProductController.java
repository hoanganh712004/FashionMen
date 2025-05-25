/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.CategoryDAO;
import dal.ColorDAO;
import dal.GalleryDAO;
import dal.ProductDAO;
import dal.SizeDAO;
import dal.SupperlierDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.List;
import model.Category;
import model.Color;
import model.Product;
import model.Size;
import model.Supperlier;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddProductController", urlPatterns = {"/addProduct"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class AddProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categorysAdminList = categoryDAO.getCategorysAll();
        session.setAttribute("categorysAdminList", categorysAdminList);

        SupperlierDAO supperlierDAO = new SupperlierDAO();
        List<Supperlier> supperlierAdminList = supperlierDAO.getSupperlierAll();
        session.setAttribute("supperlierAdminList", supperlierAdminList);

        response.sendRedirect("views/admin/item-page/addproduct.jsp");
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

        Product product = new Product(category, supperlier, productName, quantity, 0, price, sortDescription, imagePath, discount);
        ProductDAO productDAO = new ProductDAO();
        productDAO.insertProductAll(product);
        
        List<Product> productLists = productDAO.getProductsAll();
        Product lastProduct = productLists.get(productLists.size() - 1);
        GalleryDAO galleryDAO = new GalleryDAO();
        galleryDAO.insertGallery(lastProduct);
         
        response.sendRedirect("productListAdmin");

    }

}
