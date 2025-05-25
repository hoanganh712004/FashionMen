/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.Date;
import model.Product;
import model.User;

/**
 *
 * @author ADMIN
 */
public class CommentDTO {
    
    private int commentId;
    private Product product;
    private User user;
    private String content;
    private Date commentDate;
    private int rating;

    public CommentDTO(int commentId, Product product, User user, String content, Date commentDate, int rating) {
        this.commentId = commentId;
        this.product = product;
        this.user = user;
        this.content = content;
        this.commentDate = commentDate;
        this.rating = rating;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "CommentDTO{" + "commentId=" + commentId + ", product=" + product + ", user=" + user + ", content=" + content + ", commentDate=" + commentDate + ", rating=" + rating + '}';
    }
    
    

}
