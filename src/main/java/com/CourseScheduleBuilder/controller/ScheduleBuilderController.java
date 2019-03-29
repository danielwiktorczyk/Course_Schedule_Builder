package com.CourseScheduleBuilder.controller;

import com.CourseScheduleBuilder.Model.FEMessage;
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
    public String addCourseToWishListFall(@RequestBody FEMessage courses)
    {

       if(!scheduleBuilderService.validatePrerequisites(courses.getMessage()))
           return "Prereqs not met";

        scheduleBuilderService.generateSchedules(courses.getMessage(),"Fall");
        return "Couse added";
    }
    @PostMapping("/clear")
    @CrossOrigin
    @ResponseBody
    public boolean clear()
    {
        scheduleBuilderService.clear();
        return true;
    }

    @PostMapping("/fallSchedule")
    @CrossOrigin
    @ResponseBody
    public Schedule seeUserScheduleFall()
    {
        return scheduleBuilderService.seeUserScheduleFall();
    }

    @PostMapping("/addCourseToWishListWinter")

    @CrossOrigin
    @ResponseBody
    public boolean addCourseToWishListWinter(@RequestBody FEMessage courses)
    {
        scheduleBuilderService.generateSchedules(courses.getMessage(),"Winter");
        return true;
    }

    @PostMapping("/addCourseToWishListSummer")

    @CrossOrigin
    @ResponseBody
    public boolean addCourseToWishListSummer(@RequestBody FEMessage courses)
    {
        scheduleBuilderService.generateSchedules(courses.getMessage(),"Summer");
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
    @PostMapping("/enrollFall")
    @CrossOrigin
    @ResponseBody
    public boolean enrollFall()
    {
        return scheduleBuilderService.enroll("Fall");

    }
    @PostMapping("/enrollWinter")
    @CrossOrigin
    @ResponseBody
    public boolean enroleWinter()
    {
        return scheduleBuilderService.enroll("Winter");

    }
    @PostMapping("/enrollSummer")
    @CrossOrigin
    @ResponseBody
    public boolean enrollSummer()
    {
        return scheduleBuilderService.enroll("Summer");

    }




}
