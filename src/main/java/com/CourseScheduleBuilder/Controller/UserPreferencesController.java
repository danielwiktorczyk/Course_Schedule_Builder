package com.CourseScheduleBuilder.Controller;

import com.CourseScheduleBuilder.Model.PreferencesFromFrontEnd;
import com.CourseScheduleBuilder.Model.UserPreferences;
import com.CourseScheduleBuilder.Services.UserPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class UserPreferencesController {

    @Autowired
    UserPreferencesService userPreferencesService;

    UserPreferences newPreference = new UserPreferences();

    @PostMapping("/userPreferences")
    @CrossOrigin
    @ResponseBody
    public String requestUserPreferences(@RequestBody PreferencesFromFrontEnd updateParams) {
        if(userPreferencesService.createPreferences(updateParams.isMm(), updateParams.isMe(), updateParams.isMall(), updateParams.isTm(), updateParams.isTe(), updateParams.isTall(), updateParams.isWm(), updateParams.isWe(), updateParams.isWall(), updateParams.isThm(), updateParams.isThe(), updateParams.isThall(), updateParams.isFm(), updateParams.isFe(), updateParams.isFall())){
            return "Success";
        }
        else{
            return "Preferences Not Created";
        }

    }
}
