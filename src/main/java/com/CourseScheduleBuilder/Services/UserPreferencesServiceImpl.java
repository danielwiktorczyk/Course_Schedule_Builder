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
    Removes all preferences in the database, needs to be run when the user leaves the schedule builder so the
    preferences start new for each schedule builder session
     */
    public void destroyPreferences(){
        List<UserPreferences> currentPrefs = getUserPreferences();
        for (int i = 0; i < currentPrefs.size(); i++) {
            preferencesRepo.delete(currentPrefs.get(i));
        }
    }

    /*
   Method that modifies userPrefs when they are updated by the user
   uses helper methods addPref() and removePref() defined below
    */
    public void modifyUserPrefs(UserPreferences newPreference) {

        if(newPreference.isAdd()){
            addPref(newPreference);
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
        System.out.println("startTime = " + newPreference.getStartTime() + " endTime = " + newPreference.getEndTime());
        List<UserPreferences> currentPrefs = getCurrentPrefs(newPreference);
        boolean alreadyUpdated = false;
        if (preferencesRepo.findAll().size() == 0) {
            preferencesRepo.save(newPreference);
        }
        else {
            for (int i = 0; i < currentPrefs.size(); i++) {
                UserPreferences prefToUpdate = currentPrefs.get(i);
                if (prefToUpdate.timeOverlap(newPreference)) {
                    if (prefToUpdate.getStartTime() > newPreference.getStartTime() && prefToUpdate.getEndTime() >= newPreference.getEndTime()) {
                        System.out.println("current pref starts after new pref");
                        prefToUpdate.setStartTime(newPreference.getStartTime());
                        System.out.println("prefToUpdate startTime is now: " + prefToUpdate.getStartTime() + " end time is still " + prefToUpdate.getEndTime());
                        preferencesRepo.save(prefToUpdate);
                        alreadyUpdated = true;
                    }
                    if (prefToUpdate.getEndTime() < newPreference.getEndTime() && prefToUpdate.getStartTime() <= newPreference.getStartTime()) {
                        System.out.println("current pref ends before new pref");
                        prefToUpdate.setEndTime(newPreference.getEndTime());
                        System.out.println("prefToUpdate endTime is now: " + prefToUpdate.getEndTime() + " start time is still " + prefToUpdate.getStartTime());
                        preferencesRepo.save(prefToUpdate);
                        alreadyUpdated = true;
                    }
                    if (currentPrefs.get(i).getStartTime() < newPreference.getStartTime() && currentPrefs.get(i).getEndTime() > newPreference.getEndTime()) {
                        alreadyUpdated = true;
                    }
                }

            }
            if (!alreadyUpdated) {
                preferencesRepo.save(newPreference);
            }


        }
    }

    public void removePref(UserPreferences newPreference) {
        List<UserPreferences> currentPrefs = getCurrentPrefs(newPreference);

        for (int i = 0; i < currentPrefs.size(); i++) {
            UserPreferences prefToUpdate = currentPrefs.get(i);

            if (prefToUpdate.timeOverlap(newPreference)) {
                System.out.println("overlap identified");
                if(prefToUpdate.getStartTime() <= newPreference.getStartTime() && prefToUpdate.getEndTime() >= newPreference.getEndTime()){
                    UserPreferences splitPrefFirstHalf = new UserPreferences();
                    UserPreferences splitPrefSecondHalf = new UserPreferences();

                    splitPrefFirstHalf.setMonday(prefToUpdate.isMonday());
                    splitPrefSecondHalf.setMonday(prefToUpdate.isMonday());
                    splitPrefFirstHalf.setTuesday(prefToUpdate.isTuesday());
                    splitPrefSecondHalf.setTuesday(prefToUpdate.isTuesday());
                    splitPrefFirstHalf.setWednesday(prefToUpdate.isWednesday());
                    splitPrefSecondHalf.setWednesday(prefToUpdate.isWednesday());
                    splitPrefFirstHalf.setThursday(prefToUpdate.isThursday());
                    splitPrefSecondHalf.setThursday(prefToUpdate.isThursday());
                    splitPrefFirstHalf.setFriday(prefToUpdate.isFriday());
                    splitPrefSecondHalf.setFriday(prefToUpdate.isFriday());
                    splitPrefFirstHalf.setAdd(true);
                    splitPrefSecondHalf.setAdd(true);

                    splitPrefFirstHalf.setStartTime(prefToUpdate.getStartTime());
                    splitPrefFirstHalf.setEndTime(newPreference.getStartTime());
                    splitPrefSecondHalf.setStartTime(newPreference.getEndTime());
                    splitPrefSecondHalf.setEndTime(prefToUpdate.getEndTime());

                    preferencesRepo.delete(prefToUpdate);
                    preferencesRepo.save(splitPrefFirstHalf);
                    preferencesRepo.save(splitPrefSecondHalf);
                }
                else {
                    if (prefToUpdate.getStartTime() <= newPreference.getStartTime()) {
                        System.out.println("pref to modify starts before the delete start time");
                        prefToUpdate.setEndTime(newPreference.getStartTime());
                        preferencesRepo.save(prefToUpdate);
                    }
                    if (prefToUpdate.getEndTime() >= newPreference.getEndTime()) {
                        System.out.println("pref to modify ends after the delete end time");
                        prefToUpdate.setStartTime(newPreference.getEndTime());
                        preferencesRepo.save(prefToUpdate);
                    }
                    if (prefToUpdate.getStartTime() >= newPreference.getStartTime() && prefToUpdate.getEndTime() <= newPreference.getEndTime()) {
                        preferencesRepo.delete(prefToUpdate);
                    }
                }
            }
        }
    }

    /*
    Method created a new preference object based on data sent from fe
     */
    public UserPreferences createNewPreferenceFromRequestData(String day, Integer prefStartTime, Integer prefEndTime, Boolean add) {
        UserPreferences newPreference = new UserPreferences();
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
