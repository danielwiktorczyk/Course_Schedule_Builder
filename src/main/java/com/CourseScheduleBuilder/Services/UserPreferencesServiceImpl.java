package com.CourseScheduleBuilder.Services;

import com.CourseScheduleBuilder.Model.User;
import com.CourseScheduleBuilder.Model.UserPreferences;
import com.CourseScheduleBuilder.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserPreferencesServiceImpl implements UserPreferencesService{

    @Autowired
    private UserRepo userRepo;


    private ArrayList<UserPreferences> getUserPreferenceData()
    {
        User user;

        user = userRepo.findByEmail(.getEmail());

        return user.getUserPrefs();
    }

    public void modifyUserPrefs(UserPreferences newPreference) {
        if(newPreference.isAdd()){
            addPref(newPreference);
        }
        else{
            removePref(newPreference);
        }
    }
    /*
    Method that modifies userPrefs when they are updated by the user
    uses helper methods addPref() and removePref() defined below
     */

    public void addPref(UserPreferences newPreference) {
        ArrayList<UserPreferences>  currentPrefs = getUserPreferenceData();
        if (currentPrefs.size() == 0) {
            currentPrefs.add(newPreference);
        } else {
            for (int i = 0; i < currentPrefs.size(); i++) {
                if (currentPrefs.get(i).compareDays(newPreference) && currentPrefs.get(i).timeOverlap(newPreference)) {
                    if (currentPrefs.get(i).getStartTime() < newPreference.getStartTime()) {
                        newPreference.setStartTime(currentPrefs.get(i).getStartTime());
                    }
                    if (currentPrefs.get(i).getEndTime() > newPreference.getEndTime()) {
                        newPreference.setEndTime(currentPrefs.get(i).getEndTime());
                    }
                    currentPrefs.add(newPreference);
                }
            }
        }
    }

    public void removePref(UserPreferences newPreference) {
        ArrayList<UserPreferences>  currentPrefs = getUserPreferenceData();
        for (int i = 0; i < currentPrefs.size(); i++) {
            if (currentPrefs.get(i).compareDays(newPreference) && currentPrefs.get(i).timeOverlap(newPreference)) {
                if (currentPrefs.get(i).getStartTime() < newPreference.getEndTime()) {
                    currentPrefs.get(i).setStartTime(newPreference.getEndTime());
                }
                if (currentPrefs.get(i).getEndTime() < newPreference.getStartTime()) {
                    currentPrefs.get(i).setEndTime(currentPrefs.get(i).getStartTime());
                }
                if(currentPrefs.get(i).getStartTime() > newPreference.getStartTime() && currentPrefs.get(i).getEndTime() < newPreference.getEndTime()){
                    currentPrefs.remove(i);
                    i--;
                }

            }
        }
    }


}
