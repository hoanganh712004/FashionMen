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
public class OrderDetailProductSummaryDTO {
    
    private int orderId;
    private int productId;
    private int quantity;
    private float price;
    private Color color;
    private Size size;
    private String productName;
    private String productImage;

    public OrderDetailProductSummaryDTO(int orderId, int productId, int quantity, float price, Color color, Size size, String productName, String productImage) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.color = color;
        this.size = size;
        this.productName = productName;
        this.productImage = productImage;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    @Override
    public String toString() {
        return "OrderDetailProductSummaryDTO{" + "orderId=" + orderId + ", productId=" + productId + ", quantity=" + quantity + ", price=" + price + ", color=" + color + ", size=" + size + ", productName=" + productName + ", productImage=" + productImage + '}';
    }
    
    
}
