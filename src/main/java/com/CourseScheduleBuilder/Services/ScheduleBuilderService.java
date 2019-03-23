package com.CourseScheduleBuilder.Services;

import com.CourseScheduleBuilder.Model.Schedule;

public interface ScheduleBuilderService {
    boolean validatePrerequisites(String course);
    //Schedule[] addToSchedule(Schedule[] schedule, List<Course> lectures,List<Course> tutorial, List<Course> labs ,int possibilities);
    Schedule[] scheduleGenerator(String[] string);
}
