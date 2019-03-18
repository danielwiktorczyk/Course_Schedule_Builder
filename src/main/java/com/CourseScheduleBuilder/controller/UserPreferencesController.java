package com.CourseScheduleBuilder.controller;

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

    @PostMapping("/userPreferences")
    @CrossOrigin
    @ResponseBody
    public boolean requestUserPreferences(/* //TODO */) {
        return true;
        // using userPreferences for logic
    }
}
