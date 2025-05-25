/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Page {

    private int totalPage;
    private int page;
    private String urlPattern;

    public Page() {
    }

    public Page(int totalPage, int page, String urlPattern) {
        this.totalPage = totalPage;
        this.page = page;
        this.urlPattern = urlPattern;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    @Override
    public String toString() {
        return "Page{" + "totalPage=" + totalPage + ", page=" + page + ", urlPattern=" + urlPattern + '}';
    }

    
    
}
