package com.CourseScheduleBuilder.controller;

import com.CourseScheduleBuilder.Services.ScheduleBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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



}
