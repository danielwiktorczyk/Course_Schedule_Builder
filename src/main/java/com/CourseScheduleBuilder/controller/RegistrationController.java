package com.CourseScheduleBuilder.controller;


import com.CourseScheduleBuilder.Model.User;
import com.CourseScheduleBuilder.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 */
@RestController
public class RegistrationController {

    @Autowired //needed to point to the same repo database
    private UserRepo userrepo;

    @PostMapping("/registration")
    @CrossOrigin
    @ResponseBody
    public boolean login(@RequestBody User user){

        if (userrepo.findByEmail(user.getEmail()) == null ){
            userrepo.save(user);
             return true;
        }
            else {
                System.out.println("DUPLICATE ACCOUNT CREATION ATTEMPTED");
                System.out.println(user.getEmail());
                 return false;
        }


        //
        // Verification of login info against database to be added.
        // Checks include if username already exists in database.
        // true boolean returned if registration successfully completed
        // false boolean if registration failure (username already exists)
        // FE should declare error if communication failure
        // Currently printing all information received to terminal to provide proof of communications
        // No FE interface exists for testing currently, use POSTMAN.
        //

    }
}

