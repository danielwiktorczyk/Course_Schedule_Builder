package com.example.springbootwithreactjs;

public class Course {
    private String courseId;
    private String prereq;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getPrereq() {
        return prereq;
    }

    public void setPrereq(String prereq) {
        this.prereq = prereq;
    }

    public Course(String courseId, String prereq){
        this.courseId = courseId;
        this.prereq = prereq;
    }
}
