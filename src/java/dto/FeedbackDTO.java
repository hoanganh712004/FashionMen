/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class FeedbackDTO {
    
    private String userImage;
    private String username;
    private String subjectName;
    private Date feedbackDate;
    private String note;

    public FeedbackDTO() {
    }

    public FeedbackDTO(String userImage, String username, String subjectName, Date feedbackDate, String note) {
        this.userImage = userImage;
        this.username = username;
        this.subjectName = subjectName;
        this.feedbackDate = feedbackDate;
        this.note = note;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "FeedbackDTO{" + "userImage=" + userImage + ", username=" + username + ", subjectName=" + subjectName + ", feedbackDate=" + feedbackDate + ", note=" + note + '}';
    }
  
    
    
}
