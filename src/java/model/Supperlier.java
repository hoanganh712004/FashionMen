/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class Supperlier {
    
    private int supperlierId;
    private String company_name;
    private String phoneNumber;
    private String county;

    public Supperlier() {
    }

    public Supperlier(int supperlierId, String company_name, String phoneNumber, String county) {
        this.supperlierId = supperlierId;
        this.company_name = company_name;
        this.phoneNumber = phoneNumber;
        this.county = county;
    }

    public int getSupperlierId() {
        return supperlierId;
    }

    public void setSupperlierId(int supperlierId) {
        this.supperlierId = supperlierId;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Override
    public String toString() {
        return "Supperlier{" + "supperlierId=" + supperlierId + ", company_name=" + company_name + ", phoneNumber=" + phoneNumber + ", county=" + county + '}';
    }
}
