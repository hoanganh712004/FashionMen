/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dix.dal.config.DBContext;
import dto.FilterProductDTO;
import dto.Top3ProductByCategoryDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.Category;
import model.Gallery;
import model.Product;
import model.Supperlier;

/**
 *
 * @author ADMIN
 */
public class ProductDAO extends DBContext {

    public List<Product> getProducts(String keyword) {

        // Create product list
        List<Product> productLists = new ArrayList<>();

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     SELECT p.product_id,
                     \t   p.category_id,
                     \t   p.supperlier_id,
                     \t   p.product_name,
                     \t   p.quantity_stock,
                     \t   p.quantity_sold,
                     \t   p.price,
                     \t   p.desciption,
                     \t   p.image,
                     \t   p.discount,
                     \t   c.rating
                     FROM Product p
                     LEFT JOIN Comment c on p.product_id = c.product_id 
                     WHERE p.product_name LIKE ?""";

        try {

            // Create object PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, "%" + keyword + "%");

            // ExecuteQuery
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int productId = Integer.parseInt(resultSet.getString("product_id"));
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

                Product product = new Product(productId, category, supperlier, productName, quantityStock, quantitySold, price, desciption, image, discount, gallery, rating);

                productLists.add(product);
            }
            return productLists;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Product> getProductsAll() {

        // Create product list
        List<Product> productLists = new ArrayList<>();

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     SELECT p.product_id,
                     \t   p.category_id,
                     \t   p.supperlier_id,
                     \t   p.product_name,
                     \t   p.quantity_stock,
                     \t   p.quantity_sold,
                     \t   p.price,
                     \t   p.desciption,
                     \t   p.image,
                     \t   p.discount,
                     \t   c.rating
                     FROM Product p
                     LEFT JOIN Comment c on p.product_id = c.product_id""";

        try {

            // Create object PrepareStatement
            statement = connection.prepareStatement(sql);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int productId = Integer.parseInt(resultSet.getString("product_id"));
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

                Product product = new Product(productId, category, supperlier, productName, quantityStock, quantitySold, price, desciption, image, discount, gallery, rating);

                productLists.add(product);
            }
            return productLists;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Product> getFilterProduct(FilterProductDTO filterProductDTO) {
        // Create product list
        List<Product> productLists = new ArrayList<>();

        connection = getConnection();

        String sql = """
                     SELECT p.product_id,
                     \t\tp.category_id,
                     \t\tp.supperlier_id,
                     \t\tp.product_name,
                     \t\tp.quantity_stock,
                     \t\tp.quantity_sold,
                     \t\tp.desciption,
                     \t\tp.price,
                     \t\tp.image,
                     \t\tp.discount,
                     \t\tAVG(COALESCE(c.rating, 0)) AS rating
                     FROM Product p
                     LEFT JOIN Comment c ON p.product_id = c.product_id
                     WHERE 1 = 1"""; //  Giữ WHERE 1=1 để tránh lỗi cú pháp khi không có điều kiện nào

        if (filterProductDTO.getCategories() != null && filterProductDTO.getCategories()[0].compareTo("0") != 0) {
            sql += " and ( ";
            for (int i = 0; i < filterProductDTO.getCategories().length; i++) {
                if (i == 0) {
                    sql += " category_id = " + filterProductDTO.getCategories()[i];
                } else {
                    sql += " or category_id = " + filterProductDTO.getCategories()[i];
                }

            }
            sql += " )";
        }

        sql += " and ( price between " + filterProductDTO.getMinPrice() + " and " + filterProductDTO.getMaxPrice() + ") ";

        if (filterProductDTO.getOrderBy() == 3) {
            sql += "and c.rating > 0 ";
        }

        sql += """
                GROUP BY p.product_id,p.category_id, p.supperlier_id,p.product_name,
                \t\tp.quantity_stock, p.quantity_sold, p.desciption,
                \t\tp.price, p.image, p.discount""";

        switch (filterProductDTO.getOrderBy()) {
            case 1:
                sql += " Order by price asc";
                break;
            case 2:
                sql += " Order by price desc";
                break;

            default:
                break;
        }

//        System.out.println(sql);
        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int productId = Integer.parseInt(resultSet.getString("product_id"));
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

                Product product = new Product(productId, category, supperlier, productName, quantityStock, quantitySold, price, desciption, image, discount, rating);

                productLists.add(product);

            }
            return productLists;
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<Category, List<Product>> getTop3ProductByCategory() {
        Map<Category, List<Product>> top3ProductByCategory = new LinkedHashMap<>();
        connection = getConnection();

        String sql = """
                 SELECT c.category_id, c.category_name, p.product_id, p.product_name
                 FROM Category c
                 CROSS APPLY (
                     SELECT TOP 3 * 
                     FROM Product p
                     WHERE p.category_id = c.category_id
                     ORDER BY quantity_sold DESC
                 ) AS p;
                 """;

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int categoryId = resultSet.getInt("category_id");
                String categoryName = resultSet.getString("category_name");
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");

                Category category = new Category(categoryId, categoryName);

                Product product = new Product(productId, productName);

                // Đảm bảo danh mục có danh sách sản phẩm
                top3ProductByCategory.putIfAbsent(category, new ArrayList<>());

                // Chỉ thêm sản phẩm nếu danh sách chưa đủ 3 sản phẩm
                if (top3ProductByCategory.get(category).size() < 3) {
                    top3ProductByCategory.get(category).add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi để dễ debug
        }

        return top3ProductByCategory;
    }

    public Product getProductOfDetail(int pid) {

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
            statement.setObject(1, pid);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int productId = Integer.parseInt(resultSet.getString("product_id"));
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

                Product product = new Product(productId, category, supperlier, productName, quantityStock, quantitySold, price, desciption, image, discount, gallery, rating);

                return product;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Product getProductId(int productId) {

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     SELECT p.product_id,
                     \t   p.category_id,
                     \t   p.supperlier_id,
                     \t   p.product_name,
                     \t   p.quantity_stock,
                     \t   p.quantity_sold,
                     \t   p.price,
                     \t   p.desciption,
                     \t   p.image,
                     \t   p.discount,
                     \t   c.rating
                     FROM Product p
                     LEFT JOIN Comment c on p.product_id = c.product_id 
                     WHERE p.product_id LIKE ?""";

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

                Product product = new Product(productIdData, category, supperlier, productName, quantityStock, quantitySold, price, desciption, image, discount, gallery, rating);

                return product;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Map<Category, List<Product>> top6NewProductByCategory() {

        // Create Map top6NewProductByCategory
        Map<Category, List<Product>> map = new LinkedHashMap<>();

        // Connect data
        connection = getConnection();

        // Create list category
        List<Category> categoryLists = new ArrayList<>();
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryLists = categoryDAO.getCategorysAll();
        for (Category categoryList : categoryLists) {

            // Create list product 6 new
            List<Product> top6ProductsList = new ArrayList<>();

            // Systax sql
            String sql = """
                          SELECT TOP 6 p.product_id,
                          p.category_id,
                          p.supperlier_id,
                          p.product_name,
                          p.quantity_stock,
                          p.quantity_sold,
                          p.desciption,
                          p.price,
                          p.image,
                          p.discount,
                          AVG(COALESCE(c.rating, 0)) AS rating
                          FROM Product p
                          LEFT JOIN Comment c ON p.product_id = c.product_id
                            \t\t\t\t\t WHERE 1 = 1""";

            sql += "and p.category_id =" + categoryList.getCategoryId();

            sql += """
                   GROUP BY p.product_id,p.category_id, p.supperlier_id,p.product_name,
                                        p.quantity_stock, p.quantity_sold, p.desciption,
                                        p.price, p.image, p.discount""";

            sql += " ORDER BY p.product_id desc";

            try {

                // PrepareStatement
                statement = connection.prepareStatement(sql);

                // ExecuteQuery
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    int productId = Integer.parseInt(resultSet.getString("product_id"));
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

                    Category category = categoryDAO.getCategorys(categoryId);

                    SupperlierDAO supperlierDAO = new SupperlierDAO();
                    Supperlier supperlier = supperlierDAO.getSupperlier(supperlierId);

                    Product product = new Product(productId, category, supperlier, productName, quantityStock, quantitySold, price, desciption, image, discount, rating);

                    top6ProductsList.add(product);
                }

                map.put(categoryList, top6ProductsList);
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
            }

//             System.out.println(sql);
        }
        return map;
    }

    public List<Product> getTop6NewProduct() {

        // Create listTop6NewProductAll
        List<Product> listTop6NewProductAll = new ArrayList<>();

        // Connect data
        connection = getConnection();

        // Systax sql
        String sql = """
                      SELECT TOP 6 p.product_id,
                                          p.category_id,
                                          p.supperlier_id,
                                          p.product_name,
                                          p.quantity_stock,
                                          p.quantity_sold,
                                          p.desciption,
                                          p.price,
                                          p.image,
                                          p.discount,
                                          AVG(COALESCE(c.rating, 0)) AS rating
                                          FROM Product p
                                          LEFT JOIN Comment c ON p.product_id = c.product_id
                                          GROUP BY p.product_id,p.category_id, p.supperlier_id,p.product_name,
                                          p.quantity_stock, p.quantity_sold, p.desciption,
                                          p.price, p.image, p.discount
                                          ORDER BY p.product_id desc""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int productId = Integer.parseInt(resultSet.getString("product_id"));
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

                Product product = new Product(productId, category, supperlier, productName, quantityStock, quantitySold, price, desciption, image, discount, rating);

                listTop6NewProductAll.add(product);
            }

            return listTop6NewProductAll;

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Product> getTop10ProductBestSelling() {

        // Create listTop6NewProductAll
        List<Product> top10ProductBestSelling = new ArrayList<>();

        // Connect data
        connection = getConnection();

        // Systax
        String sql = """
                     SELECT TOP 10 p.product_id,
                     \t\tp.category_id,
                     \t\tp.supperlier_id,
                     \t\tp.product_name,
                     \t\tp.quantity_stock,
                     \t\tp.quantity_sold,
                     \t\tp.desciption,
                     \t\tp.price,
                     \t\tp.image,
                     \t\tp.discount,
                     \t\tAVG(COALESCE(c.rating, 0)) AS rating
                     FROM Product p
                     LEFT JOIN Comment c ON p.product_id = c.product_id
                     GROUP BY p.product_id,p.category_id, p.supperlier_id,p.product_name,
                     \t\tp.quantity_stock, p.quantity_sold, p.desciption,
                     \t\tp.price, p.image, p.discount
                     ORDER BY p.quantity_sold desc""";
        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int productId = Integer.parseInt(resultSet.getString("product_id"));
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

                Product product = new Product(productId, category, supperlier, productName, quantityStock, quantitySold, price, desciption, image, discount, rating);

                top10ProductBestSelling.add(product);
            }

            return top10ProductBestSelling;

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Product> getCategoryRelatedProduct(int categoryIdRelatedProduct) {

        // Create list categoryRelatedProduct
        List<Product> categoryRelatedProduct = new ArrayList<>();

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     SELECT p.product_id,
                     \t\tp.category_id,
                     \t\tp.supperlier_id,
                     \t\tp.product_name,
                     \t\tp.quantity_stock,
                     \t\tp.quantity_sold,
                     \t\tp.desciption,
                     \t\tp.price,
                     \t\tp.image,
                     \t\tp.discount,
                     \t\tAVG(COALESCE(c.rating, 0)) AS rating
                     FROM Product p
                     LEFT JOIN Comment c ON p.product_id = c.product_id
                     WHERE p.category_id = ?
                     GROUP BY p.product_id,p.category_id, p.supperlier_id,p.product_name,
                     p.quantity_stock, p.quantity_sold, p.desciption,
                     p.price, p.image, p.discount ORDER BY p.product_id desc""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, categoryIdRelatedProduct);

            // ExecuteQuery
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int productId = Integer.parseInt(resultSet.getString("product_id"));
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

                Product product = new Product(productId, category, supperlier, productName, quantityStock, quantitySold, price, desciption, image, discount, rating);
                categoryRelatedProduct.add(product);
            }

            return categoryRelatedProduct;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateProductQuantity(Product product) {

        // Connect data
        connection = getConnection();

        // Systax sql
        String sql = """
                     UPDATE [dbo].[Product]
                        SET [quantity_stock] = ?, 
                            [quantity_sold] = ? 
                      WHERE product_id = ?""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, product.getQuantityStock());
            statement.setObject(2, product.getQuantitySold());
            statement.setObject(3, product.getProductId());

            int checkUpdateProduct = statement.executeUpdate();

            return checkUpdateProduct > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Product getProductById(int productId) {

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     SELECT p.product_id,
                     \t   p.category_id,
                     \t   p.supperlier_id,
                     \t   p.product_name,
                     \t   p.quantity_stock,
                     \t   p.quantity_sold,
                     \t   p.price,
                     \t   p.desciption,
                     \t   p.image,
                     \t   p.discount,
                     \t   c.rating
                     FROM Product p
                     LEFT JOIN Comment c on p.product_id = c.product_id
                     WHERE p.product_id = ?""";

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

                Product product = new Product(productIdData, category, supperlier, productName, quantityStock, quantitySold, price, desciption, image, discount, gallery, rating);

                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void main(String[] args) {
//        ProductDAO productDAO = new ProductDAO();
//        Map<Category, List<Product>> categoryMap = productDAO.getTop3ProductByCategory();
//
//        for (Map.Entry<Category, List<Product>> entry : categoryMap.entrySet()) {
//            System.out.println("📌 Danh mục: " + entry.getKey().getCategoryName());
//
//            for (Product product : entry.getValue()) {
//                System.out.println("   🔹 Sản phẩm: " + product.getProductName());
//            }
//
//            System.out.println("------------");
//        }
//        System.out.println(new ProductDAO().getProductId(95).toString());

//        for (Product product : new ProductDAO().getTop6NewProduct()) {
//            System.out.println(product.toString());
//        }
//        ProductDAO productDAO = new ProductDAO();
//        Map<Category, List<Product>> categoryMap = productDAO.top6NewProductByCategory();
//
//        for (Map.Entry<Category, List<Product>> entry : categoryMap.entrySet()) {
//            System.out.println("📌 Danh mục: " + entry.getKey().getCategoryName());
//
//            for (Product product : entry.getValue()) {
//                System.out.println("   🔹 Sản phẩm: " + product.getProductName());
//            }
//
//            System.out.println("------------");
//        }
//        for (Product product : new ProductDAO().getCategoryRelatedProduct(1)) {
//            System.out.println(product.toString());
//        }
//        Product product = new ProductDAO().getProductOfDetail(29);
//        System.out.println(product);
//        Product product = new Product();
//        
//        
//        ProductDAO productDAO = new ProductDAO();
//        System.out.println(productDAO.updateProductQuantity(product));
        for (Product product : new ProductDAO().findByPage(1)) {
            System.out.println(product.toString());
        }
    }

    public boolean insertProductAll(Product product) {

        // Create list categoryRelatedProduct
        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     INSERT INTO [dbo].[Product]
                                ([category_id]
                                ,[supperlier_id]
                                ,[product_name]
                                ,[quantity_stock]
                                ,[quantity_sold]
                                ,[price]
                                ,[desciption]
                                ,[image]
                                ,[discount])
                          VALUES
                                (?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?,?
                                ,?
                                ,?)""";
        
        

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, product.getCategory().getCategoryId());
            statement.setObject(2, product.getSupperlier().getSupperlierId());
            statement.setObject(3, product.getProductName());
            statement.setObject(4, product.getQuantityStock());
            statement.setObject(5, product.getQuantitySold());
            statement.setObject(6, product.getPrice());
            statement.setObject(7, product.getDesciption());
            statement.setObject(8, product.getImage());
            statement.setObject(9, product.getDiscount());

            // ExecuteQuery
            int checkInsertProduct = statement.executeUpdate();

            return checkInsertProduct > 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateProduct(Product product) {

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     UPDATE [dbo].[Product]
                        SET [category_id] = ?
                           ,[supperlier_id] = ?
                           ,[product_name] = ?
                           ,[quantity_stock] = ?
                           ,[quantity_sold] = ?
                           ,[price] = ?
                           ,[desciption] = ?
                           ,[image] = ?
                           ,[discount] = ?
                      WHERE product_id = ?""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, product.getCategory().getCategoryId());
            statement.setObject(2, product.getSupperlier().getSupperlierId());
            statement.setObject(3, product.getProductName());
            statement.setObject(4, product.getQuantityStock());
            statement.setObject(5, product.getQuantitySold());
            statement.setObject(6, product.getPrice());
            statement.setObject(7, product.getDesciption());
            statement.setObject(8, product.getImage());
            statement.setObject(9, product.getDiscount());
            statement.setObject(10, product.getProductId());

            // ExecuteQuery
            int checkUpdateProduct = statement.executeUpdate();

            return checkUpdateProduct > 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteProduct(int productId) {

        // Connect database
        connection = getConnection();

        // Systax sql
        String sql = """
                     DELETE FROM [dbo].[Product]
                           WHERE product_id = ?""";

        try {

            // PrepareStatement
            statement = connection.prepareStatement(sql);

            // Set object
            statement.setObject(1, productId);

            // ExecuteQuery
            int checkDeleteProduct = statement.executeUpdate();

            return checkDeleteProduct > 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int findTotalRecord() {

        connection = getConnection();

        String sql = """
                     SELECT COUNT(*) as Count_product
                       FROM [dbo].[Product]""";

        try {
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int countProduct = Integer.parseInt(resultSet.getString("Count_product"));
                return countProduct;
            }
        } catch (Exception e) {
        }

        return 0;
    }

    public List<Product> findByPage(int page) {

        // Create product list
        List<Product> productLists = new ArrayList<>();
        
        connection = getConnection();

        String sql = """
                     SELECT p.product_id,
                      p.category_id,
                      p.supperlier_id,
                      p.product_name,
                      p.quantity_stock,
                      p.quantity_sold,
                      p.desciption,
                      p.price,
                      p.image,
                      p.discount,
                      AVG(COALESCE(c.rating, 0)) AS rating
                      FROM Product p
                      LEFT JOIN Comment c ON p.product_id = c.product_id
                      GROUP BY p.product_id,p.category_id, p.supperlier_id,p.product_name,
                      p.quantity_stock, p.quantity_sold, p.desciption,
                      p.price, p.image, p.discount
                      ORDER BY product_id
                      OFFSET ? ROWS
                      FETCH NEXT 9 ROWS ONLY""";

//        System.out.println(sql);
        try {

            statement = connection.prepareStatement(sql);

            statement.setObject(1, (page - 1) * 9);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int productId = Integer.parseInt(resultSet.getString("product_id"));
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

                Product product = new Product(productId, category, supperlier, productName, quantityStock, quantitySold, price, desciption, image, discount, rating);

                productLists.add(product);
            }
            return productLists;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
