package com.CourseScheduleBuilder.controller;


import com.CourseScheduleBuilder.Model.User;
import org.springframework.web.bind.annotation.*;

/**
 */
@RestController
public class RegistrationController {
    @PostMapping("/registration")
    @CrossOrigin
    @ResponseBody
    public boolean login(@RequestBody User user){

        return true;

        //
        // Verification of login info against database to be added.
        // Checks include if username already exists in database.
        // true boolean returned if registration successfully completed
        // false boolean if registration failure (username already exists)
        // FE should declare error if communication failure
        // Currently printing all information received to terminal to provide proof of communications
        // No FE interface exists for testing currently, use POSTMAN.
        //

    }
}

