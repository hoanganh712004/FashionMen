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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "UpdateNewpassword", urlPatterns = {"/updatenewpassword"})
public class UpdateNewpassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String npassword = request.getParameter("npassword");

        UserDAO userDAO = new UserDAO();
        if ( userDAO.updatepasswordUserByEmail(email,encodeMD5(npassword))) {
            UserAdminDAO userAdminDAO = new UserAdminDAO();
            List<UserListAdminDTO> list = userAdminDAO.getUserListnpassword();
            String username = "";
            for (UserListAdminDTO userListAdminDTO : list) {
                if (userListAdminDTO.getEmail().equals(email)) {
                    username += userListAdminDTO.getUserName();
                    break;
                }
            }
            
            request.setAttribute("username", username);
            request.getRequestDispatcher("./views/common/user/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
