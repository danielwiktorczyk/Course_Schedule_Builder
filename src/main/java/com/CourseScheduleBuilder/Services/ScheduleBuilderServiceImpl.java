package com.CourseScheduleBuilder.Services;

import com.CourseScheduleBuilder.Model.Course;
import com.CourseScheduleBuilder.Model.User;
import com.CourseScheduleBuilder.Model.loggedInUser;
import com.CourseScheduleBuilder.Repositories.CourseRepo;
import com.CourseScheduleBuilder.Repositories.UserRepo;
import com.CourseScheduleBuilder.Repositories.loggedInUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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


       Course courseInDB = courseRepo.findByNameAndComponent(courseToValidate,"LEC").get(0);
       String coursePrereq = courseInDB.getPreReq();

       if(coursePrereq == null)
            return true;
       user = retriveUserInfo();
        prereqs = user.getPrereqs();
        for(int i=0; i<prereqs.size();i++){
            if (coursePrereq.equals(prereqs.get(i)))
                return true;
        }
        return false;





    //    List<String> course = courseRepo.findByName(info.getCourses());


      //  return false;
    }

    private User retriveUserInfo()
    {

        loginUser = login.findByUser("user");

       user = userRepo.findByEmail(loginUser.getEmail());

  //       nameOfCourseToTake = info.getCourses().get(0);

        //retrieves all the users course already taken
        return user;
    }
}
