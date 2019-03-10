package com.CourseScheduleBuilder.controller;

import com.CourseScheduleBuilder.Model.loginUser;
import com.CourseScheduleBuilder.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 */
@RestController
public class LoginController {

    @Autowired //needed to point to the same repo database
    private UserRepo userrepo;

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


