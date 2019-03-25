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

        @Autowired
        CourseRepo courseRepo;
        @Autowired
        UserRepo userRepo;
        @Autowired
        loggedInUserRepo login;

        private static Schedule[] savedSchedules = new Schedule[5];
        private static int scheduleCount = 0;


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

    public void scheduleGenerator(String string){
        scheduleCount = 0;
        long startTime = System.nanoTime(); //Following this line, a list of possible lectures and one of tutorials are obtained
        List<Course> lectureList = courseRepo.findByNameAndComponentAndTerm(string,"LEC","Fall");
        List<Course> tutorialList = courseRepo.findByNameAndComponentAndTerm(string,"TUT","Fall");
        List<Course> labList = new ArrayList();
        if (lectureList.get(0).getLabRequired().equals("TRUE")) { //If there is a lab, a list of labs is obtained.
            labList = courseRepo.findByNameAndComponentAndTerm(string, "LAB", "Fall");
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
            System.out.println(System.nanoTime()-startTime);
            return;
        }
        savedSchedules = addToSchedule(courseList,savedSchedules); //For all courses beyond the initial one, the addToSchedule method is used to see the combinations.
        System.out.println(System.nanoTime()-startTime);
        return;
    }


    public Schedule[] addToSchedule(List<CourseTrio> courseList,Schedule[] schedules){
        List<Schedule> scheduleList = new ArrayList<>(); //List that will hold all new schedules objects as they are built
        Schedule newSchedule; //Temporary schedule object where schedules are built.
        for(int i=0;i<schedules.length;i++){   //Each existing schedule will be added to each of the options available in courseList, these are the new courses to be added
            for(int j=0;j<courseList.size();j++){
                try {
                    newSchedule = (Schedule) schedules[i].clone(); //Original schedule is cloned and stored in newSchedule
                    newSchedule.insertCourse((CourseTrio) courseList.get(j).clone()); //From here a course is added
                    if (validateSchedule(newSchedule)) { //Schedule validated for time conflicts
                        scheduleList.add(newSchedule); //If schedule is valid it should be saved. If you print the schedule here, all is well. Values are messed up at each loop iteration somehow.

                    }
                } catch(Exception e){
                    System.out.println("Cloning failed");
                }
              //  scheduleList.get(scheduleList.size()-1).printSchedule(); //Code works till here, but once this loop is
            }                                                           // exited, the values of the Tutorial object are changed.
            scheduleList.get(scheduleList.size()-1).printSchedule();
            scheduleList.get(scheduleList.size()-2).printSchedule();
        }
        Schedule[] returnSchedule = new Schedule[scheduleList.size()];
        scheduleList.toArray(returnSchedule); //Schedule transformed to array to be sent back
        /* Uncomment to see all the possible schedule options that are returned.
        The problem lies here, the returned schedules dont match the values added to the list during the operations
          for (int i=0;i<returnSchedule.length;i++)
            returnSchedule[i].printSchedule();
            */
        return returnSchedule;

    }


    public List<CourseTrio> groupCourses(List<Course> lecture, List<Course> tutorial, List<Course> labs){
        List<CourseTrio> courseTrio = new ArrayList<>(); //List where combined courses will be stored until ready for return.
        boolean haslab = lecture.get(0).getLabRequired().equals("TRUE"); //Checking if this course requires a lab versus its first entry
        for (int i=0;i<lecture.size();i++){
            for(int j=0;j<tutorial.size();j++){
                if (!lecture.get(i).getAssociation().equals(tutorial.get(j).getAssociation()))
                    continue; //If tutorial and lecture association invalid, this combination is not saved
                if(!haslab) {
                    courseTrio.add(new CourseTrio(lecture.get(i),tutorial.get(j)));
                    continue; //If no lab required, then a lecture and tutorial are stored only
                }
                for(int k=0; k<labs.size();k++){
                    courseTrio.add(new CourseTrio(lecture.get(i),tutorial.get(j), labs.get(k)));
                    //If a lab is required. Each lab possibilities is associated with the proper lecture/tutorial combination
                }

            }
        }
        return courseTrio;

    }
    public Schedule generateSchedule(){
        scheduleCount = 0;
        System.out.println("PRINTING SCHEDULE : " + scheduleCount);
        return savedSchedules[scheduleCount];
    }
    public Schedule nextSchedule(){
        if(++scheduleCount < savedSchedules.length) {
            System.out.println("PRINTING SCHEDULE : " + scheduleCount);
            return savedSchedules[scheduleCount];
        }
        else
            return new Schedule();
    }

    public Schedule previousSchedule(){
        if(--scheduleCount > 0) {
            System.out.println("PRINTING SCHEDULE : " + scheduleCount);
            return savedSchedules[scheduleCount];
        }
        else return
        new Schedule();
    }


    public boolean validateSchedule(Schedule schedule){
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
