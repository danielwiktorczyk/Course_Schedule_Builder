package com.CourseScheduleBuilder.Services;

import com.CourseScheduleBuilder.Model.Course;
import com.CourseScheduleBuilder.Model.CourseTrio;
import com.CourseScheduleBuilder.Model.Schedule;
import com.CourseScheduleBuilder.Model.UserPreferences;

public interface ScheduleBuilderService {
    boolean validatePrerequisites(String course);
    boolean validateCorequisites();
    //Schedule[] addToSchedule(Schedule[] schedule, List<Course> lectures,List<Course> tutorial, List<Course> labs ,int possibilities);
    public void generateSchedules(String string, String semester);
    public Schedule generateAndShowFirstSchedule();
    public Schedule nextSchedule();
    public Schedule previousSchedule();
    public boolean enroll(String semester);
    public void clear();
    public Schedule seeUserSchedule(String semester);
    //void preferredSchedule();
    boolean verifyScheduleForPrefs(Schedule schedule);
    Schedule generateAndShowFirstPrefSchedule();
    Schedule nextPrefSchedule();
    Schedule previousPrefSchedule();
    public String[] coursesTaken();
}
