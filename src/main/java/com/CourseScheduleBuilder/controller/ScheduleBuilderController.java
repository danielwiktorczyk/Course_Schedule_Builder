package com.CourseScheduleBuilder.controller;

import com.CourseScheduleBuilder.Model.FEMessage;
import com.CourseScheduleBuilder.Model.Schedule;
import com.CourseScheduleBuilder.Repositories.CourseRepo;
import com.CourseScheduleBuilder.Services.ScheduleBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.CourseScheduleBuilder.Services.UserPreferencesService;

@RestController
public class ScheduleBuilderController {

    @Autowired
    ScheduleBuilderService scheduleBuilderService;

    @Autowired
    CourseRepo courseRepo;

    @Autowired
    UserPreferencesService userPreferencesService;

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
           return "Prerequisites not met";

        scheduleBuilderService.generateSchedules(courses.getMessage(),"Fall");
        return "Course added!";
    }
    @PostMapping("/clear")
    @CrossOrigin
    @ResponseBody
    public boolean clear()
    {
        userPreferencesService.destroyPreferences();
        scheduleBuilderService.clear();
        return true;
    }

    @PostMapping("/fallSchedule")
    @CrossOrigin
    @ResponseBody
    public Schedule seeUserScheduleFall()
    {
        return scheduleBuilderService.seeUserSchedule("Fall");
    }

    @PostMapping("/WinterSchedule")
    @CrossOrigin
    @ResponseBody
    public Schedule seeUserScheduleWinter()
    {
        return scheduleBuilderService.seeUserSchedule("Winter");
    }
    @PostMapping("/SummerSchedule")
    @CrossOrigin
    @ResponseBody
    public Schedule seeUserScheduleSummer()
    {
        return scheduleBuilderService.seeUserSchedule("Summer");
    }

    @PostMapping("/addCourseToWishListWinter")
    @CrossOrigin
    @ResponseBody
    public String addCourseToWishListWinter(@RequestBody FEMessage courses)
    {
        if(!scheduleBuilderService.validatePrerequisites(courses.getMessage()))
            return "Prerequisites not met";

        scheduleBuilderService.generateSchedules(courses.getMessage(),"Winter");
        return "Course added!";
    }

    @PostMapping("/addCourseToWishListSummer")
    @CrossOrigin
    @ResponseBody
    public String addCourseToWishListSummer(@RequestBody FEMessage courses)
    {
        if(!scheduleBuilderService.validatePrerequisites(courses.getMessage()))
            return "Prerequisites not met";

        scheduleBuilderService.generateSchedules(courses.getMessage(),"Summer");
        return "Course added!";
    }

    @PostMapping("/generate")
    @CrossOrigin
    @ResponseBody
    public Schedule generateSchedule()
    {
        Schedule returnSchedule;
        if(userPreferencesService.preferencesPresent()){
            returnSchedule = scheduleBuilderService.generateAndShowFirstPrefSchedule();
        }
        else {
            returnSchedule = scheduleBuilderService.generateAndShowFirstSchedule();
        }
        returnSchedule.adjustLength();
        return returnSchedule;

    }
    @PostMapping("/next")
    @CrossOrigin
    @ResponseBody
    public Schedule nextSchedule()
    {
        Schedule returnSchedule;
        if(userPreferencesService.preferencesPresent()){
            returnSchedule = scheduleBuilderService.nextPrefSchedule();
        }
        else {
            returnSchedule = scheduleBuilderService.nextSchedule();
        }
        returnSchedule.adjustLength();
        return returnSchedule;

    }
    @PostMapping("/previous")
    @CrossOrigin
    @ResponseBody
    public Schedule previousSchedule()
    {
        Schedule returnSchedule;
        if(userPreferencesService.preferencesPresent()){
            returnSchedule = scheduleBuilderService.nextPrefSchedule();
        }
        else{
            returnSchedule = scheduleBuilderService.previousSchedule();
    }
        returnSchedule.adjustLength();
        return returnSchedule;

    }
    @PostMapping("/enrollFall")
    @CrossOrigin
    @ResponseBody
    public String enrollFall()
    {
        if (!scheduleBuilderService.validateCorequisites())
        return "Corequisites were not met";

        scheduleBuilderService.enroll("Fall");
        return "Enrolled";

    }
    @PostMapping("/enrollWinter")
    @CrossOrigin
    @ResponseBody
    public String enrollWinter()
    {
        if (!scheduleBuilderService.validateCorequisites())
            return "Corequisites were not met";
        scheduleBuilderService.enroll("Winter");
        return "Enrolled";
    }
    @PostMapping("/enrollSummer")
    @CrossOrigin
    @ResponseBody
    public String enrollSummer()
    {
        if (!scheduleBuilderService.validateCorequisites())
            return "Corequisites were not met";
        scheduleBuilderService.enroll("Summer");
        return "Enrolled";

    }

    @PostMapping("/progression")
    @CrossOrigin
    @ResponseBody
    public String[] progression()
    {
        return scheduleBuilderService.coursesTaken();

    }






}
