/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.List;
import model.Category;
import model.Supperlier;

/**
 *
 * @author ADMIN
 */
public class FilterProductDTO {
    
    private String[] categories;
    private float maxPrice;
    private float minPrice;
    private int orderBy;

    public FilterProductDTO() {
    }

    public FilterProductDTO(String[] categories, float maxPrice, float minPrice, int orderBy) {
        this.categories = categories;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.orderBy = orderBy;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    public int getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public String toString() {
        return "FilterProductDTO{" + "categories=" + categories + ", maxPrice=" + maxPrice + ", minPrice=" + minPrice + ", orderBy=" + orderBy + '}';
    }

    
}
