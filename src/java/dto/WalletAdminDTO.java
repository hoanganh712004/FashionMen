/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author ADMIN
 */
public class WalletAdminDTO {
    
    private int walletId;
    private int userId;
    private String image;
    private String userName;
    private float balance;

    public WalletAdminDTO() {
    }

    public WalletAdminDTO(int walletId, int userId, String image, String userName, float balance) {
        this.walletId = walletId;
        this.userId = userId;
        this.image = image;
        this.userName = userName;
        this.balance = balance;
    }

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
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

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "WalletAdminDTO{" + "walletId=" + walletId + ", userId=" + userId + ", image=" + image + ", userName=" + userName + ", balance=" + balance + '}';
    }
    
    
}
