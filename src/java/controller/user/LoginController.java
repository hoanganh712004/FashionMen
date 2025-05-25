/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.OrderDAO;
import dal.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.User;
/**
 *
 * @author ADMIN
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("./views/common/user/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        UserDAO userDAO = new UserDAO();
        
        HttpSession session = request.getSession();
                
        if (userDAO.checkLogin(username, password) != null) {
            
            if (rememberMe !=null) {
                
                // Create Cookie and Assign data
                String maHoa = encodeMD5(password);
                Cookie usernameCookie = new Cookie("COOKIE_USERNAME", username);
                Cookie passwordCookie = new Cookie("COOKIE_PASSWORD", password);
                
                // Set time Cookie => second
                usernameCookie.setMaxAge(60 * 60 * 24); // One day
                passwordCookie.setMaxAge(60 * 60 * 24); // One day
                
                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
            } else {
                Cookie[] cookies = request.getCookies(); // Take data from cookie

                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("COOKIE_USERNAME")) {
                            cookie.setMaxAge(0); // Mark cookies for delete 
                            response.addCookie(cookie); // send cookie to browser and delete cookie
                        }

                        if (cookie.getName().equals("COOKIE_PASSWORD")) {
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                        }
                    }
                }
            }
            
            session.setAttribute("account", userDAO.checkLogin(username, password));
            response.sendRedirect("home");
        } else {
            request.setAttribute("errorLogin", "Login false !!!");
            request.getRequestDispatcher("./views/common/user/login.jsp").forward(request, response);
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
