package com.CourseScheduleBuilder.controller;

import com.CourseScheduleBuilder.Model.User;
import com.CourseScheduleBuilder.Model.UserFromFrontEnd;
import com.CourseScheduleBuilder.Repositories.UserRepo;
import com.CourseScheduleBuilder.Repositories.loggedInUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class userController {

    @Autowired
    UserRepo userRepo;
    @Autowired
    loggedInUserRepo loggedInUserRepo;


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public UserFromFrontEnd user() {
        UserFromFrontEnd testUser = new UserFromFrontEnd("waqar", "password");
        return testUser
                ;
    }

    @PostMapping("/User")
    @CrossOrigin
    @ResponseBody
    public User userInfo()
    {
        String username = loggedInUserRepo.findByUser("user").getUsername();
        User returnUser = new User();
        try {
           returnUser = (User) userRepo.findByUsername(username).clone();
           returnUser.setPassword("--Redacted--");
        } catch(CloneNotSupportedException e){

        }
        return returnUser;
    }
}
