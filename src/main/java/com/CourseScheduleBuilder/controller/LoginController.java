package com.CourseScheduleBuilder.controller;
import com.CourseScheduleBuilder.Model.*;
import com.CourseScheduleBuilder.Model.UserFromFrontEnd;
import com.CourseScheduleBuilder.Repositories.UserRepo;
import com.CourseScheduleBuilder.Services.AuthenticateFacade;
import com.CourseScheduleBuilder.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 */
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthenticateFacade authenticateFacade;

    @PostMapping("/login")
    @CrossOrigin
    @ResponseBody
    public boolean login(@RequestBody UserFromFrontEnd user){
        User newUser = userRepo.findByUsername(user.getUsername());

        logout_old_user_and_login_new(user);
        newUser.setActive(1);
        return loginService.loginUser(user);

//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), null);
//        SecurityContextHolder.getContext().setAuthentication(authentication);



    }

    /**
     * will check to see if a user is logged in,
     * log them out if the case and allow the new user to log in
     * @param user
     */
    public void logout_old_user_and_login_new(UserFromFrontEnd user)
    {
        if(userRepo.findByActive(1).size() > 0)
        {
            System.out.println("Logging out: "+userRepo.findByActive(1).get(0).getFirstName()+" "+userRepo.findByActive(1).get(0).getLastName());
            userRepo.findByActive(1).get(0).setActive(0);
            System.out.println("New Login: "+userRepo.findByUsername(user.getUsername()).getFirstName()+" "+userRepo.findByUsername(user.getUsername()).getFirstName());
        }
    }

    public String currentUserNameSimple() {
        Authentication authentication = authenticateFacade.getAuthentication();
        return authentication.getName();
    }
}


