/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author ADMIN
 */
public class Top3ProductByCategoryDTO {
    
    private int categoryId;
    private String categoryName;
    private int productId;
    private String productName;

    public Top3ProductByCategoryDTO() {
    }

    public Top3ProductByCategoryDTO(int categoryId, String categoryName, int productId, String productName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.productId = productId;
        this.productName = productName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }   

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "Top3ProductByCategory{" + "categoryId=" + categoryId + ", categoryName=" + categoryName + ", productId=" + productId + ", productName=" + productName + '}';
    }
    
}
