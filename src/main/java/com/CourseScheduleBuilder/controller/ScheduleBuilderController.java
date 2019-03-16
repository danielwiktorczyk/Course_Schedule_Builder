package com.CourseScheduleBuilder.controller;

import com.CourseScheduleBuilder.Model.Course;
import com.CourseScheduleBuilder.Model.InfoSession;
import com.CourseScheduleBuilder.Services.ScheduleBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScheduleBuilderController {

    @Autowired
    ScheduleBuilderService scheduleBuilderService;

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
        return scheduleBuilderService.validatePrerequisites( course);

    }





}
