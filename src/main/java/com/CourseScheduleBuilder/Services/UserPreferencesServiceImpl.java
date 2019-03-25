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

    // TODO: 2019-03-23 add a remove duplicates method if there is time, doesn't affect the code but it's nicer
    public void addPref(UserPreferences newPreference) {
        List<UserPreferences> currentPrefs = getCurrentPrefs(newPreference);
        if (currentPrefs.size() == 0) {
            preferencesRepo.save(newPreference);
        }
        else {
            for (int i = 0; i < currentPrefs.size(); i++) {
                UserPreferences prefToUpdate = currentPrefs.get(i);

                if (prefToUpdate.getStartTime() == newPreference.getStartTime() && prefToUpdate.getEndTime() == newPreference.getEndTime() ) {
                    break;

                } else if (prefToUpdate.getStartTime() <= newPreference.getStartTime() && prefToUpdate.getEndTime() >= newPreference.getEndTime()) {
                    break;

                } else if (prefToUpdate.getStartTime() > newPreference.getStartTime() && newPreference.getEndTime() > prefToUpdate.getStartTime() && newPreference.getEndTime() <= prefToUpdate.getEndTime()) {
                    prefToUpdate.setStartTime(newPreference.getStartTime());
                    preferencesRepo.save(prefToUpdate);

                } else if (newPreference.getStartTime() <= prefToUpdate.getEndTime() && prefToUpdate.getEndTime() < newPreference.getEndTime() && newPreference.getStartTime() >= prefToUpdate.getStartTime()) {
                    prefToUpdate.setEndTime(newPreference.getEndTime());
                    preferencesRepo.save(prefToUpdate);
                } else if (prefToUpdate.getStartTime() > newPreference.getStartTime() && prefToUpdate.getEndTime() < newPreference.getEndTime()) { prefToUpdate.setStartTime(newPreference.getStartTime());
                    prefToUpdate.setEndTime(newPreference.getEndTime());
                    preferencesRepo.save(prefToUpdate);
                }
                else {
                    preferencesRepo.save(newPreference);
                }
            }

        }
    }

    public void removePref(UserPreferences newPreference) {
        List<UserPreferences> currentPrefs = getCurrentPrefs(newPreference);
        if(currentPrefs.size() == 0){
        }
        else{

            for (int i = 0; i < currentPrefs.size(); i++) {
                UserPreferences prefToUpdate = currentPrefs.get(i);

                if (prefToUpdate.getStartTime() >= newPreference.getStartTime() && prefToUpdate.getEndTime() <= newPreference.getEndTime()) {
                    preferencesRepo.delete(prefToUpdate);
                } else if (prefToUpdate.getStartTime() < newPreference.getStartTime() && prefToUpdate.getEndTime() > newPreference.getEndTime()) {
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
                } else if (prefToUpdate.getStartTime() >= newPreference.getStartTime() && newPreference.getEndTime() > prefToUpdate.getStartTime() && newPreference.getEndTime() <= prefToUpdate.getEndTime()) {
                    prefToUpdate.setStartTime(newPreference.getEndTime());
                    preferencesRepo.save(prefToUpdate);
                } else if (newPreference.getStartTime() < prefToUpdate.getEndTime() && prefToUpdate.getEndTime() < newPreference.getEndTime() && newPreference.getStartTime() > prefToUpdate.getStartTime()) {
                    prefToUpdate.setEndTime(newPreference.getStartTime());
                    preferencesRepo.save(prefToUpdate);
                }
                    else if (newPreference.getStartTime() > prefToUpdate.getStartTime() && prefToUpdate.getEndTime() <= newPreference.getEndTime() && newPreference.getStartTime() < prefToUpdate.getEndTime()) {
                        prefToUpdate.setEndTime(newPreference.getStartTime());
                        preferencesRepo.save(prefToUpdate);
                } else {
                    System.out.println("there is no preference set on " + newPreference.getDay() + " between " + newPreference.getStartTime() + " and " + newPreference.getEndTime());
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
