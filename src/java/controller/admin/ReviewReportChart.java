/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.CommentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Comment;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ReviewReportChart", urlPatterns = {"/reviewreportchart"})
public class ReviewReportChart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CommentDAO commentDAO = new CommentDAO();
        List<Comment> commentList = commentDAO.getComments();

        int[] star5 = new int[12];
        int[] star4 = new int[12];
        int[] star3 = new int[12];
        int[] star2 = new int[12];
        int[] star1 = new int[12];

        // Lấy năm từ request, nếu không có thì lấy năm hiện tại
        int currentYear = LocalDate.now().getYear();
        int year = currentYear;
        String selectedYear = request.getParameter("year");
        if (selectedYear != null && !selectedYear.isEmpty()) {
            year = Integer.parseInt(selectedYear);
        }

        // Xử lý dữ liệu từ commentList
        for (Comment comment : commentList) {
            Date date = comment.getCommentDate();  // Lấy giá trị Date từ DB
            LocalDate commentDate = new java.sql.Date(date.getTime()).toLocalDate(); // Chuyển đổi đúng cách

            // Chỉ thống kê comment của năm đã chọn
            if (commentDate.getYear() == year) {
                int month = commentDate.getMonthValue() - 1; // Mảng index từ 0-11

                // Tăng số lượng đánh giá theo sao
                switch (comment.getRating()) {
                    case 5:
                        star5[month]++;
                        break;
                    case 4:
                        star4[month]++;
                        break;
                    case 3:
                        star3[month]++;
                        break;
                    case 2:
                        star2[month]++;
                        break;
                    case 1:
                        star1[month]++;
                        break;
                }
            }
        }

        // Truyền dữ liệu vào request scope để hiển thị trên JSP
        for (int i = 0; i < 12; i++) {
            request.setAttribute("month" + (i + 1) + "star5", star5[i]);
            request.setAttribute("month" + (i + 1) + "star4", star4[i]);
            request.setAttribute("month" + (i + 1) + "star3", star3[i]);
            request.setAttribute("month" + (i + 1) + "star2", star2[i]);
            request.setAttribute("month" + (i + 1) + "star1", star1[i]);
        }

        // Truyền danh sách năm để dropdown không bị mất giá trị
        List<Integer> yearList = new ArrayList<>();
        for (int i = currentYear - 4; i <= currentYear; i++) {
            yearList.add(i);
        }
        request.setAttribute("yearList", yearList);
        request.setAttribute("year", year);
        request.setAttribute("selected", year);

        // Chuyển dữ liệu sang trang JSP
        request.getRequestDispatcher("./views/admin/item-page/reviewreportchart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
