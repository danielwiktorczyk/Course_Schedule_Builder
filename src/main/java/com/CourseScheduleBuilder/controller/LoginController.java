package com.CourseScheduleBuilder.controller;

import com.CourseScheduleBuilder.Model.UserFromFrontEnd;
import com.CourseScheduleBuilder.Repositories.UserRepo;
import com.CourseScheduleBuilder.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 */
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    UserRepo userRepo;

    @PostMapping("/login")
    @CrossOrigin
    @ResponseBody
    public boolean login(@RequestBody UserFromFrontEnd user){
        return loginService.loginUser(user);

    }

    @PostMapping("/logout")
    @CrossOrigin
    @ResponseBody
    public boolean logout(){
        return loginService.logOutUser();

    }
}


