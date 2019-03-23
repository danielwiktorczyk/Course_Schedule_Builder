package com.CourseScheduleBuilder.Services;

import com.CourseScheduleBuilder.Model.UserPreferences;
import com.CourseScheduleBuilder.Repositories.PreferencesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPreferencesServiceImpl implements UserPreferencesService{

    @Autowired
    private PreferencesRepo preferencesRepo;

    public List<UserPreferences> getUserPreferences(){
            return preferencesRepo.findAll();
    }

    /*
   Method that modifies userPrefs when they are updated by the user
   uses helper methods addPref() and removePref() defined below
    */
    public void modifyUserPrefs(UserPreferences newPreference) {

        if(newPreference.isAdd()){
            System.out.println("in modify userPrefs, add boolean regognized");
            addPref(newPreference);
            System.out.println("newPref added successfully");
        }
        else{
           removePref(newPreference);
        }
    }

    public List<UserPreferences> getCurrentPrefs(UserPreferences newPreference){
        if(newPreference.isMonday()){
            return preferencesRepo.findByMondayIsTrue();
        }
        else if(newPreference.isTuesday()){
            return preferencesRepo.findByTuesdayIsTrue();
        }
        else if(newPreference.isWednesday()){
            return preferencesRepo.findByWednesdayIsTrue();
        }
        else if(newPreference.isThursday()){
            return preferencesRepo.findByThursdayIsTrue();
        }
        else if(newPreference.isFriday()){
            return preferencesRepo.findByFridayIsTrue();
        }
        else{
            return null;
        }
    }

    public void addPref(UserPreferences newPreference) {
        System.out.println("into AddPref()");
        System.out.println("stuff in prefs" + preferencesRepo.findAll().size());
        if (preferencesRepo.findAll().size() == 0) {
            System.out.println("no preferences in repo");
            preferencesRepo.save(newPreference);
        }
        else {
            List<UserPreferences> currentPrefs = getCurrentPrefs(newPreference);
                 for(int i = 0; i <currentPrefs.size(); i++) {
                    if (currentPrefs.get(i).getStartTime() < newPreference.getStartTime()) {
                        newPreference.setStartTime(currentPrefs.get(i).getStartTime());
                    }
                    if (currentPrefs.get(i).getEndTime() > newPreference.getEndTime()) {
                        newPreference.setEndTime(currentPrefs.get(i).getEndTime());
                    }
                     preferencesRepo.save(newPreference);
                }
            }
        }

    public void removePref(UserPreferences newPreference) {
        List<UserPreferences> currentPrefs = getCurrentPrefs(newPreference);

        for (int i = 0; i < currentPrefs.size(); i++) {
            if (currentPrefs.get(i).timeOverlap(newPreference)) {
                if (currentPrefs.get(i).getStartTime() < newPreference.getEndTime()) {
                    currentPrefs.get(i).setStartTime(newPreference.getEndTime());
                }
                if (currentPrefs.get(i).getEndTime() < newPreference.getStartTime()) {
                    currentPrefs.get(i).setEndTime(currentPrefs.get(i).getStartTime());
                }
                if(currentPrefs.get(i).getStartTime() > newPreference.getStartTime() && currentPrefs.get(i).getEndTime() < newPreference.getEndTime()){
                    // TODO: 2019-03-22 remove from repo code
                    currentPrefs.remove(i);
                    i--;
                }

            }
        }
    }

    /*
    Method created a new preference object based on data sent from fe
     */
    public UserPreferences createNewPreferenceFromRequestData(String day, Integer prefStartTime, Integer prefEndTime, Boolean add) {
        UserPreferences newPreference = new UserPreferences();
        System.out.println("params: " + day + " " + prefStartTime + " " + prefEndTime + " " + add);
        if (day.equalsIgnoreCase("monday")) {
            newPreference.setMonday(true);
            newPreference.setTuesday(false);
            newPreference.setWednesday(false);
            newPreference.setThursday(false);
            newPreference.setFriday(false);
        } else if (day.equalsIgnoreCase("tuesday")) {
            newPreference.setTuesday(true);
            newPreference.setMonday(false);
            newPreference.setWednesday(false);
            newPreference.setThursday(false);
            newPreference.setFriday(false);
        } else if (day.equalsIgnoreCase("wednesday")) {
            newPreference.setWednesday(true);
            newPreference.setTuesday(false);
            newPreference.setMonday(false);
            newPreference.setThursday(false);
            newPreference.setFriday(false);
        } else if (day.equalsIgnoreCase("thursday")) {
            newPreference.setThursday(true);
            newPreference.setTuesday(false);
            newPreference.setWednesday(false);
            newPreference.setMonday(false);
            newPreference.setFriday(false);
        } else if (day.equalsIgnoreCase("friday")) {
            newPreference.setFriday(true);
            newPreference.setTuesday(false);
            newPreference.setWednesday(false);
            newPreference.setThursday(false);
            newPreference.setMonday(false);
        } else {
            System.out.println("A day for this preference option was not specified, preferences not updated");
        }

        newPreference.setStartTime(prefStartTime);
        newPreference.setEndTime(prefEndTime);
        if (add) {
            newPreference.setAdd(true);
        } else {
            newPreference.setAdd(false);
        }
        return newPreference;

    }


}
