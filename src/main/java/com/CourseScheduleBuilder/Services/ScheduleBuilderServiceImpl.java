package com.CourseScheduleBuilder.Services;

import com.CourseScheduleBuilder.Model.Course;
import com.CourseScheduleBuilder.Model.InfoSession;
import com.CourseScheduleBuilder.Model.User;
import com.CourseScheduleBuilder.Model.loggedInUser;
import com.CourseScheduleBuilder.Repositories.CourseRepo;
import com.CourseScheduleBuilder.Repositories.UserRepo;
import com.CourseScheduleBuilder.Repositories.loggedInUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleBuilderServiceImpl implements ScheduleBuilderService {

        @Autowired
        CourseRepo courseRepo;
        @Autowired
        UserRepo userRepo;
        @Autowired
        loggedInUserRepo login;

        private static User user;
        private static loggedInUser loginUser;
        private static ArrayList prereqs;
    @Override
    public boolean validatePrerequisites(String courseToValidate) {

        Course courseInDB = courseRepo.findByName(courseToValidate);




    //    List<String> course = courseRepo.findByName(info.getCourses());


        return false;
    }

    private void retriveUserInfo()
    {

        loginUser = login.findByUser("user");

       user = userRepo.findByEmail(loginUser.getEmail());

  //       nameOfCourseToTake = info.getCourses().get(0);

        //retrieves all the users course already taken
        prereqs = user.getPrereqs();
    }
}
