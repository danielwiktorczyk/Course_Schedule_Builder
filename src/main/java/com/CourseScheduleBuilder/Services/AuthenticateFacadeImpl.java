package com.CourseScheduleBuilder.Services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticateFacadeImpl implements AuthenticateFacade {

    /**
     * this return the SecurityContext of the logged in user
     * so you can get the details
     * @return
     */
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
