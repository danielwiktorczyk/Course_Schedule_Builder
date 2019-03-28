package com.CourseScheduleBuilder.Services;

import com.CourseScheduleBuilder.Model.User;
import com.CourseScheduleBuilder.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired //needed to point to the same repo database
    private UserRepo userrepo;

    public boolean validateAndRegisterNewUserRequest(User user){
        if (userrepo.findByUserName(user.getUsername()) == null ){
            user.setEWT(false);
            userrepo.save(user);
            return true;
        }
        else {
            System.out.println("DUPLICATE ACCOUNT CREATION ATTEMPTED");
            System.out.println(user.getUsername());
            return false;
        }


        //
        // Verification of login info against database to be added.
        // Checks include if username already exists in database.
        // true boolean returned if registration successfully completed
        // false boolean if registration failure (username already exists)
        // FE should declare error if communication failure
        // Prints message to console if duplicated attempted
        // Repository find method returns a null value if the search returns no result
        //


    }

}
