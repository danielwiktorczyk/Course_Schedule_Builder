package com.CourseScheduleBuilder.controller;

import com.CourseScheduleBuilder.Model.Schedule;
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

    @PostMapping("/createSchedule") // TODO rename as needed, we can have // many mappings this controller can handle
    @CrossOrigin
    @ResponseBody
    public boolean requestSchedule(/* //TODO */){
        return true;
        // using scheduleBuilder here for logic
    }
    @PostMapping("/addCourseToWishListFall")

    @CrossOrigin
    @ResponseBody
    public boolean addCourseToWishListFall(@RequestBody String courses)
    {
        scheduleBuilderService.generateSchedules(courses,"Fall");
        return true;
    }

    @PostMapping("/addCourseToWishListWinter")

    @CrossOrigin
    @ResponseBody
    public boolean addCourseToWishListWinter(@RequestBody String courses)
    {
        scheduleBuilderService.generateSchedules(courses,"Winter");
        return true;
    }

    @PostMapping("/addCourseToWishListSummer")

    @CrossOrigin
    @ResponseBody
    public boolean addCourseToWishListSummer(@RequestBody String courses)
    {
        scheduleBuilderService.generateSchedules(courses,"Summer");
        return true;
    }

    @PostMapping("/generate")
    @CrossOrigin
    @ResponseBody
    public Schedule generateSchedule()
    {
        Schedule returnSchedule = scheduleBuilderService.generateAndShowFirstSchedule();
        returnSchedule.adjustLength();
        return returnSchedule;

    }
    @PostMapping("/next")
    @CrossOrigin
    @ResponseBody
    public Schedule nextSchedule()
    {
        Schedule returnSchedule = scheduleBuilderService.nextSchedule();
        returnSchedule.adjustLength();
        return returnSchedule;

    }
    @PostMapping("/previous")
    @CrossOrigin
    @ResponseBody
    public Schedule previousSchedule()
    {
        Schedule returnSchedule = scheduleBuilderService.previousSchedule();
        returnSchedule.adjustLength();
        return returnSchedule;

    }




}
