package com.CourseScheduleBuilder.Model;

public class UserPreferences {
private boolean monday;
private boolean tuesday;
private boolean wednesday;
private boolean thursday;
private boolean friday;
private boolean add;
private Integer startTime;
private Integer endTime;

    /*
    UserPreferences can be added or removed using the same object, the boolean "add"
    provides a way to know if the UserPreference Object created should be added or removed
    from the User userPrefs array
    */
    public boolean isAdd() {
        return add;
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
    method to determines if two userPreferences Objects overlap in time
     */

    public boolean timeOverlap(UserPreferences prefToCompare){
        if(startTime == prefToCompare.getStartTime() && endTime == prefToCompare.getEndTime()){
            return true;
        }
        else if(startTime > prefToCompare.getStartTime() && endTime > prefToCompare.getEndTime()){
            return true;
        }
        else if(startTime < prefToCompare.getStartTime() && prefToCompare.getEndTime() > endTime){
            return true;
        }
        else if(startTime > prefToCompare.getStartTime() && endTime < prefToCompare.getEndTime()){
            return true;
        }
        else if(startTime < prefToCompare.getStartTime() && endTime > prefToCompare.getEndTime()){
            return true;
        }
        else{
            return false;
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

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }
}
