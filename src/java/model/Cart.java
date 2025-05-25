/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author PC
 */
public class Cart {

    private List<Product> product;
    private List<Size> size;
    private List<Color> color;
    private List<Integer> soLuong;
    private double totalPriceBeforeDiscount;
    private double totalPriceAfterDiscount;

    public Cart() {
    }

    public Cart(List<Product> product, List<Size> size, List<Color> color, List<Integer> soLuong) {
        this.product = product;
        this.size = size;
        this.color = color;
        this.soLuong = soLuong;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public List<Size> getSize() {
        return size;
    }

    public void setSize(List<Size> size) {
        this.size = size;
    }

    public List<Color> getColor() {
        return color;
    }

    public void setColor(List<Color> color) {
        this.color = color;
    }

    public List<Integer> getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(List<Integer> soLuong) {
        this.soLuong = soLuong;
    }

    public void setTotalPriceBeforeDiscount() {
        double total = 0;
        for (int i = 0; i < product.size(); i++) {
            double price = product.get(i).getPrice();
            int discount = product.get(i).getDiscount();
            double giaSanPhamTruocDiscount = price + (price * discount / 100);
            int sl = soLuong.get(i);
            total += giaSanPhamTruocDiscount * sl;
        }
        totalPriceBeforeDiscount = total;
    }

    public void setTotalPriceAfterDiscount() {
        double total = 0;
        for (int i = 0; i < product.size(); i++) {
            double price = product.get(i).getPrice();
            double giaSanPhamSauDiscount = price;
            int sl = soLuong.get(i);
            total += giaSanPhamSauDiscount * sl;
        }
        totalPriceAfterDiscount = total;
    }

    public double getTotalPriceBeforeDiscount() {
        return totalPriceBeforeDiscount;
    }

    public double getTotalPriceAfterDiscount() {
        return totalPriceAfterDiscount;
    }
    
    
    

}
