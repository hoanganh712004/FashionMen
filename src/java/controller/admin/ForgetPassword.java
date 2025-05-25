package controller.admin;

import dal.UserAdminDAO;
import dto.UserVericationDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

@WebServlet(name = "ForgetPassword", urlPatterns = {"/forgetPassword"})
public class ForgetPassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String ma = request.getParameter("ma");

        UserAdminDAO userAdminDAO = new UserAdminDAO();
        List<UserVericationDTO> listGetVerificationCode = userAdminDAO.getUserListAfterupdatVerificattion();

        boolean checkMa = false;

        for (UserVericationDTO userVericationDTO : listGetVerificationCode) {
            if (userVericationDTO.getEmail() == null || userVericationDTO.getVerificationCode() == null) {
                continue;
            }
            if (userVericationDTO.getEmail().equals(email) && userVericationDTO.getVerificationCode().equals(ma)) {
                checkMa = true;
                break;
            }
        }

        if (checkMa) {
            request.setAttribute("checkEmail", email);
            request.getRequestDispatcher("./views/common/user/newpassword.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMa", "Error Code");
            request.getRequestDispatcher("./views/common/user/forget.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String verificationCode = generateVerificationCode();

        System.out.println(verificationCode);

        UserAdminDAO userAdminDAO = new UserAdminDAO();
        List<UserVericationDTO> listGetVerificationCode = userAdminDAO.getUserListAfterupdatVerificattion();

        boolean checkEmail = false;
        for (UserVericationDTO userVericationDTO : listGetVerificationCode) {
            if (userVericationDTO.getEmail() == null) {
                continue;
            }
            if (userVericationDTO.getEmail().equals(email)) {
                checkEmail = true;
                break;
            }
        }

        if (checkEmail) {

            if (userAdminDAO.updateVerification(verificationCode, email)) {
                // Gửi mã xác minh đến email
                sendVerificationEmail(email, verificationCode);

                request.setAttribute("checkEmail", email);
                request.getRequestDispatcher("./views/common/user/checkma.jsp").forward(request, response);
            }

        } else {
            request.setAttribute("errorEmail", "Error Email");
            request.getRequestDispatcher("./views/common/user/forget.jsp").forward(request, response);
        }
    }

    public static String generateVerificationCode() {
        // Tạo một chuỗi gồm tất cả các chữ cái (hoa và thường) và các chữ số
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder code = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            int randomIndex = random.nextInt(characters.length());
            code.append(characters.charAt(randomIndex));
        }

        return code.toString();  // Trả về mã xác minh
    }

    // Gửi mã xác minh qua email
    // Gửi mã xác minh qua email
    private void sendVerificationEmail(String toEmail, String verificationCode) {
        String fromEmail = ""; // Địa chỉ Gmail của bạn
        String password = ""; // Mật khẩu của email của bạn (hoặc mật khẩu ứng dụng)

        // Cấu hình kết nối đến SMTP server của Gmail
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);  // Sử dụng mật khẩu ứng dụng nếu có
            }
        });

        try {
            // Tạo nội dung email với charset UTF-8 để hỗ trợ tiếng Việt
            String subject = "Mã xác minh thay đổi mật khẩu";
            String body = "Mã xác minh của bạn là: " + verificationCode + "\nHãy nhập mã này để thay đổi mật khẩu của bạn.";

            // Tạo đối tượng message với MIME type là "text/plain; charset=UTF-8"
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject, "UTF-8");  // Đảm bảo rằng tiêu đề cũng sử dụng UTF-8
            message.setText(body, "UTF-8"); // Cấu hình charset cho nội dung email

            // Gửi email
            Transport.send(message);

            System.out.println("Mã xác minh đã được gửi đến email: " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
