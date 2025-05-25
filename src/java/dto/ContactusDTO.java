/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.Date;
import model.User;

/**
 *
 * @author ADMIN
 */
public class ContactusDTO {

    private User user;
    private String subjectName;
    private Date feebackDate;
    private String note;

    public ContactusDTO() {
    }

    public ContactusDTO(User user, String subjectName, Date feebackDate, String note) {
        this.user = user;
        this.subjectName = subjectName;
        this.feebackDate = feebackDate;
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Date getFeebackDate() {
        return feebackDate;
    }

    public void setFeebackDate(Date feebackDate) {
        this.feebackDate = feebackDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "FeedBackContactusDTO{" + "user=" + user + ", subjectName=" + subjectName + ", feebackDate=" + feebackDate + ", note=" + note + '}';
    }
    
    
}
