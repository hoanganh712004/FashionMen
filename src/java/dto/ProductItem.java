/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author ADMIN
 */
public class ProductItem {
    
    private int categoryId;
    private int totalProuduct;

    public ProductItem() {
    }

    public ProductItem(int categoryId, int totalProuduct) {
        this.categoryId = categoryId;
        this.totalProuduct = totalProuduct;
    }
    
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getTotalProuduct() {
        return totalProuduct;
    }

    public void setTotalProuduct(int totalProuduct) {
        this.totalProuduct = totalProuduct;
    }
   
    
}
