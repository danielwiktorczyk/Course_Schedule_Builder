package com.CourseScheduleBuilder.Services;

import org.springframework.security.core.Authentication;

public interface AuthenticateFacade {
    Authentication getAuthentication();
}
