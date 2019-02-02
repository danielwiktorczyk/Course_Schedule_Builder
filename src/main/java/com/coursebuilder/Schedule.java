package com.coursebuilder;
import java.util.List;

public class Schedule {

    private List<Section> sections;
    private int scheduleReference;

    public Schedule(List<Section> sections, int scheduleReference) {
        this.sections = sections;
        this.scheduleReference = scheduleReference;
    }

    public int getScheduleReference() {
        return scheduleReference;
    }

    public void setScheduleReference(int scheduleReference) {
        this.scheduleReference = scheduleReference;
    }
}
