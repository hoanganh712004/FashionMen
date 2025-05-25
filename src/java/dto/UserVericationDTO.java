/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author ADMIN
 */
public class UserVericationDTO {
    private int id;
    private String image;
    private String fullName;
    private String newPassword;
    private String email;
    private String birthday;
    private String address;
    private String phoneNumber;
    private String verificationCode;

    public UserVericationDTO() {
    }

    public UserVericationDTO(int id, String image, String fullName, String newPassword, String email, String birthday, String address, String phoneNumber, String verificationCode) {
        this.id = id;
        this.image = image;
        this.fullName = fullName;
        this.newPassword = newPassword;
        this.email = email;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.verificationCode = verificationCode;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public String toString() {
        return "UserVericationDTO{" + "id=" + id + ", image=" + image + ", fullName=" + fullName + ", newPassword=" + newPassword + ", email=" + email + ", birthday=" + birthday + ", address=" + address + ", phoneNumber=" + phoneNumber + ", verificationCode=" + verificationCode + '}';
    }
    
    
}
