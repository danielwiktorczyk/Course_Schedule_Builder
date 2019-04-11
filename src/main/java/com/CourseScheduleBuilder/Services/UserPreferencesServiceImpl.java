package com.CourseScheduleBuilder.Services;

import com.CourseScheduleBuilder.Model.UserPreferences;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserPreferencesServiceImpl implements UserPreferencesService{

    private UserPreferences currentPreferences = new UserPreferences();
    private boolean toggle = false;


    public boolean isToggle() {
        return toggle;
    }

    public void setToggle(boolean toggle) {
        this.toggle = toggle;
    }

    public UserPreferences getCurrentPreferences(){
        return currentPreferences;
    }
            /*
    creates preference object that has all the user preference information
     */
    public boolean createPreferences(boolean mm, boolean me, boolean mall, boolean tm, boolean te, boolean tall, boolean wm, boolean we, boolean wall, boolean thm, boolean the, boolean thall, boolean fm, boolean fe, boolean fall){
        currentPreferences = new UserPreferences(mm, me, mall, tm, te, tall, wm, we, wall, thm, the, thall, fm, fe, fall);
        if(preferencesPresent()){
            return true;
        }
        else{
            return false;
        }
    }

    /*
    Removes all preferences by returning currentPreferences to the default with all values equal to false
     */
    public void destroyPreferences(){
        currentPreferences = new UserPreferences();
    }

    /*
    method to determine if there are any current preferences
     */
    public boolean preferencesPresent(){
        if(currentPreferences.isMm()||currentPreferences.isMe()||currentPreferences.isMall()||currentPreferences.isTm()||currentPreferences.isTe()||currentPreferences.isTall()
                ||currentPreferences.isWm()||currentPreferences.isWe()||currentPreferences.isWall()||currentPreferences.isThm()||currentPreferences.isThe()||currentPreferences.isThall()
                ||currentPreferences.isFm()||currentPreferences.isFe()||currentPreferences.isFall()){
            return true;
        }
        else {
            return false;
        }
    }
    /*
       gives specific times to booleans from fe:
       morning is 420-720 (7am to noon)
       evening is 1020-1380 (5pm-11pm)
       all day is 420-1380 (7am-11pm)
       returns an array [startTime, endTime]
       if both morning and evening are present on the same day, returns an array [startTimeM, endTimeM, startTimeE, endTimeE]
        */
    public int[] getMonday(){
        int[] times;
        if(currentPreferences.isMe() && currentPreferences.isMm()){
            times = new int[]{420, 720, 1020, 1380};
        }
        else{
            if(currentPreferences.isMall()){
                times = new int[]{420, 1380};
            }
            else if(currentPreferences.isMm()){
                times = new int[]{420, 720};
            }
            else{
                times = new int[]{1020, 1380};
            }
        }
        return times;
    }

    public int[] getTuesday(){
        int[] times;
        if(currentPreferences.isTe() && currentPreferences.isTm()){
            times = new int[]{420, 720, 1020, 1380};
        }
        else{
            if(currentPreferences.isTall()){
                times = new int[]{420, 1380};
            }
            else if(currentPreferences.isTm()){
                times = new int[]{420, 720};
            }
            else{
                times = new int[]{1020, 1380};
            }
        }
        return times;
    }
    public int[] getWednesday(){
        int[] times;
        if(currentPreferences.isWe() && currentPreferences.isWm()){
            times = new int[]{420, 720, 1020, 1380};
        }
        else{
            if(currentPreferences.isWall()){
                times = new int[]{420, 1380};
            }
            else if(currentPreferences.isWm()){
                times = new int[]{420, 720};
            }
            else{
                times = new int[]{1020, 1380};
            }
        }
        return times;
    }
    public int[] getThursday(){
        int[] times;
        if(currentPreferences.isThe() && currentPreferences.isThm()){
            times = new int[]{420, 720, 1020, 1380};
        }
        else{
            if(currentPreferences.isThall()){
                times = new int[]{420, 1380};
            }
            else if(currentPreferences.isThm()){
                times = new int[]{420, 720};
            }
            else{
                times = new int[]{1020, 1380};
            }
        }
        return times;
    }
    public int[] getFriday(){
        int[] times;
        if(currentPreferences.isFe() && currentPreferences.isFm()){
            times = new int[]{420, 720, 1020, 1380};
        }
        else{
            if(currentPreferences.isFall()){
                times = new int[]{420, 1380};
            }
            else if(currentPreferences.isFm()){
                times = new int[]{420, 720};
            }
            else{
                times = new int[]{1020, 1380};
            }
        }
        return times;
    }

}
