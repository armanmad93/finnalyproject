package com.example.techthink.controller.model;

import java.util.Date;

public class AssignmentResponse {
    private Long id;
    private String name;
    private String text;
    private Date dueDate;
    private String assignmentFileURL;
    private Long sectionID;
    private CourseSectionResponse section;

    public AssignmentResponse() {
    }

    public AssignmentResponse(Long id, String name, String text, Date dueDate, String assignmentFileURL, Long sectionID, CourseSectionResponse section) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.dueDate = dueDate;
        this.assignmentFileURL = assignmentFileURL;
        this.sectionID = sectionID;
        this.section = section;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CourseSectionResponse getSection() {
        return section;
    }

    public void setSection(CourseSectionResponse section) {
        this.section = section;
    }
}
