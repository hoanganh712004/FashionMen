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
import model.Color;
import model.OrderDetail;
import model.Product;
import model.ProductDetail;
import model.Size;
import model.Supperlier;

/**
 *
 * @author ADMIN
 */
public class ProductDetailDAO extends DBContext {

    public Product getProductOfDetailColorSize(int colorId, int sizeId, int productId) {

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = "SELECT *"
                + "FROM Product p "
                + "LEFT JOIN Comment c on p.product_id = c.product_id "
                + "WHERE p.product_id = ?";

        try {

            // Create object PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, productId);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int productIdData = Integer.parseInt(resultSet.getString("product_id"));
                int categoryId = Integer.parseInt(resultSet.getString("category_id"));
                int supperlierId = Integer.parseInt(resultSet.getString("supperlier_id"));
                String productName = resultSet.getString("product_name");
                int quantityStock = Integer.parseInt(resultSet.getString("quantity_stock"));
                int quantitySold = Integer.parseInt(resultSet.getString("quantity_sold"));
                float price = Float.parseFloat(resultSet.getString("price"));
                String desciption = resultSet.getString("desciption");
                String image = resultSet.getString("image");

                int discount = Integer.parseInt(resultSet.getString("discount") == null
                        ? "0" : resultSet.getString("discount"));

                int rating = Integer.parseInt(resultSet.getString("rating") == null
                        ? "0" : resultSet.getString("rating"));

                CategoryDAO categoryDAO = new CategoryDAO();
                Category category = categoryDAO.getCategorys(categoryId);

                SupperlierDAO supperlierDAO = new SupperlierDAO();
                Supperlier supperlier = supperlierDAO.getSupperlier(supperlierId);

                GalleryDAO galleryDAO = new GalleryDAO();
                List<String> gallery = new ArrayList<>();
                gallery = galleryDAO.getGallery(productId);

                quantityStock = getTotalProductQuantityStock(colorId, sizeId, productIdData);
                quantitySold = getTotalProductDetailSold(colorId, sizeId, productIdData);

                Product product = new Product(productIdData, category, supperlier, productName, quantityStock, quantitySold, price, desciption, image, discount, gallery, rating);

                return product;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int getTotalProductDetailSold(int colorId, int sizeId, int productIdData) {

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     SELECT COALESCE(SUM(od.quantity), 0) AS [total_productdetail_sold]
                     FROM Orders AS o
                     JOIN Order_Details AS od ON o.Order_id = od.order_id
                     WHERE od.product_id = ?
                       AND od.color_id = ?
                       AND od.size_id = ?
                       AND (o.status = 1 OR o.status = 0)""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, productIdData);
            statement.setObject(2, colorId);
            statement.setObject(3, sizeId);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int totalProductDetailSold = Integer.parseInt(resultSet.getString("total_productdetail_sold"));
                return totalProductDetailSold;
            }

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int getTotalProductQuantityStock(int colorId, int sizeId, int productIdData) {
        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     SELECT *
                     FROM Product_detail pd
                      WHERE pd.product_id = ?
                        AND pd.color_id = ?
                        AND pd.size_id = ?""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, productIdData);
            statement.setObject(2, colorId);
            statement.setObject(3, sizeId);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int quantityStock = Integer.parseInt(resultSet.getString("quantity"));
                return quantityStock;
            }

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public ProductDetail getProductDetail(int productId, int colorId, int sizeId) {

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     SELECT [product_id]
                           ,[color_id]
                           ,[size_id]
                           ,[quantity]
                       FROM [dbo].[Product_detail]
                       WHERE product_id = ? and color_id = ? and size_id = ?""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, productId);
            statement.setObject(2, colorId);
            statement.setObject(3, sizeId);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int productIdData = Integer.parseInt(resultSet.getString("product_id"));
                int corldIdData = Integer.parseInt(resultSet.getString("color_id"));
                int sizeIdData = Integer.parseInt(resultSet.getString("size_id"));
                int quantity = Integer.parseInt(resultSet.getString("quantity"));

                ColorDAO colorDAO = new ColorDAO();
                Color color = colorDAO.getColors(colorId);

                SizeDAO sizeDAO = new SizeDAO();
                Size size = sizeDAO.getSizes(sizeId);

                ProductDetail productDetail = new ProductDetail(productId, color, size, quantity);
                return productDetail;
            }

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateProductDetail(ProductDetail productDetailList, int quantityUpdate) {

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     UPDATE [dbo].[Product_detail]
                        SET [quantity] = ?
                      WHERE product_id = ? and color_id = ? and size_id = ?""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, quantityUpdate);
            statement.setObject(2, productDetailList.getProductId());
            statement.setObject(3, productDetailList.getColor().getColorId());
            statement.setObject(4, productDetailList.getSize().getSizeId());

            // ExecuteUpdate
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProductDetail2(ProductDetail productDetailList, int quantityUpdate) {

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     UPDATE [dbo].[Product_detail]
                        SET [quantity] = ?
                      WHERE product_id = ? and color_id = ? and size_id = ?""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);
            
            // Set object
            int quantityOdd = productDetailList.getQuantity();
            statement.setObject(1, quantityOdd + quantityUpdate);
            statement.setObject(2, productDetailList.getProductId());
            statement.setObject(3, productDetailList.getColor().getColorId());
            statement.setObject(4, productDetailList.getSize().getSizeId());

            // ExecuteUpdate
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ProductDetail> getAllProductDetails() {

        // List productDetailLists
        List<ProductDetail> productDetailLists = new ArrayList<>();

        // Connect data
        connection = getConnection();

        // Systax sql
        String sql = """
                     SELECT [product_id]
                           ,[color_id]
                           ,[size_id]
                           ,[quantity]
                       FROM [dbo].[Product_detail]""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int productId = Integer.parseInt(resultSet.getString("product_id"));
                int colorId = Integer.parseInt(resultSet.getString("color_id"));
                int sizeId = Integer.parseInt(resultSet.getString("size_id"));
                int quantity = Integer.parseInt(resultSet.getString("quantity"));

                ColorDAO colorDAO = new ColorDAO();
                Color color = colorDAO.getColors(colorId);

                SizeDAO sizeDAO = new SizeDAO();
                Size size = sizeDAO.getSizes(sizeId);

                ProductDetail productDetail = new ProductDetail(productId, color, size, quantity);

                productDetailLists.add(productDetail);
            }
            return productDetailLists;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

//        System.out.println(new ProductDetailDAO().getProductDetail(16, 1, 1));
        for (ProductDetail allProductDetail : new ProductDetailDAO().getAllProductDetails()) {
            System.out.println(allProductDetail.toString());
        }
    }

}
