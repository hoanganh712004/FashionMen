/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class Size {

    private int sizeId;
    private String sizeOption;

    public Size() {
    }

    public Size(int sizeId, String sizeOption) {
        this.sizeId = sizeId;
        this.sizeOption = sizeOption;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public String getSizeOption() {
        return sizeOption;
    }

    public void setSizeOption(String sizeOption) {
        this.sizeOption = sizeOption;
    }

    @Override
    public String toString() {
        return "Size{" + "sizeId=" + sizeId + ", sizeOption=" + sizeOption + '}';
    }
}
