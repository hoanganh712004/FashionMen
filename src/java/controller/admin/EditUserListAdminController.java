/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.UserAdminDAO;
import dal.UserDAO;
import dto.UpdateUserDTO;
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
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "EditUserListAdminController", urlPatterns = {"/edituser"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class EditUserListAdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String userId_raw = request.getParameter("userId");
        int userId = Integer.parseInt(userId_raw);

        UserDAO userDAO = new UserDAO();
        User userAccount = userDAO.getUserById(userId);

        session.setAttribute("userIdAdminCheck", userId);
        session.setAttribute("accountAdmin", userAccount);
        response.sendRedirect("./views/admin/item-page/userprofile.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userIdAdminCheck");
        UserDAO userDAO = new UserDAO();
        User userAccount = userDAO.getUserById(userId);

        // Lấy thông tin từ request
        String fullName = request.getParameter("fullName");
        String newPassword = request.getParameter("newPassword");
        String email = request.getParameter("email");
        //Part part = request.getPart("image");
        String birthDayStr = request.getParameter("birthDay");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");

        Part part = request.getPart("image");
        String imagePath = userAccount.getImage(); // Mặc định giữ ảnh cũ nếu không upload ảnh mới

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

        UpdateUserDTO updateUserDTO = new UpdateUserDTO(
                userAccount.getUserId(), imagePath, fullName, newPassword, email, birthDayStr, address, phoneNumber
        );

        boolean checkUpdateUser = userDAO.updateUser(updateUserDTO);

        if (checkUpdateUser) {
            
            // Get userList in admin
            UserAdminDAO userAdminDAO = new UserAdminDAO();
            List<UserListAdminDTO> userAdminLists = new ArrayList<>();
            userAdminLists = userAdminDAO.getUserList();
            session.setAttribute("totalBuyByUser", userAdminLists);

            response.sendRedirect("./views/admin/item-page/userlist.jsp");
        }
    }

}
