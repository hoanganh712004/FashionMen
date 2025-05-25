/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class Gallery {

    private int galleryId;
    private int productId;
    private String thumbnail;

    public Gallery() {
    }

    public Gallery(int galleryId, int productId, String thumbnail) {
        this.galleryId = galleryId;
        this.productId = productId;
        this.thumbnail = thumbnail;
    }
    
    public int getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(int galleryId) {
        this.galleryId = galleryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "Gallery{" + "galleryId=" + galleryId + ", productId=" + productId + ", thumbnail=" + thumbnail + '}';
    }
    
}
