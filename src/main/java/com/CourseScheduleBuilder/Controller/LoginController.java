package com.CourseScheduleBuilder.Controller;

import com.CourseScheduleBuilder.Services.LoginService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 *
 */
@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    @CrossOrigin
    @ResponseBody
    public boolean login(@RequestBody Map user) {
        return loginService.loginUser(user);

    }

    @PostMapping("/logout")
    @CrossOrigin
    @ResponseBody
    public boolean logout() {
        return loginService.logOutUser();

    }
}


