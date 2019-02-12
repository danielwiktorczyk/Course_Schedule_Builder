package com.example.springbootwithreactjs.controller;

import com.example.springbootwithreactjs.Course;
import com.example.springbootwithreactjs.loginUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public loginUser user() {
        loginUser x = new loginUser("waqar", "password");
        return x;
    }
}
