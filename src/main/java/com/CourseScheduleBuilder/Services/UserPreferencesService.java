package com.CourseScheduleBuilder.Services;
import com.CourseScheduleBuilder.Model.UserPreferences;
import java.util.ArrayList;


public interface UserPreferencesService {
   void modifyUserPrefs(UserPreferences newPreference);
   void addPref(UserPreferences newPreference);
   void removePref(UserPreferences newPreference);
   UserPreferences createNewPreferenceFromRequestData(String day, Integer prefStartTime, Integer prefEndTime, Boolean add);
   ArrayList<UserPreferences> getUserPreferences();
   }
