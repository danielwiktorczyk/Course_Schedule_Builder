package com.CourseScheduleBuilder.Repositories;

import com.CourseScheduleBuilder.Model.UserPreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PreferencesRepo extends JpaRepository<UserPreferences, String>{

    List<UserPreferences> findByMondayIsTrue();
    List<UserPreferences> findByTuesdayIsTrue();
    List<UserPreferences> findByWednesdayIsTrue();
    List<UserPreferences> findByThursdayIsTrue();
    List<UserPreferences> findByFridayIsTrue();
    List<UserPreferences> findByStartTime(int StartTime);
    List<UserPreferences> findByEndTime(int EndTime);


}
