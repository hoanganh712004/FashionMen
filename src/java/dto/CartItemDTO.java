/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import model.Color;
import model.Size;

/**
 *
 * @author ADMIN
 */
public class CartItemDTO {
    private int productId; 
    private String productName;
    private String itemName;
    private Size size;
    private Color color;
    private float price;
    private int quantity;
    private float totalMoney;

    public CartItemDTO() {
    }

    public CartItemDTO(int productId, String productName, String itemName, Size size, Color color, float price, int quantity, float totalMoney) {
        this.productId = productId;
        this.productName = productName;
        this.itemName = itemName;
        this.size = size;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
        this.totalMoney = totalMoney;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return "CartItemDTO{" + "productId=" + productId + ", productName=" + productName + ", itemName=" + itemName + ", size=" + size + ", color=" + color + ", price=" + price + ", quantity=" + quantity + ", totalMoney=" + totalMoney + '}';
    }
    
    
    
}
