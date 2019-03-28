package com.CourseScheduleBuilder.Model;

import java.util.List;

public class InfoSession {


   private String email;
   List<String> courses;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }


    public InfoSession(String email, List<String> courses) {
        this.email = email;
        this.courses = courses;
    }





}

