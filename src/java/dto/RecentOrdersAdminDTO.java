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
public class RecentOrdersAdminDTO {

    private int orderId;
    private int userId;
    private String fullName;
    private Date orderDate;
    private int status;
    private int totalQuantity;
    private float totalMoney;

    public RecentOrdersAdminDTO() {
    }
   
    public RecentOrdersAdminDTO(int orderId, int userId, String fullName, Date orderDate, int status, int totalQuantity, float totalMoney) {
        this.orderId = orderId;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
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
        return "RecentOrdersAdminDTO{" + "orderId=" + orderId + ", userId=" + userId + ", fullName=" + fullName + ", orderDate=" + orderDate + ", status=" + status + ", totalQuantity=" + totalQuantity + ", totalMoney=" + totalMoney + '}';
    }
    
    
    
}
