/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author PC
 */
public class Product {

    private int productId;
    private Category category;
    private Supperlier supperlier;
    private String productName;
    private int quantityStock;
    private int quantitySold;
    private float price;
    private String desciption;
    private String image;
    private int discount;
    private List<String> thumbnails;
    private int rating;

    public Product() {
    }

    public Product(int productId, Category category, Supperlier supperlier, String productName, int quantityStock, int quantitySold, float price, String desciption, String image, int discount, List<String> thumbnails, int rating) {
        this.productId = productId;
        this.category = category;
        this.supperlier = supperlier;
        this.productName = productName;
        this.quantityStock = quantityStock;
        this.quantitySold = quantitySold;
        this.price = price;
        this.desciption = desciption;
        this.image = image;
        this.discount = discount;
        this.thumbnails = thumbnails;
        this.rating = rating;
    }

    public Product(int productId, Category category, Supperlier supperlier, String productName, int quantityStock, int quantitySold, float price, String desciption, String image, int discount, int rating) {
        this.productId = productId;
        this.category = category;
        this.supperlier = supperlier;
        this.productName = productName;
        this.quantityStock = quantityStock;
        this.quantitySold = quantitySold;
        this.price = price;
        this.desciption = desciption;
        this.image = image;
        this.discount = discount;
        this.rating = rating;
    }

    public Product(Category category, Supperlier supperlier, String productName, int quantityStock, int quantitySold, float price, String desciption, String image, int discount) {
        this.category = category;
        this.supperlier = supperlier;
        this.productName = productName;
        this.quantityStock = quantityStock;
        this.quantitySold = quantitySold;
        this.price = price;
        this.desciption = desciption;
        this.image = image;
        this.discount = discount;
    }
    
    

    public Product(int productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Supperlier getSupperlier() {
        return supperlier;
    }

    public void setSupperlier(Supperlier supperlier) {
        this.supperlier = supperlier;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantityStock() {
        return quantityStock;
    }

    public void setQuantityStock(int quantityStock) {
        this.quantityStock = quantityStock;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public List<String> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(List<String> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", category=" + category + ", supperlier=" + supperlier + ", productName=" + productName + ", quantityStock=" + quantityStock + ", quantitySold=" + quantitySold + ", price=" + price + ", desciption=" + desciption + ", image=" + image + ", discount=" + discount + ", thumbnails=" + thumbnails + ", rating=" + rating + '}';
    }

}
