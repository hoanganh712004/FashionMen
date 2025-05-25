/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dix.dal.config.DBContext;
import dto.ProductItem;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Payment;

/**
 *
 * @author ADMIN
 */
public class CategoryDAO extends DBContext {

    public Category getCategorys(int categoryId) {

        // Connect database
        connection = getConnection();

        // String sql
        String sql = """
                     SELECT [category_id]
                           ,[category_name]
                           ,[Desciption]
                           ,[image]
                       FROM [dbo].[Category]
                       WHERE category_id = ?""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set Objet
            statement.setObject(1, categoryId);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int categoryIdData = Integer.parseInt(resultSet.getString("category_id"));
                String categoryName = resultSet.getString("category_name");
                String Desciption = resultSet.getString("Desciption");
                String image = resultSet.getString("image");

                Category category = new Category(categoryIdData, categoryName, Desciption, image);
                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Category> getCategorysAll() {

        // Create list Category
        List<Category> categoryLists = new ArrayList<>();

        // Connect database
        connection = getConnection();

        // String sql
        String sql = """
                     SELECT [category_id]
                           ,[category_name]
                           ,[Desciption]
                           ,[image]
                       FROM [dbo].[Category]""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int categoryIdData = Integer.parseInt(resultSet.getString("category_id"));
                String categoryName = resultSet.getString("category_name");
                String Desciption = resultSet.getString("Desciption");
                String image = resultSet.getString("image");

                Category category = new Category(categoryIdData, categoryName, Desciption, image);
                categoryLists.add(category);
            }
            return categoryLists;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<ProductItem> getProductItems() {

        // Create product items
        List<ProductItem> productItemLists = new ArrayList<>();

        // Connect database
        connection = getConnection();

        // String sql
        String sql = """
                     SELECT p.category_id, COUNT(p.product_id) AS total_products
                     FROM Product p
                     GROUP BY p.category_id""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int categoryId = Integer.parseInt(resultSet.getString("category_id"));
                int totalProuduct = Integer.parseInt(resultSet.getString("total_products"));

                ProductItem productItem = new ProductItem(categoryId, totalProuduct);
                productItemLists.add(productItem);
            }
            return productItemLists;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean insertCategory(Category category) {

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                      INSERT INTO [dbo].[Category] ([Category_name],[Desciption],[image])
                            VALUES (?,?,?)""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, category.getCategoryName());
            statement.setObject(2, category.getDesciption());
            statement.setObject(3, category.getImage());

            // ExecuteQuery
            int checkInsertCategory = statement.executeUpdate();

            return checkInsertCategory > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateCategory(Category category) {

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     UPDATE [dbo].[Category]
                        SET [category_name] = ?
                           ,[Desciption] = ?
                           ,[image] = ?
                      WHERE category_id = ?""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, category.getCategoryName());
            statement.setObject(2, category.getDesciption());
            statement.setObject(3, category.getImage());
            statement.setObject(4, category.getCategoryId());

            // ExecuteQuery
            int checkUpdateCategory = statement.executeUpdate();

            return checkUpdateCategory > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCategory(int categoryId1) {
        
        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     DELETE FROM [dbo].[Category]
                           WHERE category_id = ?""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, categoryId1);

            // ExecuteQuery
            int checkdeleteCategory = statement.executeUpdate();

            return checkdeleteCategory > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        for (ProductItem productItem : new CategoryDAO().getProductItems()) {
            System.out.println(productItem.toString());
        }
    }
}
