/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.ColorDAO;
import dal.ProductDAO;
import dal.CommentDAO;
import dal.ProductDetailDAO;
import dal.SizeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.Color;
import model.Comment;
import model.Product;
import model.ProductDetail;
import model.Size;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ProductDetailController", urlPatterns = {"/productdetail"})
public class ProductDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User userAccount = (User) session.getAttribute("account");

        if (userAccount != null) {
            int pid = Integer.parseInt(request.getParameter("pid"));

            // Product detail
            ProductDAO productDAO = new ProductDAO();
            Product productOfDetail = new Product();
            productOfDetail = productDAO.getProductOfDetail(pid);

            // List Comment
            CommentDAO commentDAO = new CommentDAO();
            List<Comment> comments = new ArrayList<>();
            comments = commentDAO.getComments(pid);
            float checkRating = 0;
            for (Comment comment : comments) {
                checkRating += comment.getRating();
            }
            int realRating = (int) (checkRating / comments.size());

            // Size
            SizeDAO sizeDAO = new SizeDAO();
            List<Size> sizeLists = new ArrayList<>();
            sizeLists = sizeDAO.getSizesAll();
            request.setAttribute("listSize", sizeLists);

            // Color
            ColorDAO colorDAO = new ColorDAO();
            List<Color> colorLists = new ArrayList<>();
            colorLists = colorDAO.getColorsAll();
            request.setAttribute("listColor", colorLists);

            // Related Product
            int categoryIdRelatedProduct = productOfDetail.getCategory().getCategoryId();
            List<Product> categoryRelatedProduct = new ArrayList<>();
            categoryRelatedProduct = productDAO.getCategoryRelatedProduct(categoryIdRelatedProduct);
            request.setAttribute("relatedProduct", categoryRelatedProduct);

            // catch exeption colorid and sizeid
            session.setAttribute("colorid", null);
            session.setAttribute("sizeid", null);
            session.setAttribute("productid", pid);

            request.setAttribute("product", productOfDetail);
            request.setAttribute("productRating", realRating);
            request.setAttribute("numberRating", comments.size());
            request.setAttribute("listComment", comments);
            request.getRequestDispatcher("views/user/item-page/productdetail.jsp").forward(request, response);
        } else {
            response.sendRedirect("./views/common/user/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User userAccount = (User) session.getAttribute("account");

        if (userAccount != null) {
            int productId = Integer.parseInt(request.getParameter("productid"));
            int colorId = Integer.parseInt(request.getParameter("colorid"));
            int sizeId = Integer.parseInt(request.getParameter("sizeid"));

            // Check related
            ProductDAO productDAO = new ProductDAO();
            Product productOfDetail = new Product();
            productOfDetail = productDAO.getProductOfDetail(productId);

            // Check color size
            Product productOfDetail1 = new Product();
            ProductDetailDAO productDetailDAOColorSize = new ProductDetailDAO();
            productOfDetail1 = productDetailDAOColorSize.getProductOfDetailColorSize(colorId, sizeId, productId);

            // List comment
            CommentDAO commentDAO = new CommentDAO();
            List<Comment> comments = new ArrayList<>();
            comments = commentDAO.getComments(productId);
            float checkRating = 0;
            for (Comment comment : comments) {
                checkRating += comment.getRating();
            }
            int realRating = (int) (checkRating / comments.size());

            // Size
            SizeDAO sizeDAO = new SizeDAO();
            List<Size> sizeLists = new ArrayList<>();
            sizeLists = sizeDAO.getSizesAll();
            request.setAttribute("listSize", sizeLists);

            // Color
            ColorDAO colorDAO = new ColorDAO();
            List<Color> colorLists = new ArrayList<>();
            colorLists = colorDAO.getColorsAll();
            request.setAttribute("listColor", colorLists);

            // Related Product
            int categoryIdRelatedProduct = productOfDetail.getCategory().getCategoryId();
            List<Product> categoryRelatedProduct = new ArrayList<>();
            categoryRelatedProduct = productDAO.getCategoryRelatedProduct(categoryIdRelatedProduct);
            request.setAttribute("relatedProduct", categoryRelatedProduct);

            request.setAttribute("product", productOfDetail1);
            request.setAttribute("productRating", realRating);
            request.setAttribute("numberRating", comments.size());
            request.setAttribute("listComment", comments);

            // Goal to http shopping cart nhận được
            session.setAttribute("colorid", colorId);
            session.setAttribute("sizeid", sizeId);
            session.setAttribute("productid", productId);
            session.setAttribute("quantityStock", productOfDetail1.getQuantityStock());
            request.getRequestDispatcher("views/user/item-page/productdetail.jsp").forward(request, response);
        } else {
            response.sendRedirect("./views/common/user/login.jsp");
        }
    }

}
