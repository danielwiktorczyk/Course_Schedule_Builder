package com.CourseScheduleBuilder.Services;

import com.CourseScheduleBuilder.Model.*;
import com.CourseScheduleBuilder.Repositories.CourseRepo;
import com.CourseScheduleBuilder.Repositories.UserRepo;
import com.CourseScheduleBuilder.Repositories.loggedInUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleBuilderServiceImpl implements ScheduleBuilderService {

        private final CourseRepo courseRepo;
        private final UserRepo userRepo;
        private final loggedInUserRepo login;
        private static Schedule[] savedSchedules = new Schedule[5];
        private static int scheduleCount = 0;

    @Autowired
    public ScheduleBuilderServiceImpl(CourseRepo courseRepo, UserRepo userRepo, loggedInUserRepo login) {
        this.courseRepo = courseRepo;
        this.userRepo = userRepo;
        this.login = login;
    }


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
        for (Object prereq : prereqs) {
            if (coursePrereq.equals(prereq))
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

    public void generateSchedules(String courseName){
        scheduleCount = 0;
        long startTime = System.nanoTime(); //Following this line, a list of possible lectures and one of tutorials are obtained
        List<Course> lectureList = courseRepo.findByNameAndComponentAndTerm(courseName,"LEC","Fall");
        List<Course> tutorialList = courseRepo.findByNameAndComponentAndTerm(courseName,"TUT","Fall");
        List<Course> labList = new ArrayList();
        if (lectureList.get(0).getLabRequired().equals("TRUE")) { //If there is a lab, a list of labs is obtained.
            labList = courseRepo.findByNameAndComponentAndTerm(courseName, "LAB", "Fall");
        }
        /*
        On this next line, the lectures, tutorials and labs are combined into all valid groupings and returned as a single list.
         */
        List<CourseTrio> courseList = groupCourses(lectureList,tutorialList,labList);
        if(savedSchedules[0] == null){ //This is the initial case for when a user add the first course to a schedule. It will be empty and it'll be created for the first time
            savedSchedules = new Schedule[courseList.size()];
            for (int i=0;i<courseList.size();i++){
                savedSchedules[i] = new Schedule();
                savedSchedules[i].insertCourse(courseList.get(i));
            }
            System.out.println("Finish Time : " + (System.nanoTime()-startTime));
            return;
        }
        savedSchedules = addToSchedule(courseList,savedSchedules); //For all courses beyond the initial one, the addToSchedule method is used to see the combinations.
        System.out.println("Finish Time : " + (System.nanoTime()-startTime));
    }


    private Schedule[] addToSchedule(List<CourseTrio> courseList, Schedule[] schedules){
        List<Schedule> scheduleList = new ArrayList<>(); //List that will hold all new schedules objects as they are built
        Schedule newSchedule; //Temporary schedule object where schedules are built.
        for (Schedule schedule : schedules) {   //Each existing schedule will be added to each of the options available in courseList, these are the new courses to be added
            for (int j = 0; j < courseList.size(); j++) {
                try {
                    newSchedule = (Schedule) schedule.clone(); //Original schedule is cloned and stored in newSchedule
                    newSchedule.insertCourse((CourseTrio) courseList.get(j).clone()); //From here a course is added
                    if (validateSchedule2(newSchedule)) { //Schedule validated for time conflicts
                        scheduleList.add(newSchedule); //If schedule is valid it should be saved. If you print the schedule here, all is well. Values are messed up at each loop iteration somehow.
                        // newSchedule.printSchedule();
                    }
                } catch (CloneNotSupportedException e) {
                    System.out.println("Cloning failed");
                }
                //  scheduleList.get(scheduleList.size()-1).printSchedule(); //Code works till here, but once this loop is
            }                                                           // exited, the values of the Tutorial object are changed.
        }
        Schedule[] returnSchedule = new Schedule[scheduleList.size()];
        scheduleList.toArray(returnSchedule); //Schedule transformed to array to be sent back
        // Uncomment to see all the possible schedule options that are returned.
        //The problem lies here, the returned schedules dont match the values added to the list during the operations
        //for (Schedule schedule : returnSchedule)
         //   schedule.printSchedule();

        return returnSchedule;

    }


    private List<CourseTrio> groupCourses(List<Course> lecture, List<Course> tutorial, List<Course> labs){
        List<CourseTrio> courseTrio = new ArrayList<>(); //List where combined courses will be stored until ready for return.
        boolean haslab = lecture.get(0).getLabRequired().equals("TRUE"); //Checking if this course requires a lab versus its first entry
        for (Course course1 : lecture) {
            for (Course course : tutorial) {
                if (!course1.getAssociation().equals(course.getAssociation()))
                    continue; //If tutorial and lecture association invalid, this combination is not saved
                if (!haslab) {
                    courseTrio.add(new CourseTrio(course1, course));
                    continue; //If no lab required, then a lecture and tutorial are stored only
                }
                for (Course lab : labs) {
                    courseTrio.add(new CourseTrio(course1, course, lab));
                    //If a lab is required. Each lab possibilities is associated with the proper lecture/tutorial combination
                }

            }
        }
        return courseTrio;

    }
    public Schedule generateAndShowFirstSchedule(){
        scheduleCount = 0;
        System.out.println("PRINTING SCHEDULE : " + scheduleCount);
        return savedSchedules[scheduleCount];
    }
    public Schedule nextSchedule(){
        if(++scheduleCount < savedSchedules.length) {
            System.out.println("PRINTING SCHEDULE : " + scheduleCount);
            return savedSchedules[scheduleCount];
        }
        else{
            scheduleCount =0;
            System.out.println("PRINTING SCHEDULE : " + scheduleCount);
            return savedSchedules[scheduleCount];
        }
    }

    public Schedule previousSchedule(){
        if(--scheduleCount > 0) {
            System.out.println("PRINTING SCHEDULE : " + scheduleCount);
            return savedSchedules[scheduleCount];
        }
        else {
            scheduleCount = savedSchedules.length-1;
            System.out.println("PRINTING SCHEDULE : " + scheduleCount);
            return savedSchedules[scheduleCount];
        }
    }


    private boolean validateSchedule(Schedule schedule){
        int individualCourses = schedule.getSize()*2 + schedule.labCount();
        Course[] courses = new Course[individualCourses]; //New array where all the course objects will be stored as equals
        int l=0;
        for (int j=0; j<schedule.getSize();j++){ //This loop turns the different courseTrios back into courses
                 courses[l] = schedule.getCourseTrio()[j].getLecture();
                l++;
                courses[l] = schedule.getCourseTrio()[j].getTutorial();
                l++;
                if (schedule.getCourseTrio()[j].isHasLab())
                {
                    courses[l] = schedule.getCourseTrio()[j].getLab();
                    l++;
                }

        } //If the starttime or endtime of a class falls between the start and endtime of another AND on the same day, this returns a false boolean showing that the schedule is invalid
        for (int i=0; i<individualCourses;i++){
            for(int j=0; j<individualCourses;j++){
                if (j==i)
                    continue;
                if (courses[i].getStartTime() <= courses[j].getStartTime() && courses[j].getStartTime() <= courses[i].getEndTime()){
                    if (isClassOnTheSameDay(courses, i, j)) return false;
                }
                if (courses[i].getStartTime() <= courses[j].getEndTime() && courses[j].getEndTime() <= courses[i].getEndTime()){
                    if (isClassOnTheSameDay(courses, i, j)) return false;
                }
            }
        }
        return true;
    }

    private boolean validateSchedule2(Schedule schedule){
        boolean hasLab = schedule.getCourseTrio()[schedule.getSize()-1].isHasLab();
        int labCount = 0;
        if (hasLab)
            labCount = 1;

        int individualCourses = (schedule.getSize()-1)*2 + schedule.labCount()-labCount;
        Course[] courses = new Course[individualCourses]; //New array where all the course objects will be stored as equals
        Course[] courses2 = new Course[2+labCount];
        int l=0;
        int m=0;
        for (int j=0; j<schedule.getSize()-1;j++){ //This loop turns the different courseTrios back into courses
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

        courses2[m++] = schedule.getCourseTrio()[schedule.getSize()-1].getLecture();
        courses2[m++] = schedule.getCourseTrio()[schedule.getSize()-1].getTutorial();
        if(schedule.getCourseTrio()[schedule.getSize()-1].isHasLab())
        courses2[m++] = schedule.getCourseTrio()[schedule.getSize()-1].getLab();


        //If the starttime or endtime of a class falls between the start and endtime of another AND on the same day, this returns a false boolean showing that the schedule is invalid
        for (int i=0; i<courses.length;i++){
            for(int j=0; j<courses2.length;j++){
                if (courses[i].getStartTime() <= courses2[j].getStartTime() && courses2[j].getStartTime() <= courses[i].getEndTime()){
                    if (isClassOnTheSameDay2(courses, courses2,i, j)) return false;
                }
                if (courses[i].getStartTime() <= courses2[j].getEndTime() && courses2[j].getEndTime() <= courses[i].getEndTime()){
                    if (isClassOnTheSameDay2(courses,courses2, i, j)) return false;
                }
            }
        }
        return true;
    }

    private boolean isClassOnTheSameDay(Course[] courses, int i, int j) {
        for(int k=0; k<5; k++) {
            if(courses[i].getClassDays()[k] == courses[j].getClassDays()[k] && courses[i].getClassDays()[k]) {
                return true;
            }
        }
        return false;
    }

    private boolean isClassOnTheSameDay2(Course[] courses,Course[] courses2 ,int i, int j) {
        for(int k=0; k<5; k++) {
            if(courses[i].getClassDays()[k] == courses2[j].getClassDays()[k] && courses[i].getClassDays()[k]) {
                return true;
            }
        }
        return false;
    }

}
