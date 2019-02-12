package com.example.springbootwithreactjs.controller;

import com.example.springbootwithreactjs.Course;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

  @RequestMapping(value = "/pong", method = RequestMethod.GET)
  @CrossOrigin(origins = "http://localhost:3000")
  public Course pong() {
    Course x = new Course("COMP249", "COMP248");
    return x;
  }
}
