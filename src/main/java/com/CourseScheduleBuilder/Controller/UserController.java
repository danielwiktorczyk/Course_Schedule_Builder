package com.CourseScheduleBuilder.Controller;

import com.CourseScheduleBuilder.Model.User;
import com.CourseScheduleBuilder.Model.UserFromFrontEnd;
import com.CourseScheduleBuilder.Repositories.UserRepo;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


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
    public User userInfo() {
        String username = userRepo.findByActive(1).get(0).getUsername();
        User returnUser = new User();
        try {
            returnUser = (User) userRepo.findByUsername(username).clone();
            returnUser.setPassword("--Redacted--");
        } catch (CloneNotSupportedException e) {

        }
        return returnUser;
    }
}
