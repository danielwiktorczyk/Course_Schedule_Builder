package com.CourseScheduleBuilder.controller;

import com.CourseScheduleBuilder.Model.UserPreferences;
import com.CourseScheduleBuilder.Services.UserPreferencesService;
import com.CourseScheduleBuilder.Model.UpdateUserPrefsRequestFromFrontEnd;
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
    public boolean requestUserPreferences(@RequestBody UpdateUserPrefsRequestFromFrontEnd updateParams) {
        System.out.println("made it in, recieved " + updateParams);
        UserPreferences newPreference = userPreferencesService.createNewPreferenceFromRequestData(updateParams.getDay(), updateParams.getPrefStartTime(), updateParams.getPrefEndTime(), updateParams.isAdd());
        System.out.println("going into modifyUserPrefs()...");
        userPreferencesService.modifyUserPrefs(newPreference, updateParams.getEmail());
        System.out.println(newPreference.isAdd());
        return true;
    }
}
