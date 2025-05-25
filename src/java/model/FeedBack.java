/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author PC
 */
public class FeedBack {
    
    private int feedbackId;
    private User user;
    private String subjectName;
    private String note;
    private Date feedbackDate;

    public FeedBack() {
    }

    public FeedBack(int feedbackId, User user, String subjectName, String note) {
        this.feedbackId = feedbackId;
        this.user = user;
        this.subjectName = subjectName;
        this.note = note;
    }

    public FeedBack(int feedbackId, User user, String subjectName, String note, Date feedbackDate) {
        this.feedbackId = feedbackId;
        this.user = user;
        this.subjectName = subjectName;
        this.note = note;
        this.feedbackDate = feedbackDate;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    @Override
    public String toString() {
        return "FeedBack{" + "feedbackId=" + feedbackId + ", user=" + user + ", subjectName=" + subjectName + ", note=" + note + ", feedbackDate=" + feedbackDate + '}';
    }

}
