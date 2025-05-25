/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dix.dal.config.DBContext;
import dto.CommentDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Comment;
import model.Product;
import model.User;

/**
 *
 * @author ADMIN
 */
public class CommentDAO extends DBContext {

    public List<Comment> getComments(int pid) {

        // Create list Category
        List<Comment> commentLists = new ArrayList<>();

        // Connect database
        connection = getConnection();

        // String sql
        String sql = """
                     SELECT [product_id]
                           ,[user_id]
                           ,[content]
                           ,[comment_date]
                           ,[rating]
                       FROM [dbo].[Comment]
                     WHERE product_id = ?""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, pid);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int productId = Integer.parseInt(resultSet.getString("product_id"));
                int userId = Integer.parseInt(resultSet.getString("user_id"));
                String content = resultSet.getString("content");
                Date image = resultSet.getDate("comment_date");
                int rating = Integer.parseInt(resultSet.getString("rating"));

                ProductDAO productDAO = new ProductDAO();
                Product product = new Product();
                product = productDAO.getProductId(productId);

                UserDAO userDAO = new UserDAO();
                User user = new User();
                user = userDAO.getUserById(userId);

                Comment comment = new Comment(product, user, content, image, rating);
                commentLists.add(comment);
            }
            return commentLists;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Comment> getComments() {

        // Create list Category
        List<Comment> commentLists = new ArrayList<>();

        // Connect database
        connection = getConnection();

        // String sql
        String sql = """
                     SELECT [product_id]
                           ,[user_id]
                           ,[content]
                           ,[comment_date]
                           ,[rating]
                       FROM [dbo].[Comment]
                     """;

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int productId = Integer.parseInt(resultSet.getString("product_id"));
                int userId = Integer.parseInt(resultSet.getString("user_id"));
                String content = resultSet.getString("content");
                Date image = resultSet.getDate("comment_date");
                int rating = Integer.parseInt(resultSet.getString("rating"));

                ProductDAO productDAO = new ProductDAO();
                Product product = new Product();
                product = productDAO.getProductId(productId);

                UserDAO userDAO = new UserDAO();
                User user = new User();
                user = userDAO.getUserById(userId);

                Comment comment = new Comment(product, user, content, image, rating);
                commentLists.add(comment);
            }
            return commentLists;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean insertComment(Comment comment) {

        // Connect database
        connection = getConnection();

        // String sql
        String sql = """
                     INSERT INTO [dbo].[Comment]
                                ([product_id]
                                ,[user_id]
                                ,[content]
                                ,[comment_date]
                                ,[rating])
                          VALUES
                                (?
                                ,?
                                ,?
                                ,?
                                ,?)""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, comment.getProduct().getProductId());
            statement.setObject(2, comment.getUser().getUserId());
            statement.setObject(3, comment.getContent());

            statement.setObject(4, comment.getCommentDate());
            statement.setObject(5, comment.getRating());

            // ExecuteQuery
            int check = statement.executeUpdate();

            return check > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        Product product = new Product();
        product.setProductId(95); // Giả sử sản phẩm có ID = 1

        // Giả lập dữ liệu người dùng
        User user = new User();
        user.setUserId(1); // Giả sử người dùng có ID

        Date commentDate = new Date();

        Comment comment = new Comment(product, user, "hay lắm pro", commentDate, 2);
        System.out.println(new CommentDAO().insertComment(comment));
    }

    public List<CommentDTO> getCommentALLs() {

        // Create list Category
        List<CommentDTO> commentLists = new ArrayList<>();

        // Connect database
        connection = getConnection();

        // String sql
        String sql = """
                     SELECT [product_id]
                           ,[user_id]
                           ,[content]
                           ,[comment_date]
                           ,[rating]
                           ,[comment_id]
                       FROM [dbo].[Comment]""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int productId = Integer.parseInt(resultSet.getString("product_id"));
                int userId = Integer.parseInt(resultSet.getString("user_id"));
                String content = resultSet.getString("content");
                Date image = resultSet.getDate("comment_date");
                int rating = Integer.parseInt(resultSet.getString("rating"));
                int commentId = Integer.parseInt(resultSet.getString("comment_id"));
                
                ProductDAO productDAO = new ProductDAO();
                Product product = new Product();
                product = productDAO.getProductId(productId);

                UserDAO userDAO = new UserDAO();
                User user = new User();
                user = userDAO.getUserById(userId);

                CommentDTO commentDTO = new CommentDTO(commentId, product, user, content, image, rating);
                commentLists.add(commentDTO);
            }
            return commentLists;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean deleteComment(int commentId) {
        
        // Connect database
        connection = getConnection();

        // String sql
        String sql = """
                     DELETE FROM [dbo].[Comment]
                           WHERE comment_id = ?""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, commentId);
           

            // ExecuteQuery
            int checkDeleteComment = statement.executeUpdate();

            return checkDeleteComment > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteCommentByProductId(int productId) {
        
        // Connect database
        connection = getConnection();

        // String sql
        String sql = """
                     DELETE FROM [dbo].[Comment]
                           WHERE product_id = ?""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, productId);
           

            // ExecuteQuery
            int checkDeleteComment = statement.executeUpdate();

            return checkDeleteComment > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
