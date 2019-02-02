package com.coursebuilder;

public class Section {

    private int startTime; //We have to decide the storage format of these times
    private int endTime;
    private String teacherName;
    private String roomNumber;
    private LabSection lab;

    public Section(){

    }

    public Section(int startTime, int endTime, String teacherName, String roomNumber) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.teacherName = teacherName;
        this.roomNumber = roomNumber;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
