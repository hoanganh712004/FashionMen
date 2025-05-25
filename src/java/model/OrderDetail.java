/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dto.OrderDetailProductSummaryDTO;
import java.util.List;

/**
 *
 * @author PC
 */
public class OrderDetail {
    
    private int orderId;
    private int productId;
    private int colorId;
    private int sizeId;
    private float price;
    private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(int orderId, int productId, int colorId, int sizeId, float price, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.colorId = colorId;
        this.sizeId = sizeId;
        this.price = price;
        this.quantity = quantity;
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

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
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

    @Override
    public String toString() {
        return "OrderDetail{" + "orderId=" + orderId + ", productId=" + productId + ", colorId=" + colorId + ", sizeId=" + sizeId + ", price=" + price + ", quantity=" + quantity + '}';
    }

   
    
    
}
