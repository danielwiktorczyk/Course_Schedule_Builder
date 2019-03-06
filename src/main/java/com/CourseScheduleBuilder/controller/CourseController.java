package com.CourseScheduleBuilder.controller;

import com.CourseScheduleBuilder.Model.Course;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

  @RequestMapping(value = "/course", method = RequestMethod.GET)
  @CrossOrigin(origins = "http://localhost:3000")
  public Course courseTest() {
    Course testCourse = new Course();
    testCourse.setName("COMP249");
    testCourse.setPreReq("COMP248");
    return testCourse;
  }
}
