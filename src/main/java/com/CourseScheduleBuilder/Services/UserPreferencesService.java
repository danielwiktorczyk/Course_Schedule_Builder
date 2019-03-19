package com.CourseScheduleBuilder.Services;
import com.CourseScheduleBuilder.Model.User;
import com.CourseScheduleBuilder.Model.UserPreferences;

import java.util.ArrayList;


public interface UserPreferencesService {
   void modifyUserPrefs(UserPreferences newPreference, String email);
   User addPref(UserPreferences newPreference, User user);
   User removePref(UserPreferences newPreference, User user);
   UserPreferences createNewPreferenceFromRequestData(String day, Integer prefStartTime, Integer prefEndTime, Boolean add);

    }
