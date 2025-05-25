/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author ADMIN
 */
public class TopCustomAdminDTO {
    
    private int userId;
    private String image;
    private String fullName;
    private String email;
    private float totalMoney;

    public TopCustomAdminDTO() {
    }

    public TopCustomAdminDTO(int userId, String image, String fullName, String email, float totalMoney) {
        this.userId = userId;
        this.image = image;
        this.fullName = fullName;
        this.email = email;
        this.totalMoney = totalMoney;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return "TopCustomAdminDTO{" + "userId=" + userId + ", image=" + image + ", fullName=" + fullName + ", email=" + email + ", totalMoney=" + totalMoney + '}';
    }
}
