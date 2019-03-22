package com.CourseScheduleBuilder.Services;

import com.CourseScheduleBuilder.Model.UserPreferences;
import com.CourseScheduleBuilder.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class UserPreferencesServiceImpl implements UserPreferencesService{

    @Autowired
    private UserRepo userRepo;

    private ArrayList<UserPreferences> sessionPreferences;

    public ArrayList<UserPreferences> getUserPreferences(){
            return sessionPreferences;
    }

    public void destroyCurrentPreferences(){
        Iterator it = sessionPreferences.iterator();

        while (it.hasNext()){
            it.remove();
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
        System.out.println("Array size is now: " + sessionPreferences.size());

        Iterator it = sessionPreferences.iterator();
        while (it.hasNext()){
            System.out.println(it.toString());
        }
    }

    public void addPref(UserPreferences newPreference) {
        ArrayList<UserPreferences>  currentPrefs = sessionPreferences;
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

    public void removePref(UserPreferences newPreference) {
        ArrayList<UserPreferences>  currentPrefs = sessionPreferences;
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
        System.out.println("made a new preference: " + newPreference.getStartTime() + ", " + newPreference.getEndTime() + " " + " No Friday: " + newPreference.isFriday());
        return newPreference;

    }


}
