package com.courseBuilder.courseBuilderAPI.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Courses {

    @Id
    private String courseId, courseName, preRequisiteID, coRequisisteID;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getPreRequisiteID() {
        return preRequisiteID;
    }

    public void setPreRequisiteID(String preRequisiteID) {
        this.preRequisiteID = preRequisiteID;
    }

    public String getCoRequisisteID() {
        return coRequisisteID;
    }

    public void setCoRequisisteID(String coRequisisteID) {
        this.coRequisisteID = coRequisisteID;
    }
}
