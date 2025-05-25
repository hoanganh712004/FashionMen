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
public class User {

    private int userId;
    private int roleId;
    private String userName;
    private String passWord;
    private String fullName;
    private Date birthDay;
    private String image;
    private String phoneNumber;
    private String address;
    private String email;
    private Date createdAt;


    public User() {
    }

    public User(int userId, int roleId, String userName, String passWord, String fullName, Date birthDay, String image, String phoneNumber, String address, String email, Date createdAt) {
        this.userId = userId;
        this.roleId = roleId;
        this.userName = userName;
        this.passWord = passWord;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.image = image;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.createdAt = createdAt;
    }

    public User(int roleId, String userName, String passWord, String fullName, Date birthDay, String image, String phoneNumber, String address, String email, Date createdAt) {
        this.roleId = roleId;
        this.userName = userName;
        this.passWord = passWord;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.image = image;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.createdAt = createdAt;
    }
    
    

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", roleId=" + roleId + ", userName=" + userName + ", passWord=" + passWord + ", fullName=" + fullName + ", birthDay=" + birthDay + ", image=" + image + ", phoneNumber=" + phoneNumber + ", address=" + address + ", email=" + email + ", createdAt=" + createdAt + '}';
    }

    
    
}
