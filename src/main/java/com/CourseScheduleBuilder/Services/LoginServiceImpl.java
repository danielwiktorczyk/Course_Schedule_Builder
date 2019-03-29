package com.CourseScheduleBuilder.Services;

import com.CourseScheduleBuilder.Model.UserFromFrontEnd;
import com.CourseScheduleBuilder.Model.loggedInUser;
import com.CourseScheduleBuilder.Repositories.UserRepo;
import com.CourseScheduleBuilder.Repositories.loggedInUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired //needed to point to the same repo database
    private UserRepo userrepo;
    @Autowired //needed to point to the same repo database
    private loggedInUserRepo loggedInUser;

    @Override
    public boolean loginUser(UserFromFrontEnd user) {
        if (userrepo.findByUsername(user.getUsername()) == null){
            return false;
        }
        if (userrepo.findByUsername(user.getUsername()).getPassword().equals(user.getPassword())){
            loggedInUser.save(new loggedInUser(user.getUsername()));
            return true;
        }
        else {
            return false;

        }
        /*
        This code validates if a user has entered a valid login. If the account doesn't exist
        or the password is invalid, the method will return a false value to the front-end. The
        front end should tell the user that either the email or password is invalid. The first
        if condition is activated if the database query yields no existing email by that name.
        A user is logged in if the database finds an email by the email attempted to be accessed
        and the password is correct, otherwise a false value is returned.
         */
    }
}
