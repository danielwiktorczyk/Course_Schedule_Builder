package com.CourseScheduleBuilder.Services;

import com.CourseScheduleBuilder.Model.Course;
import com.CourseScheduleBuilder.Model.Schedule;

import java.util.List;

public interface ScheduleBuilderService {
    boolean validatePrerequisites(String course);
    Schedule[] addToSchedule(Schedule[] schedule, List<Course> course, int possibilities);
    Schedule[] scheduleGenerator(String[] string);
}
