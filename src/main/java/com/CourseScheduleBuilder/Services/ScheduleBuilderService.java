package com.CourseScheduleBuilder.Services;

import com.CourseScheduleBuilder.Model.InfoSession;

public interface ScheduleBuilderService {
    boolean validatePrerequisites(String course);
}
