package com.CourseScheduleBuilder.controller;

import com.CourseScheduleBuilder.Model.loginUser;
import org.springframework.web.bind.annotation.*;

/**
 */
@RestController
public class LoginController {

    @PostMapping("/login")
    @CrossOrigin
    @ResponseBody
    public boolean login(@RequestBody loginUser user){
        System.out.println(user.getUsername() + " " + user.getPassword());
        if (user.getUsername().equals("waqar") && user.getPassword().equals("password")) {
            System.out.println("Login true!");
            return true;
        }
            else return false;
    }
}


