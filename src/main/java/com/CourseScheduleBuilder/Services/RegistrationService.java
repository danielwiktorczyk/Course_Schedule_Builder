package com.CourseScheduleBuilder.Services;

import com.CourseScheduleBuilder.Model.User;

public interface RegistrationService {

    boolean validateAndRegisterNewUserRequest(User user);

    User getUserProfileInfo();

}
