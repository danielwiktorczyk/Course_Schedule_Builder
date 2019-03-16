package com.CourseScheduleBuilder.controller;

import com.CourseScheduleBuilder.Model.Course;
import com.CourseScheduleBuilder.Model.searchCourse;
import com.CourseScheduleBuilder.Repositories.CourseRepo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class CourseController {
  @Autowired
  private CourseRepo courseRepo;

  @RequestMapping(value = "/course", method = RequestMethod.GET)
  //@CrossOrigin(origins = "http://localhost:3000")

  @CrossOrigin
  @ResponseBody
  public Course returnCourse(@RequestBody searchCourse course) {

    if (courseRepo.findBySubjectAndCourseNumber(course.getSearchedCourse(), course.getSearchedCourseNum()) != null) {
      return courseRepo.findBySubjectAndCourseNumber(course.getSearchedCourse(), course.getSearchedCourseNum());
    } else {
      return null;
    }
  }

    /*
     This method should allow a user to request a course from the database by subject and course number
     and return a course matching that criteria from the database.  To be useful, a lot more criteria
     will need to be added
     */
}
