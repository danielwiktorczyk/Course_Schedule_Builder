package com.CourseScheduleBuilder.Services;
import com.CourseScheduleBuilder.Model.User;
import com.CourseScheduleBuilder.Model.UserPreferences;


public interface UserPreferencesService {
    public void updateUserPreferences(User user, UserPreferences newPreference);
}
