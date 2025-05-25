/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dix.dal.config.DBContext;
import java.sql.SQLException;
import model.Payment;

/**
 *
 * @author ADMIN
 */
public class PaymentDAO extends DBContext{

    public Payment getPayment(int paymentId) {
        
        // Connect database
        connection = getConnection();
        
        // String sql
        String sql = """
                 SELECT [payment_id]
                       ,[payment_name]
                   FROM [dbo].[Payment]
                   WHere payment_id = ?""";
        
        try {
            
            // PrepareStatement
            statement = connection.prepareStatement(sql);
            
            // Set Objet
            statement.setObject(1, paymentId);
            
            // ExecuteQuery
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                int paymentIdData = Integer.parseInt(resultSet.getString("payment_id"));
                String paymentName = resultSet.getString("payment_name");
                
                Payment payment = new Payment(paymentIdData, paymentName);
                
                return payment;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static void main(String[] args) {
        System.out.println(new PaymentDAO().getPayment(3));
    }
    
}
