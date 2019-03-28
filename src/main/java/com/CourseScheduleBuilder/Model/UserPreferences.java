package com.CourseScheduleBuilder.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserPreferences {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Integer id;
    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean add;
    private int startTime;
    private int endTime;

    // TODO: 2019-03-25 write some error methods for invalid input (limits on times, invalid entries etc...)
    /*
    UserPreferences can be added or removed using the same object, the boolean "add"
    provides a way to know if the UserPreference Object created should be added or removed
    from the User userPrefs array
    */
    public boolean isAdd() {
        return add;
    }

    @Override
    public String toString() {
        return "UserPreferences{" +
                "monday=" + monday +
                ", tuesday=" + tuesday +
                ", wednesday=" + wednesday +
                ", thursday=" + thursday +
                ", friday=" + friday +
                ", add=" + add +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    /*
        method that compares the day booleans of two UserPreference Objects to determine if they are the same
         */
    public boolean compareDays(UserPreferences prefToCompare){
        if(monday && prefToCompare.monday){
            return true;
        }
        else if(tuesday && prefToCompare.tuesday){
            return true;
        }
        else if(wednesday && prefToCompare.wednesday){
            return true;
        }
        else if(thursday && prefToCompare.thursday){
            return true;
        }
        else if(friday && prefToCompare.friday){
            return true;
        }
        else{
            return false;
        }

    }

    /*
    puts day booleans in an array for direct comparison with course booleans
     */
    public boolean[] getPreferenceDays(){
        boolean[] prefDays = new boolean[5];
        prefDays[0] = monday;
        prefDays[1] = tuesday;
        prefDays[2] = wednesday;
        prefDays[3] = thursday;
        prefDays[4] = friday;
        return prefDays;
    }

    /*
        method that checks day boolean attributes and returns the day as a string
         */
    public String getDay(){
        if(monday ){
            return "Monday";
        }
        else if(tuesday){
            return "Tuesday";
        }
        else if(wednesday){
            return "Wednesday";
        }
        else if(thursday){
            return "Thursday";
        }
        else if(friday){
            return "Friday";
        }
        else{
            return "Day cannot be determined";
        }

    }


    public void setAdd(boolean add) {
        this.add = add;
    }

    public boolean isMonday() {
        return monday;
    }

    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    public boolean isTuesday() {
        return tuesday;
    }

    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    public boolean isWednesday() {
        return wednesday;
    }

    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    public boolean isThursday() {
        return thursday;
    }

    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    public boolean isFriday() {
        return friday;
    }

    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }
}
