package com.example.springbootwithreactjs.controller;

import com.example.springbootwithreactjs.Course;
import com.example.springbootwithreactjs.login;
import org.springframework.web.bind.annotation.*;

/**
 */
@RestController
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    public Boolean doSomeThing(@RequestBody login input){
        System.out.println(input.getUsername() + " " + input.getPassword());
        if (input.getUsername() == "waqar" && input.getPassword() == "password")
            return true;
        else return false;
    }
}
