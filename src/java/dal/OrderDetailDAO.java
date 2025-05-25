/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dix.dal.config.DBContext;
import dto.OrderDetailProductSummaryDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Color;
import model.OrderDetail;
import model.Size;

/**
 *
 * @author ADMIN
 */
public class OrderDetailDAO extends DBContext {
    
    public List<OrderDetailProductSummaryDTO> getOrderDetails(int orderId) {

        // Create List OrderDetail
        List<OrderDetailProductSummaryDTO> orderDetailList = new ArrayList<>();

        // Connect database
        connection = getConnection();

        // String sql
        String sql = """
                     SELECT 
                         od.order_id,
                         od.product_id, 
                         od.quantity, 
                         od.price, 
                         pd.color_id, 
                         pd.size_id, 
                         p.product_name, 
                         p.image
                     FROM Order_Details od
                     JOIN Product_detail pd ON od.product_id = pd.product_id AND od.color_id = pd.color_id AND od.size_id = pd.size_id
                     JOIN Product p ON pd.product_id = p.product_id
                     WHERE od.order_id = ?""";
        
        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set Objet
            statement.setObject(1, orderId);

            // ExecuteQuery
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                int orderIdData = Integer.parseInt(resultSet.getString("order_id"));
                int productId = Integer.parseInt(resultSet.getString("product_id"));
                int quantity = Integer.parseInt(resultSet.getString("quantity"));
                float price = Float.parseFloat(resultSet.getString("price"));
                int colorId = Integer.parseInt(resultSet.getString("color_id"));
                int sizeId = Integer.parseInt(resultSet.getString("size_id"));
                String productName = resultSet.getString("product_name");
                String image = resultSet.getString("image");
                
                ColorDAO colorDAO = new ColorDAO();
                Color color = new Color();
                color = colorDAO.getColors(colorId);
                
                SizeDAO sizeDAO = new SizeDAO();
                Size size = new Size();
                size = sizeDAO.getSizes(sizeId);
                
                OrderDetailProductSummaryDTO odpsdto = new OrderDetailProductSummaryDTO(orderIdData, productId, quantity, price, color, size, productName, image);
                orderDetailList.add(odpsdto);
                
            }
            return orderDetailList;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean insertOrderDetails(OrderDetail orderDetail) {

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     INSERT INTO [dbo].[Order_Details]
                                ([order_id]
                                ,[product_id]
                                ,[color_id]
                                ,[size_id]
                                ,[price]
                                ,[quantity])
                          VALUES
                                (?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?)""";
        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set Objet
            statement.setObject(1, orderDetail.getOrderId());
            statement.setObject(2, orderDetail.getProductId());
            statement.setObject(3, orderDetail.getColorId());
            statement.setObject(4, orderDetail.getSizeId());
            statement.setObject(5, orderDetail.getPrice());
            statement.setObject(6, orderDetail.getQuantity());

            // ExecuteQuery
            int checkOrderDetail = statement.executeUpdate();
            
            return checkOrderDetail > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public List<OrderDetail> getOrderDetailsById(int orderId) {

        // List OrderDetail
        List<OrderDetail> orderDetailLists = new ArrayList<>();

        // Connect data
        connection = getConnection();

        // Systax sql
        String sql = """
                     SELECT [order_id]
                           ,[product_id]
                           ,[color_id]
                           ,[size_id]
                           ,[price]
                           ,[quantity]
                           ,[order_detailsid]
                       FROM [dbo].[Order_Details]
                       WHERE order_id = ?""";
        
        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, orderId);

            // ExecuteQuery
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                int orderIdData = Integer.parseInt(resultSet.getString("order_id"));
                int productId = Integer.parseInt(resultSet.getString("product_id"));
                int colorId = Integer.parseInt(resultSet.getString("color_id"));
                int sizeId = Integer.parseInt(resultSet.getString("size_id"));
                float price = Float.parseFloat(resultSet.getString("price"));
                int quantity = Integer.parseInt(resultSet.getString("quantity"));
                
                OrderDetail orderDetail = new OrderDetail(orderIdData, productId, colorId, sizeId, price, quantity);
                orderDetailLists.add(orderDetail);
            }
            return orderDetailLists;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean deleteOrderDetailById(int orderId) {
      
        // Connect data
        connection = getConnection();

        // Systax sql
        String sql = """
                     DELETE FROM [dbo].[Order_Details]
                           WHERE order_id = ?""";
        
        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, orderId);

            // ExecuteQuery
            int checkDeleleteOrderDetail = statement.executeUpdate();
            
            return checkDeleleteOrderDetail > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void main(String[] args) {
//        OrderDetail orderDetail = new OrderDetail(38, 29, 6, 2, 199000, 2);
//        System.out.println(new OrderDetailDAO().insertOrderDetails(orderDetail));

//        for (OrderDetail orderDetail : new OrderDetailDAO().getOrderDetailsById(17)) {
//            System.out.println(orderDetail.toString());
//        }
            System.out.println(new OrderDetailDAO().deleteOrderDetailById(16));
    }
    
}
