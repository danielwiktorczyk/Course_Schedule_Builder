package com.CourseScheduleBuilder.Services;
import com.CourseScheduleBuilder.Model.User;
import com.CourseScheduleBuilder.Model.UserPreferences;

import java.util.ArrayList;


public interface UserPreferencesService {
    public void modifyUserPrefs(UserPreferences newPreference, String userEmail);
    public void addPref(UserPreferences newPreference, String userEmail);
    public void removePref(UserPreferences newPreference, String userEmail);
    public UserPreferences createNewPreferenceFromRequestData(String day, Integer prefStartTime, Integer prefEndTime, Boolean add);

    }
