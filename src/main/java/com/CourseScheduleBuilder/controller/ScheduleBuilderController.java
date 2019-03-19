package com.CourseScheduleBuilder.controller;

import com.CourseScheduleBuilder.Repositories.CourseRepo;
import com.CourseScheduleBuilder.Services.ScheduleBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScheduleBuilderController {

    @Autowired
    ScheduleBuilderService scheduleBuilderService;

    @Autowired
    CourseRepo courseRepo;

    @PostMapping("/createSchedule") // TODO rename as needed, we can have
    // many mappings this controller can handle
    @CrossOrigin
    @ResponseBody
    public boolean requestSchedule(/* //TODO */){
        return true;
        // using scheduleBuilder here for logic
    }
    @PostMapping("/addCourseToWishList")

    @CrossOrigin
    @ResponseBody
    public boolean addCourseToWishList(@RequestBody String course)
    {
        String[] classes = {"COMP248","COMP232","SOEN287"};
        scheduleBuilderService.scheduleGenerator(classes);
        return true;
       // course = course.substring(9,course.length()-2);
       // return scheduleBuilderService.validatePrerequisites(course);

    }





}
