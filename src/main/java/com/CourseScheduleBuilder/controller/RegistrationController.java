package com.CourseScheduleBuilder.controller;


import com.CourseScheduleBuilder.Model.User;
import com.CourseScheduleBuilder.Services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 */
@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/registration")
    @CrossOrigin
    @ResponseBody
    public boolean validateAndRegisterNewUserRequest(@RequestBody User user){
        return registrationService.validateAndRegisterNewUserRequest(user);




        //
        // Verification of login info against database to be added.
        // Checks include if username already exists in database.
        // true boolean returned if registration successfully completed
        // false boolean if registration failure (username already exists)
        // FE should declare error if communication failure
        // Prints message to console if duplicated attempted
        // Repository find method returns a null value if the search returns no result
        //


    }
}

