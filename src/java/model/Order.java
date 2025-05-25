/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author PC
 */
public class Order {
    
    private int orderId;
    private int userId;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String address;
    private Date orderDate;
    private String note;
    private int paymentId;
    private int status;
    private float totalMoney;

    public Order() {
    }

    public Order(int orderId, int userId, String fullName, String phoneNumber, String email, String address, Date orderDate, String note, int paymentId, int status, float totalMoney) {
        this.orderId = orderId;
        this.userId = userId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.orderDate = orderDate;
        this.note = note;
        this.paymentId = paymentId;
        this.status = status;
        this.totalMoney = totalMoney;
    }

    public Order(int userId, String fullName, String phoneNumber, String email, String address, Date orderDate, String note, int paymentId, int status, float totalMoney) {
        this.userId = userId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.orderDate = orderDate;
        this.note = note;
        this.paymentId = paymentId;
        this.status = status;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", userId=" + userId + ", fullName=" + fullName + ", phoneNumber=" + phoneNumber + ", email=" + email + ", address=" + address + ", orderDate=" + orderDate + ", note=" + note + ", paymentId=" + paymentId + ", status=" + status + ", totalMoney=" + totalMoney + '}';
    }

    
}
