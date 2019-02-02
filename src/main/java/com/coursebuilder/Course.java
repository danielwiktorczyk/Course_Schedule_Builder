package com.coursebuilder;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Course {

    private String courseId;
    private String courseName;
    private Section lecture;
    private Section lab;
    private Section tutorial;


}
