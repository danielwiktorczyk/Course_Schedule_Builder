package com.example.springbootwithreactjs.controller;

import com.example.springbootwithreactjs.loginUser;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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


