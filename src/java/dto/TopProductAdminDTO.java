/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author ADMIN
 */
public class TopProductAdminDTO {
    
    private int productId;
    private String image;
    private String productName;
    private int quantitySold;
    private float price;
    private String desciption;

    public TopProductAdminDTO() {
    }
    
    public TopProductAdminDTO(int productId, String image, String productName, int quantitySold, float price, String desciption) {
        this.productId = productId;
        this.image = image;
        this.productName = productName;
        this.quantitySold = quantitySold;
        this.price = price;
        this.desciption = desciption;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    @Override
    public String toString() {
        return "TopProductAdminDTO{" + "productId=" + productId + ", image=" + image + ", productName=" + productName + ", quantitySold=" + quantitySold + ", price=" + price + ", desciption=" + desciption + '}';
    }
    
    
  
    
}
