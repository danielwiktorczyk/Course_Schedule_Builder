package com.CourseScheduleBuilder.controller;

import com.CourseScheduleBuilder.Model.UserPreferences;
import com.CourseScheduleBuilder.Services.UserPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserPreferencesController {

    @Autowired
    UserPreferencesService userPreferencesService;

    UserPreferences newPreference = new UserPreferences();

    @PostMapping("/userPreferences")
    @CrossOrigin
    @ResponseBody
    public boolean requestUserPreferences(String email, String day, Integer prefStartTime, Integer prefEndTime, boolean add) {
        if(day.equalsIgnoreCase("monday")){
            newPreference.setMonday(true);
            newPreference.setTuesday(false);
            newPreference.setWednesday(false);
            newPreference.setThursday(false);
            newPreference.setFriday(false);
        }
        else if(day.equalsIgnoreCase("tuesday")){
            newPreference.setTuesday(true);
            newPreference.setMonday(false);
            newPreference.setWednesday(false);
            newPreference.setThursday(false);
            newPreference.setFriday(false);
        }
        else if(day.equalsIgnoreCase("wednesday")){
            newPreference.setWednesday(true);
            newPreference.setTuesday(false);
            newPreference.setMonday(false);
            newPreference.setThursday(false);
            newPreference.setFriday(false);
        }
        else if(day.equalsIgnoreCase("thursday")){
            newPreference.setThursday(true);
            newPreference.setTuesday(false);
            newPreference.setWednesday(false);
            newPreference.setMonday(false);
            newPreference.setFriday(false);
        }
        else if(day.equalsIgnoreCase("friday")){
            newPreference.setFriday(true);
            newPreference.setTuesday(false);
            newPreference.setWednesday(false);
            newPreference.setThursday(false);
            newPreference.setMonday(false);
        }
        else{
            System.out.println("A day for this preference option was not specified, preferences not updated");
            return false;
        }



        newPreference.setStartTime(prefStartTime);
        newPreference.setEndTime(prefEndTime);
        if (add){
            newPreference.setAdd(true);
        }
        else{
            newPreference.setAdd(false);
        }

        userPreferencesService.modifyUserPrefs(newPreference, email);
        System.out.println(newPreference.isAdd());
        return true;
    }
}
