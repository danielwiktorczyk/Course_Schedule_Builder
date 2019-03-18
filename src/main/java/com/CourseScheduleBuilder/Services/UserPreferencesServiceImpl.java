package com.CourseScheduleBuilder.Services;

import com.CourseScheduleBuilder.Model.User;
import com.CourseScheduleBuilder.Model.UserPreferences;
import com.CourseScheduleBuilder.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPreferencesServiceImpl implements UserPreferencesService{

    @Autowired
    private UserRepo userrepo;

    // TODO: 2019-03-18  when the loggedInUser branch is merged this method will be updated to take argument LoggedInUser user
    public void updateUserPreferences(User user, UserPreferences newPreference) {
        user.modifyUserPrefs(newPreference);
        userrepo.save(user);
        System.out.println("User Preferences Updated Successfully");
    }


        //
        // Verification of login info against database to be added.
        // Checks include if username already exists in database.
        // true boolean returned if registration successfully completed
        // false boolean if registration failure (username already exists)
        // FE should declare error if communication failure
        // Prints message to console if duplicated attempted
        // Repository find method returns a null value if the search returns no result
        //

    }
