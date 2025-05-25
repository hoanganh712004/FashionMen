/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.Date;
/**
 *
 * @author ADMIN
 */
public class RecentOrdersDTO {

    private int orderId;
    private String fullName;
    private Date orderDate;
    private int status;
    private int totalQuantity;
    private float totalMoney;

    public RecentOrdersDTO(int orderId, String fullName, Date orderDate, int status, int totalQuantity, float totalMoney) {
        this.orderId = orderId;
        this.fullName = fullName;
        this.orderDate = orderDate;
        this.status = status;
        this.totalQuantity = totalQuantity;
        this.totalMoney = totalMoney;
    }
    
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullname(String fullName) {
        this.fullName = fullName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return "RecentOrdersDTO{" + "orderId=" + orderId + ", fullname=" + fullName + ", orderDate=" + orderDate + ", status=" + status + ", totalQuantity=" + totalQuantity + ", totalMoney=" + totalMoney + '}';
    }
    
    
}
