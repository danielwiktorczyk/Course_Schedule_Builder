package com.CourseScheduleBuilder.controller;

import com.CourseScheduleBuilder.Model.UserPreferences;
import com.CourseScheduleBuilder.Services.UserPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserPreferencesController {

    @Autowired
    UserPreferencesService userPreferencesService;

    UserPreferences newPreference = new UserPreferences();

    @PostMapping("/userPreferences")
    @CrossOrigin
    @ResponseBody
    public boolean requestUserPreferences(@RequestBody String day, Integer prefStartTime, Integer prefEndTime, boolean add, String email) {
        System.out.println("made it in, recieved " + day + " " + prefStartTime + ", " + prefEndTime + ", " + add + " email: " + email);
        UserPreferences newPreference = userPreferencesService.createNewPreferenceFromRequestData(day, prefStartTime, prefEndTime, add);

        userPreferencesService.modifyUserPrefs(newPreference, email);
        System.out.println(newPreference.isAdd());
        return true;
    }
}
