/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dix.dal.config.DBContext;
import dto.RecentOrdersAdminDTO;
import dto.RecentOrdersDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Category;
import model.Order;

/**
 *
 * @author ADMIN
 */
public class OrderDAO extends DBContext {

    public List<RecentOrdersDTO> getRecentOrders(int userId) {

        // Create list recentOrdersOfUser
        List<RecentOrdersDTO> recentOrdersOfUser = new ArrayList<>();

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     SELECT o.Order_id, 
                            o.full_name, 
                            o.order_date, 
                            o.status, 
                            SUM(od.quantity) AS total_quantity,
                            SUM(od.price * od.quantity) AS total_money
                     FROM Orders o
                     JOIN Order_Details od ON o.Order_id = od.order_id
                     WHERE o.user_id = ?
                     GROUP BY o.Order_id, o.full_name, o.order_date, o.status;
                     """;

        try {

            // Create object PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set Object
            statement.setObject(1, userId);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int orderId = resultSet.getInt("Order_id");
                String fullName = resultSet.getString("full_name");
                Date orderDate = resultSet.getDate("order_date");
                int status = resultSet.getInt("status");
                int totalQuantity = resultSet.getInt("total_quantity"); // Use alias for SUM(od.quantity)
                float totalMoney = resultSet.getFloat("total_money");  // Use alias for SUM(od.price * od.quantity)

                RecentOrdersDTO recentOrdersDTO = new RecentOrdersDTO(orderId, fullName, orderDate, status, totalQuantity, totalMoney);

                recentOrdersOfUser.add(recentOrdersDTO);
            }

            return recentOrdersOfUser;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<RecentOrdersAdminDTO> getRecentOrdersAdmin() {

        // Create list recentOrdersOfUser
        List<RecentOrdersAdminDTO> recentOrdersOfUser = new ArrayList<>();

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql ="""
                    SELECT Top 10 o.Order_id, o.user_id ,
                                                o.full_name, 
                                                o.order_date, 
                                                o.status, 
                                                SUM(od.quantity) AS total_quantity,
                                                SUM(od.price * od.quantity) AS total_money
                                         FROM Orders o
                                         JOIN Order_Details od ON o.Order_id = od.order_id
                                         GROUP BY o.Order_id,o.user_id, o.full_name, o.order_date, o.status Order by o.Order_id desc""";

        try {

            // Create object PrepareStatement
            statement = connection.prepareStatement(sql);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int orderId = resultSet.getInt("Order_id");
                int userId = resultSet.getInt("user_id");
                String fullName = resultSet.getString("full_name");
                Date orderDate = resultSet.getDate("order_date");
                int status = resultSet.getInt("status");
                int totalQuantity = resultSet.getInt("total_quantity"); // Use alias for SUM(od.quantity)
                float totalMoney = resultSet.getFloat("total_money");  // Use alias for SUM(od.price * od.quantity)

                RecentOrdersAdminDTO recentOrdersAdminDTO = new RecentOrdersAdminDTO(orderId, userId, fullName, orderDate, status, totalQuantity, totalMoney);
                recentOrdersOfUser.add(recentOrdersAdminDTO);
            }

            return recentOrdersOfUser;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Order getOrder(int orderId) {

        // Create object order
        Order order = null;

        // Connect Database
        connection = getConnection();

        // Systax sql
        String sql = """
                     SELECT [Order_id]
                           ,[user_id]
                           ,[full_name]
                           ,[phone_number]
                           ,[email]
                           ,[address]
                           ,[order_date]
                           ,[note]
                           ,[payment_id]
                           ,[status]
                           ,[total_money]
                       FROM [dbo].[Orders]
                     WHERE Order_id = ?""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set Object
            statement.setObject(1, orderId);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int orderIdData = Integer.parseInt(resultSet.getString("Order_id"));
                int userId = Integer.parseInt(resultSet.getString("user_id"));
                String fullName = resultSet.getString("full_name");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                Date orderDate = resultSet.getDate("order_date");
                String note = resultSet.getString("note");
                int paymentId = Integer.parseInt(resultSet.getString("payment_id"));
                int status = Integer.parseInt(resultSet.getString("status"));
                float totalMoney = Float.parseFloat(resultSet.getString("total_money"));

                order = new Order(orderIdData, userId, fullName, phoneNumber, email, address, orderDate, note, paymentId, status, totalMoney);

                return order;
            }
        } catch (NumberFormatException | SQLException e) {
        }

        return null;
    }

    public boolean insertOrder(Order order) {

        // Connect Database
        connection = getConnection();

        // Systax sql
        String sql = """
                     INSERT INTO [dbo].[Orders]
                                ([user_id]
                                ,[full_name]
                                ,[phone_number]
                                ,[email]
                                ,[address]
                                ,[order_date]
                                ,[note]
                                ,[payment_id]
                                ,[status]
                                ,[total_money])
                          VALUES
                                (?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?)""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set Object
            statement.setObject(1, order.getUserId());
            statement.setObject(2, order.getFullName());
            statement.setObject(3, order.getPhoneNumber());
            statement.setObject(4, order.getEmail());
            statement.setObject(5, order.getAddress());
            statement.setObject(6, order.getOrderDate());
            statement.setObject(7, order.getNote());
            statement.setObject(8, order.getPaymentId());
            statement.setObject(9, order.getStatus());
            statement.setObject(10, order.getTotalMoney());

            // ExecuteQuery
            int checkInsertOrder = statement.executeUpdate();

            return checkInsertOrder > 0;
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int getOrderLast() {

        // Connect Database
        connection = getConnection();

        // Systax sql
        String sql = """
                     SELECT Top 1 *
                     FROM [dbo].[Orders]
                     Order by Order_id desc""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int orderId = Integer.parseInt(resultSet.getString("Order_id"));
                return orderId;
            }

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public boolean updateStatus(int orderId, int status) {

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     UPDATE [dbo].[Orders]
                        SET [status] = ?
                      WHERE Order_id = ?""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object 
            statement.setObject(1, status);
            statement.setObject(2, orderId);

            // ExecuteUpdate
            int checkStatusOrder = statement.executeUpdate();

            return checkStatusOrder > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int getTotalOrder() {

        // Connect database
        connection = getConnection();

        // String sql
        String sql = """
                     SELECT COUNT(Order_id) as Total_Order
                       FROM Orders""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int checkTotalOrder = Integer.parseInt(resultSet.getString("Total_Order"));
                return checkTotalOrder;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public double getTotalRevenue() {
        
        // Connect database
        connection = getConnection();

        // String sql
        String sql = """
                     SELECT COALESCE(SUM(total_money), 0) AS Total_Revenue
                     FROM Orders;""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                double checkTotalRevence = Double.parseDouble(resultSet.getString("Total_Revenue"));
                return checkTotalRevence;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public List<RecentOrdersDTO> getOrderHistoryAlls() {
         // Create list recentOrdersOfUser
        List<RecentOrdersDTO> recentOrdersOfUser = new ArrayList<>();

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     SELECT o.Order_id, 
                            o.full_name, 
                            o.order_date, 
                            o.status, 
                            SUM(od.quantity) AS total_quantity,
                            SUM(od.price * od.quantity) AS total_money
                     FROM Orders o
                     JOIN Order_Details od ON o.Order_id = od.order_id
                     GROUP BY o.Order_id, o.full_name, o.order_date, o.status;
                     """;

        try {

            // Create object PrepareStatement
            statement = connection.prepareStatement(sql);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int orderId = resultSet.getInt("Order_id");
                String fullName = resultSet.getString("full_name");
                Date orderDate = resultSet.getDate("order_date");
                int status = resultSet.getInt("status");
                int totalQuantity = resultSet.getInt("total_quantity"); // Use alias for SUM(od.quantity)
                float totalMoney = resultSet.getFloat("total_money");  // Use alias for SUM(od.price * od.quantity)

                RecentOrdersDTO recentOrdersDTO = new RecentOrdersDTO(orderId, fullName, orderDate, status, totalQuantity, totalMoney);

                recentOrdersOfUser.add(recentOrdersDTO);
            }

            return recentOrdersOfUser;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteOrder(int orderId, int status) {
        
        
        // Connect data
        connection = getConnection();

        // Systax sql
        String sql = """
                     DELETE FROM [dbo].[Orders]
                           WHERE Order_id = ? AND status = ?""";
        
        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, orderId);
            statement.setObject(2, status);

            // ExecuteQuery
            int checkDeleleteOrder = statement.executeUpdate();
            
            return checkDeleleteOrder > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
//        OrderDAO orderDAO = new OrderDAO();
//        System.out.println(orderDAO.getOrderLast());
        System.out.println(new OrderDAO().getTotalRevenue());
    }

}
