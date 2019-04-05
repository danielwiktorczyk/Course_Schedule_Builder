package com.CourseScheduleBuilder.Services;

import com.CourseScheduleBuilder.Model.UserFromFrontEnd;

public interface LoginService {

    public boolean loginUser(UserFromFrontEnd user) ;
    public boolean logOutUser();

}
