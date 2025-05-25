/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.UserAdminDAO;
import dal.WalletDAO;
import dto.UserListAdminDTO;
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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddUserController", urlPatterns = {"/adduser"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class AddUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("./views/admin/item-page/userlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String role_raw = request.getParameter("role");
        int role = Integer.parseInt(role_raw);
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        String fullName = firstName + " " + lastName;
        String email = request.getParameter("email");
        String birthDate_raw = request.getParameter("birthDate");
        java.sql.Date birthDate = java.sql.Date.valueOf(birthDate_raw );
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        Date date = new Date();

        Part part = request.getPart("image");
        String imagePath = "";

        // Kiểm tra nếu có file mới được tải lên
        if (part != null && part.getSubmittedFileName() != null && !part.getSubmittedFileName().trim().isEmpty()) {

            // Đường dẫn build/web/images/users/ và Tạo đối tượng File đại diện cho thư mục đó.
            String uploadPath = request.getServletContext().getRealPath("images/users");
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
            imagePath = "images/users/" + fileName;
        }

        UserAdminDAO userAdminDAO = new UserAdminDAO();
        User user = new User(role, userName, encodeMD5(passWord), fullName, date, imagePath, phoneNumber, address, email, date);
        
        System.out.println(user.toString());
        
        if (userAdminDAO.insertUser(user)) {
            HttpSession session = request.getSession();
            List<UserListAdminDTO> userAdminLists = new ArrayList<>();
            userAdminLists = userAdminDAO.getUserList();
            
            UserListAdminDTO lastUser = userAdminLists.get(userAdminLists.size() - 1);
            WalletDAO walletDAO = new WalletDAO();
            walletDAO.insertWallet(lastUser.getUserId());
            
            session.setAttribute("totalBuyByUser", userAdminLists);
            
            response.sendRedirect("./views/admin/item-page/userlist.jsp");
        } else {
            request.setAttribute("insertUserError", "User name exist !!!");
            request.getRequestDispatcher("./views/admin/item-page/userlist.jsp").forward(request, response);
        } 

    }
    
     public static String encodeMD5(String password) {
        try {
            // Lấy một instance của MessageDigest với thuật toán MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Thêm mật khẩu vào để tính toán
            md.update(password.getBytes());

            // Lấy giá trị băm
            byte[] digest = md.digest();

            // Chuyển đổi byte array thành chuỗi hex
            BigInteger bigInt = new BigInteger(1, digest);
            String hashtext = bigInt.toString(16);

            // Đảm bảo chuỗi hex có đủ 32 ký tự
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }


}
