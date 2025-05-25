/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.Date;
import model.Role;

/**
 *
 * @author ADMIN
 */
public class UserListAdminDTO {

    private int userId;
    private String image;
    private String userName;
    private String email;
    private String phoneNumber;
    private int totalBuy;
    private Role role;
    private Date createdAt; 

    public UserListAdminDTO() {
    }

    public UserListAdminDTO(int userId, String image, String userName, String email, String phoneNumber, int totalBuy, Role role, Date createdAt) {
        this.userId = userId;
        this.image = image;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.totalBuy = totalBuy;
        this.role = role;
        this.createdAt = createdAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getTotalBuy() {
        return totalBuy;
    }

    public void setTotalBuy(int totalBuy) {
        this.totalBuy = totalBuy;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "UserListAdminDTO{" + "userId=" + userId + ", image=" + image + ", userName=" + userName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", totalBuy=" + totalBuy + ", role=" + role + ", createdAt=" + createdAt + '}';
    }
    
    
}
