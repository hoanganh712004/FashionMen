/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

/**
 *
 * @author PC
 */
public class Category {

    private int categoryId;
    private String categoryName;
    private String desciption;
    private String image;

    public Category() {
    }

    public Category(int categoryId, String categoryName, String desciption, String image) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.desciption = desciption;
        this.image = image;
    }

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Category(String categoryName, String desciption, String image) {
        this.categoryName = categoryName;
        this.desciption = desciption;
        this.image = image;
    }
    
    

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Category category = (Category) obj;
        return categoryId == category.categoryId; // So sánh chỉ dựa trên categoryId
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId); // 1 lần (mỗi danh mục chỉ tạo một lần)
    }

    @Override
    public String toString() {
        return "Category{" + "categoryId=" + categoryId + ", categoryName=" + categoryName + ", desciption=" + desciption + ", image=" + image + '}';
    }

    public boolean deleteCategory(int categoryId1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
