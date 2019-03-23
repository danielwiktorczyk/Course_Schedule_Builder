package com.CourseScheduleBuilder.Services;

import com.CourseScheduleBuilder.Model.*;
import com.CourseScheduleBuilder.Repositories.CourseRepo;
import com.CourseScheduleBuilder.Repositories.UserRepo;
import com.CourseScheduleBuilder.Repositories.loggedInUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleBuilderServiceImpl implements ScheduleBuilderService {

        @Autowired
        CourseRepo courseRepo;
        @Autowired
        UserRepo userRepo;
        @Autowired
        loggedInUserRepo login;

        private static Schedules[] savedSchedules;


    @Override
    public boolean validatePrerequisites(String courseToValidate) {
        User user;
        ArrayList prereqs;

       Course courseInDB = courseRepo.findByNameAndComponent(courseToValidate,"LEC").get(0);
       String coursePrereq = courseInDB.getPreReq();

       if(coursePrereq == null)
            return true;
       user = retriveUserInfo();
        prereqs = user.getPrereqs();
        for(int i=0; i<prereqs.size();i++){
            if (coursePrereq.equals(prereqs.get(i)))
                return true;
        }
        return false;
    }


    private User retriveUserInfo()
    {
        loggedInUser loginUser;
        User user;

        loginUser = login.findByUser("user");

       user = userRepo.findByEmail(loginUser.getEmail());

        return user;
    }

    public Schedule[] scheduleGenerator(String[] string){
        int size = string.length;
        List<Course>[] lectureList = new List[size];
        List<Course>[] tutorialList = new List[size];
        List<Course>[] labList = new List[size];
        int possibilities = 1;

        for(int i=0;i<size;i++){
            lectureList[i] = courseRepo.findByNameAndComponentAndTerm(string[i],"LEC","Fall");
            tutorialList[i] = courseRepo.findByNameAndComponentAndTerm(string[i],"TUT","Fall");
            if (lectureList[i].get(0).getLabRequired().equals("TRUE")){
                labList[i] = courseRepo.findByNameAndComponentAndTerm(string[i], "LAB","Fall");
            }
        }
        List<CourseTrio>[] courseList = new List[size];

        for (int i=0; i<size; i++){
            courseList[i] = groupCourses(lectureList[i],tutorialList[i],labList[i]);
        }

        Schedule[] schedules = new Schedule[courseList[0].size()];

        for(int i=0;i<courseList[0].size();i++){
            schedules[i] = new Schedule();
            schedules[i].insertCourse(courseList[0].get(i));
        }
        if(courseList.length>1){
            for(int i=1;i<courseList.length;i++){
                schedules = addToSchedule(courseList[i],schedules);
            }
        }
        return schedules;
    }


    public Schedule[] addToSchedule(List<CourseTrio> courseList,Schedule[] schedules){
        List<Schedule> scheduleList = new ArrayList();

        for(int i=0;i<schedules.length;i++){
            Schedule[] newSchedule = new Schedule[schedules.length * courseList.size()];
            for(int j=0; j<courseList.size();j++){
                try {
                    newSchedule[j] = (Schedule) schedules[i].clone();
                } catch(Exception e){
                    System.out.println("Cloning failed");
                }
                newSchedule[j].insertCourse(courseList.get(j));
                if (validateSchedule(newSchedule[j])) {
                    scheduleList.add(newSchedule[j]);
                }
            }
        }
        Schedule[] returnSchedule = new Schedule[scheduleList.size()];
        return scheduleList.toArray(returnSchedule);

    }


    public List<CourseTrio> groupCourses(List<Course> lecture, List<Course> tutorial, List<Course> labs){
        List<CourseTrio> courseTrio = new ArrayList<>();
        boolean haslab = lecture.get(0).getLabRequired().equals("TRUE");
        for (int i=0;i<lecture.size();i++){
            for(int j=0;j<tutorial.size();j++){
                if (!lecture.get(i).getAssociation().equals(tutorial.get(j).getAssociation()))
                    continue;
                if(!haslab) {
                    courseTrio.add(new CourseTrio(lecture.get(i),tutorial.get(j)));
                    continue;
                }
                for(int k=0; k<labs.size();k++){
                    courseTrio.add(new CourseTrio(lecture.get(i),tutorial.get(j), labs.get(k)));
                }

            }
        }
        return courseTrio;

    }


    public boolean validateSchedule(Schedule schedule){
        int individualCourses = schedule.getSize()*2 + schedule.labCount();
        Course[] courses = new Course[individualCourses];
        int l=0;
        for (int j=0; j<schedule.getSize();j++){
                 courses[l] = schedule.getCourseTrio()[j].getLecture();
                l++;
                courses[l] = schedule.getCourseTrio()[j].getTutorial();
                l++;
                if (schedule.getCourseTrio()[j].isHasLab())
                {
                    courses[l] = schedule.getCourseTrio()[j].getLab();
                    l++;
                }

        }
        for (int i=0; i<individualCourses;i++){
            for(int j=0; j<individualCourses;j++){
                if (j==i)
                    continue;
                if (courses[i].getStartTime() <= courses[j].getStartTime() && courses[j].getStartTime() <= courses[i].getEndTime()){
                    for(int k=0; k<5; k++) {
                        if(courses[i].getClassDays()[k] == courses[j].getClassDays()[k] && courses[i].getClassDays()[k] == true) {
                            return false;
                        }
                    }
                }
                if (courses[i].getStartTime() <= courses[j].getEndTime() && courses[j].getEndTime() <= courses[i].getEndTime()){
                    for(int k=0; k<5; k++) {
                        if(courses[i].getClassDays()[k] == courses[j].getClassDays()[k] && courses[i].getClassDays()[k] == true) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

}
