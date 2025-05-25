/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class ProductDetail {
    private int productId;
    private Color color;
    private Size size;
    private int quantity;

    public ProductDetail() {
    }

    public ProductDetail(int productId, Color color, Size size, int quantity) {
        this.productId = productId;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductDetail{" + "productId=" + productId + ", color=" + color + ", size=" + size + ", quantity=" + quantity + '}';
    }
    
}
