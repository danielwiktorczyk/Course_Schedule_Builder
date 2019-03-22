package com.CourseScheduleBuilder.Model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateUserPrefsRequestFromFrontEnd {
    @JsonProperty
    private String day;
    @JsonProperty
    private int prefStartTime;
    @JsonProperty
    private int prefEndTime;
    @JsonProperty
    private boolean add;

    @Override
    public String toString() {
        return "UpdateUserPrefsRequestFromFrontEnd{" +
                ", day='" + day + '\'' +
                ", prefStartTime=" + prefStartTime +
                ", prefEndTime=" + prefEndTime +
                ", add=" + add +
                '}';
    }
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getPrefStartTime() {
        return prefStartTime;
    }

    public void setPrefStartTime(Integer prefStartTime) {
        this.prefStartTime = prefStartTime;
    }

    public int getPrefEndTime() {
        return prefEndTime;
    }

    public void setPrefEndTime(Integer prefEndTime) {
        this.prefEndTime = prefEndTime;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }
}
