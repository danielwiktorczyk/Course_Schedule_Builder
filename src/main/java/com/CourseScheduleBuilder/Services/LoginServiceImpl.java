package com.CourseScheduleBuilder.Services;

import com.CourseScheduleBuilder.Model.User;
import com.CourseScheduleBuilder.Repositories.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired //needed to point to the same repo database
    private UserRepo userRepo;

    @Override
    public boolean loginUser(Map userLogin) {

        if (userRepo.findByUsername((String)userLogin.get("username")) == null){
            return false;
        }
        if (userRepo.findByUsername((String)userLogin.get("username")).getPassword().equals(userLogin.get("password"))){
            User user = userRepo.findByUsername((String)userLogin.get("username"));
            logout_old_user_and_login_new(user);
            setActiveStatus(user);
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

    private void setActiveStatus(User user) {
        User newUser = userRepo.findByUsername(user.getUsername());
        newUser.setActive(1);
        userRepo.save(newUser);
    }


    /**
         * will check to see if a user is logged in,
         * log them out if the case and allow the new user to log in
         * @param user
         */
        private void logout_old_user_and_login_new(User user){
            if(userRepo.findByActive(1).size() > 0)
            {
                System.out.println("Logging out: "+userRepo.findByActive(1).get(0).getFirstName()+" "+userRepo.findByActive(1).get(0).getLastName());
                userRepo.findByActive(1).get(0).setActive(0);
                System.out.println("New Login: "+userRepo.findByUsername(user.getUsername()).getFirstName()+" "+userRepo.findByUsername(user.getUsername()).getFirstName());
            }
        }



    public boolean logOutUser(){
            User user = userRepo.findByActive(1).get(0);
            user.setActive(0);
            userRepo.save(user);
        return true;
    }
}
