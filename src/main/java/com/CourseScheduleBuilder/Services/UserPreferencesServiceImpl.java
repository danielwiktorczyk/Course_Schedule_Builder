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

    // TODO: 2019-03-18 update to work with LoggedInUser when merged
    private ArrayList<UserPreferences> getUserPreferenceData(String email)
    {
        User user = userRepo.findByEmail(email);
        return user.getUserPrefs();
    }

    /*
   Method that modifies userPrefs when they are updated by the user
   uses helper methods addPref() and removePref() defined below
    */
    public void modifyUserPrefs(UserPreferences newPreference, String userEmail) {
        if(newPreference.isAdd()){
            addPref(newPreference, userEmail);
        }
        else{
            removePref(newPreference, userEmail);
        }
        User updatedUser = userRepo.findByEmail(userEmail);
        System.out.println(updatedUser.getUserPrefs().get(0).getStartTime());
        userRepo.save(updatedUser);
    }

    public void addPref(UserPreferences newPreference, String userEmail) {
        ArrayList<UserPreferences>  currentPrefs = getUserPreferenceData(userEmail);
        if (currentPrefs.size() < 1) {
            currentPrefs.add(newPreference);
        }
        else {
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

    public void removePref(UserPreferences newPreference, String email) {
        ArrayList<UserPreferences>  currentPrefs = getUserPreferenceData(email);
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
