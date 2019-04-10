package com.CourseScheduleBuilder.Controller;

import com.CourseScheduleBuilder.Model.FEMessage;
import com.CourseScheduleBuilder.Model.Schedule;
import com.CourseScheduleBuilder.Model.User;
import com.CourseScheduleBuilder.Services.ScheduleBuilderService;
import com.CourseScheduleBuilder.Services.UserPreferencesService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScheduleBuilderController {

    private final ScheduleBuilderService scheduleBuilderService;
    private final UserPreferencesService userPreferencesService;

    public ScheduleBuilderController(ScheduleBuilderService scheduleBuilderService, UserPreferencesService userPreferencesService) {
        this.scheduleBuilderService = scheduleBuilderService;
        this.userPreferencesService = userPreferencesService;
    }

    @PostMapping("/createSchedule") // TODO rename as needed, we can have // many mappings this Controller can handle
    @CrossOrigin
    @ResponseBody
    public boolean requestSchedule(/* //TODO */) {
        return true;
        // using scheduleBuilder here for logic
    }

    @PostMapping("/addCourseToWishListFall")
    @CrossOrigin
    @ResponseBody
    public String addCourseToWishListFall(@RequestBody FEMessage courses) {

        if (!scheduleBuilderService.validatePrerequisites(courses.getMessage()))
            return "Prerequisites not met";

        scheduleBuilderService.generateSchedules(courses.getMessage(), "Fall");
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
    public Schedule seeUserScheduleFall() {
        return scheduleBuilderService.seeUserSchedule("Fall");
    }

    @PostMapping("/WinterSchedule")
    @CrossOrigin
    @ResponseBody
    public Schedule seeUserScheduleWinter() {
        return scheduleBuilderService.seeUserSchedule("Winter");
    }

    @PostMapping("/SummerSchedule")
    @CrossOrigin
    @ResponseBody
    public Schedule seeUserScheduleSummer() {
        return scheduleBuilderService.seeUserSchedule("Summer");
    }

    @PostMapping("/addCourseToWishListWinter")
    @CrossOrigin
    @ResponseBody
    public String addCourseToWishListWinter(@RequestBody FEMessage courses) {
        if (!scheduleBuilderService.validatePrerequisites(courses.getMessage()))
            return "Prerequisites not met";

        scheduleBuilderService.generateSchedules(courses.getMessage(), "Winter");
        return "Course added!";
    }

    @PostMapping("/addCourseToWishListSummer")
    @CrossOrigin
    @ResponseBody
    public String addCourseToWishListSummer(@RequestBody FEMessage courses) {
        if (!scheduleBuilderService.validatePrerequisites(courses.getMessage()))
            return "Prerequisites not met";

        scheduleBuilderService.generateSchedules(courses.getMessage(), "Summer");
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
            System.out.println("pref schedule generate");
        }
        else {
            returnSchedule = scheduleBuilderService.generateAndShowFirstSchedule();
            System.out.println("regular schedule generate");
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
    public String enrollFall() {
        if (!scheduleBuilderService.validateCorequisites())
            return "Corequisites were not met";

        scheduleBuilderService.enroll("Fall");
        return "Enrolled";

    }

    @PostMapping("/enrollWinter")
    @CrossOrigin
    @ResponseBody
    public String enrollWinter() {
        if (!scheduleBuilderService.validateCorequisites())
            return "Corequisites were not met";
        scheduleBuilderService.enroll("Winter");
        return "Enrolled";
    }

    @PostMapping("/enrollSummer")
    @CrossOrigin
    @ResponseBody
    public String enrollSummer() {
        if (!scheduleBuilderService.validateCorequisites())
            return "Corequisites were not met";
        scheduleBuilderService.enroll("Summer");
        return "Enrolled";

    }

    @PostMapping("/progression")
    @CrossOrigin
    @ResponseBody
    public String[] progression() {
        return scheduleBuilderService.coursesTaken();

    }

    @PostMapping("/dropFall")
    @CrossOrigin
    @ResponseBody
    public String dropFall(@RequestBody FEMessage request)
    {
        return scheduleBuilderService.dropCourse(request.getMessage(), "Fall");

    }

    @PostMapping("/dropWinter")
    @CrossOrigin
    @ResponseBody
    public String dropWinter(@RequestBody FEMessage request)
    {
        return scheduleBuilderService.dropCourse(request.getMessage(), "Winter");

    }

    @PostMapping("/dropSummer")
    @CrossOrigin
    @ResponseBody
    public String dropSummer(@RequestBody FEMessage request)
    {
        return scheduleBuilderService.dropCourse(request.getMessage(), "Summer");

    }

    @PostMapping("/addFall")
    @CrossOrigin
    @ResponseBody
    public String addFall(@RequestBody FEMessage request)
    {
        return scheduleBuilderService.addCourse(request.getMessage(), "Fall");

    }

    @PostMapping("/addWinter")
    @CrossOrigin
    @ResponseBody
    public String addWinter(@RequestBody FEMessage request)
    {
        return scheduleBuilderService.addCourse(request.getMessage(), "Winter");

    }

    @PostMapping("/addSummer")
    @CrossOrigin
    @ResponseBody
    public String addSummer(@RequestBody FEMessage request)
    {
        return scheduleBuilderService.addCourse(request.getMessage(), "Summer");

    }


}
