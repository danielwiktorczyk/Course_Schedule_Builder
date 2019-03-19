package com.CourseScheduleBuilder.Model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateUserPrefsRequestFromFrontEnd {
    @JsonProperty
    private String email;
    @JsonProperty
    private String day;
    @JsonProperty
    private Integer prefStartTime;
    @JsonProperty
    private Integer prefEndTime;
    @JsonProperty
    private boolean add;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getPrefStartTime() {
        return prefStartTime;
    }

    public void setPrefStartTime(Integer prefStartTime) {
        this.prefStartTime = prefStartTime;
    }

    public Integer getPrefEndTime() {
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
