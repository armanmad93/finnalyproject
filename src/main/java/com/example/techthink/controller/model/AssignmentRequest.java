package com.example.techthink.controller.model;

import java.util.Date;

public class AssignmentRequest {
    private String name;
    private String text;
    private Date dueDate;
    private String assignmentFileURL;
    private Long sectionID;

    public AssignmentRequest() {
    }

    public AssignmentRequest(String name, String text, Date dueDate, String assignmentFileURL, Long sectionID) {
        this.name = name;
        this.text = text;
        this.dueDate = dueDate;
        this.assignmentFileURL = assignmentFileURL;
        this.sectionID = sectionID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getAssignmentFileURL() {
        return assignmentFileURL;
    }

    public void setAssignmentFileURL(String assignmentFileURL) {
        this.assignmentFileURL = assignmentFileURL;
    }

    public Long getSectionID() {
        return sectionID;
    }

    public void setSectionID(Long sectionID) {
        this.sectionID = sectionID;
    }
}
