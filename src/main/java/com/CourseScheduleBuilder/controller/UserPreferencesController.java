package com.CourseScheduleBuilder.controller;

import com.CourseScheduleBuilder.Model.UserPreferences;
import com.CourseScheduleBuilder.Services.UserPreferencesService;
import com.CourseScheduleBuilder.Model.UpdateUserPrefsRequestFromFrontEnd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class UserPreferencesController {

    @Autowired
    UserPreferencesService userPreferencesService;

    UserPreferences newPreference = new UserPreferences();

    // TODO: 2019-03-22 should point to location of schedule builder
    @PostMapping("/userPreferences")
    @CrossOrigin
    @ResponseBody
    public boolean requestUserPreferences(@RequestBody UpdateUserPrefsRequestFromFrontEnd updateParams) {
        UserPreferences newPreference = userPreferencesService.createNewPreferenceFromRequestData(updateParams.getDay(), updateParams.getPrefStartTime(), updateParams.getPrefEndTime(), updateParams.isAdd());
        System.out.println("newPreference created" + newPreference.getEndTime() + ", " + newPreference.getEndTime() + "tuesday:" + newPreference.isTuesday() + "Add boolean = " + newPreference.isAdd());
        userPreferencesService.modifyUserPrefs(newPreference);
        return true;
    }
}