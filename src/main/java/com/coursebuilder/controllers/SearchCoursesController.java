package com.coursebuilder.controllers;

import com.coursebuilder.Course;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchCoursesController {

    @GetMapping("/api/courses")
    public String displayAllCourses() {

        Course sample = new Course();

        return "anything";
    }

}
