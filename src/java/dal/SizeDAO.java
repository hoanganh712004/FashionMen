/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dix.dal.config.DBContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Order;
import model.Size;

/**
 *
 * @author ADMIN
 */
public class SizeDAO extends DBContext{

    public Size getSizes(int sizeId) {
         // Create object order
        Size size = null;
        
        // Connect Database
        connection = getConnection();
        
        // Systax sql
        String sql = """
                     SELECT [size_id]
                           ,[size_option]
                       FROM [dbo].[Size]
                       WHERE size_id = ?""";
        
        
        try {
            
            // PrepareStatement
            statement = connection.prepareStatement(sql);
            
            // Set Object
            statement.setObject(1, sizeId);
            
            // ExecuteQuery
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                int sizeIdData = Integer.parseInt(resultSet.getString("size_id"));
                String sizeName = resultSet.getString("size_option");
               
                size = new Size(sizeIdData, sizeName);
                return size;
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public List<Size> getSizesAll() {
        
         // Create size list
         List<Size> sizeLists = new ArrayList<>();
        
        // Connect Database
        connection = getConnection();
        
        // Systax sql
        String sql = """
                     SELECT [size_id]
                           ,[size_option]
                       FROM [dbo].[Size]""";
        
        
        try {
            
            // PrepareStatement
            statement = connection.prepareStatement(sql);
            
            // ExecuteQuery
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                int sizeIdData = Integer.parseInt(resultSet.getString("size_id"));
                String sizeName = resultSet.getString("size_option");
               
                Size size = new Size(sizeIdData, sizeName);
                sizeLists.add(size);
            }
            
            return sizeLists;
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static void main(String[] args) {
        for (Size size : new SizeDAO().getSizesAll()) {
            System.out.println(size.toString());
        }
    }
    
}
