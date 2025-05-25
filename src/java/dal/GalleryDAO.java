/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dix.dal.config.DBContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Gallery;
import model.Product;

/**
 *
 * @author ADMIN
 */
public class GalleryDAO extends DBContext {

    List<String> getGallery(int productId) {

        // Create List gallery
        List<String> galleryLists = new ArrayList<>();

        // Connect database
        connection = getConnection();

        // String sql
        String sql = """
                     SELECT [gallery_id]
                           ,[product_id]
                           ,[thumbnail]
                       FROM [dbo].[Galery]
                       WHERE product_id = ?""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set Objet
            statement.setObject(1, productId);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String thumbnail = resultSet.getString("thumbnail");

                galleryLists.add(thumbnail);
            }

            return galleryLists;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean insertGallery(Product lastProduct) {

        connection = getConnection();

        String sql = """
                     INSERT INTO [dbo].[Galery]
                                ([product_id]
                                ,[thumbnail])
                          VALUES
                                (?
                                ,?)""";

        try {
            statement = connection.prepareStatement(sql);

            statement.setObject(1, lastProduct.getProductId());
            statement.setObject(2, lastProduct.getImage());

            int checkInsertGallery = statement.executeUpdate();

            return checkInsertGallery > 0;

        } catch (Exception e) {
        }

        return false;
    }

    public boolean updateGallery(Product product) {
        connection = getConnection();

        String sql = """
                     UPDATE [dbo].[Galery]
                        SET [thumbnail] = ?
                      WHERE [product_id] = ?""";

        try {
            statement = connection.prepareStatement(sql);

            statement.setObject(1, product.getImage());
            statement.setObject(2, product.getProductId());

            int checkUpdateGallery = statement.executeUpdate();

            return checkUpdateGallery > 0;

        } catch (Exception e) {
        }

        return false;
    }

    public boolean deleteByProductid(int productId) {

        connection = getConnection();

        String sql = """
                     DELETE FROM [dbo].[Galery]
                           WHERE product_id = ?""";

        try {
            statement = connection.prepareStatement(sql);

            statement.setObject(1, productId);

            int checkDeleteByProductid = statement.executeUpdate();

            return checkDeleteByProductid > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        for (String gallery : new GalleryDAO().getGallery(16)) {
            System.out.println(gallery.toString());
        }
    }
}
