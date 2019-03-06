package com.CourseScheduleBuilder.controller;

import com.CourseScheduleBuilder.Model.loginUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public loginUser user() {
        loginUser testUser = new loginUser("waqar", "password");
        return testUser
                ;
    }
}
