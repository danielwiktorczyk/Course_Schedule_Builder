package com.CourseScheduleBuilder.Services;

import com.CourseScheduleBuilder.Model.*;
import com.CourseScheduleBuilder.Repositories.CourseRepo;
import com.CourseScheduleBuilder.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.CourseScheduleBuilder.Services.UserPreferencesService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class ScheduleBuilderServiceImpl implements ScheduleBuilderService {

          @Autowired
        private final CourseRepo courseRepo;
         @Autowired
        private final UserRepo userRepo;


        private static Schedule[] savedSchedules = new Schedule[5];
        private static int scheduleCount = 0;
        private final UserPreferencesService userPreferencesService;
        private UserPreferences preferences;

    @Autowired
    public ScheduleBuilderServiceImpl(CourseRepo courseRepo, UserRepo userRepo, UserPreferencesService userPreferencesService) {
        this.courseRepo = courseRepo;
        this.userRepo = userRepo;
        this.userPreferencesService = userPreferencesService;

    //    this.login = login;
    }



    private User retriveUserInfo()
    {
        User user = null;

        if(userRepo.findByActive(1).size()>0) {
            user = userRepo.findByActive(1).get(0);
        }
        else{
            System.out.println("Not logged in");
        }
        return user;
    }

    public void generateSchedules(String courseName,String semester){
        scheduleCount = 0;
        long startTime = System.nanoTime(); //Following this line, a list of possible lectures and one of tutorials are obtained
        List<Course> lectureList = courseRepo.findByNameAndComponentAndTerm(courseName,"LEC",semester);
        List<Course> tutorialList = courseRepo.findByNameAndComponentAndTerm(courseName,"TUT",semester);
        List<Course> labList = new ArrayList();
        if (lectureList.get(0).getLabRequired().equals("TRUE")) { //If there is a lab, a list of labs is obtained.
            labList = courseRepo.findByNameAndComponentAndTerm(courseName, "LAB", semester);
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
            if(validateSchedule(savedSchedules[scheduleCount])) {
                System.out.println("PRINTING SCHEDULE : " + scheduleCount);
                return savedSchedules[scheduleCount];
            }
            else{
                return nextSchedule();
            }
        }
        else{
            scheduleCount =0;
            if(validateSchedule(savedSchedules[scheduleCount])) {
                System.out.println("PRINTING SCHEDULE : " + scheduleCount);
                return savedSchedules[scheduleCount];
            }
            else{
                return nextSchedule();
            }
        }
    }

    public Schedule previousSchedule(){
        if(--scheduleCount > 0) {
            if(validateSchedule(savedSchedules[scheduleCount])) {
                System.out.println("PRINTING SCHEDULE : " + scheduleCount);
                return savedSchedules[scheduleCount];
            }
            else{
                return previousSchedule();
            }
        }
        else {
            scheduleCount = savedSchedules.length-1;

            if(validateSchedule(savedSchedules[scheduleCount])) {
                System.out.println("PRINTING SCHEDULE : " + scheduleCount);
                return savedSchedules[scheduleCount];
            }
            else{
                return previousSchedule();
            }
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

    public boolean enroll(String semester){
        User user = retriveUserInfo();
        if (user == null)
            return false;
        if (semester.equals("Fall")) {
            user.setFallSchedule(savedSchedules[scheduleCount]);
            userRepo.saveAndFlush(user);
        }
        if (semester.equals("Winter")) {
            user.setWinterSchedule(savedSchedules[scheduleCount]);
            userRepo.saveAndFlush(user);
        }
        userRepo.saveAndFlush(user);
        if (semester.equals("Summer")) {
            user.setSummerSchedule(savedSchedules[scheduleCount]);
            userRepo.saveAndFlush(user);
        }
        return true;
    }

    public void clear()
    {
        scheduleCount = 0;
        savedSchedules = new Schedule[5];
    }
    public Schedule seeUserSchedule(String semester)
    {
        User user = retriveUserInfo();
        if (semester.equals("Fall"))
            return user.getFallSchedule();
        if(semester.equals("Winter"))
            return user.getWinterSchedule();
        if(semester.equals("Summer"))
            return user.getSummerSchedule();
        return new Schedule();
    }

    @Override
    public boolean validatePrerequisites(String courseToValidate) {
        User user;
        user = retriveUserInfo();

        List<String> previouslyTakenCourses = user.getPrereqs();



        for(int i=0;i<previouslyTakenCourses.size();i++)
        {
            List<Course> equivalentCourseList = courseRepo.findByNameAndComponent(previouslyTakenCourses.get(i), "LEC");

            if (equivalentCourseList.size() > 0 && i < equivalentCourseList.size() && equivalentCourseList.get(i).getEquivalent() != null)
                user.addToPrereqs(equivalentCourseList.get(0).getEquivalent().replaceAll("[ .()]",""));
        }

        for(int i=0;i<previouslyTakenCourses.size();i++)
        {
            System.out.println("User has already taken: "+user.getPrereqs().get(i));
        }

        Course courseToTake = null;
        try {
            courseToTake = courseRepo.findByNameAndComponent(courseToValidate, "LEC").get(0);
        }catch(Exception e)
        {
            System.out.println("Error, course not found in database");
            return false;
        }
        String coursePrereq = courseToTake.getPreReq();
        if(coursePrereq == null)
            return true;

        String[] coursePrerequArray = coursePrereq.replaceAll("[ .()]","").split(",");


        for (int i = 0; i < coursePrerequArray.length; i++) {
            System.out.println("The prerequisiste for "+courseToValidate+" is "+coursePrerequArray[i]);
        }
        ArrayList<String> missingPrereqList = new ArrayList<>();

        for (int i = 0; i < coursePrerequArray.length; i++) {
            if(!previouslyTakenCourses.contains(coursePrerequArray[i]))
                missingPrereqList.add(coursePrerequArray[i]);
        }
        if(missingPrereqList.size()>0)
        {
            System.out.println("Prerequisistes missing: ");
            for (int i = 0; i < missingPrereqList.size(); i++) {
                System.out.println(missingPrereqList.get(i));
            }
            return false;
        }

        return true;
    }

    public boolean validateCorequisites()
    {
        User user;
        user = retriveUserInfo();
        List<String> previouslyTakenCourses = new ArrayList<>();
        List<String> coReqList = new ArrayList<>();
        int success = 0;

        try {
            previouslyTakenCourses = (List<String>) user.getPrereqs().clone();
        } catch (Exception e){

        }

        for (int i = 0; i < savedSchedules[0].getCourseTrio().length; i++)
           {
               previouslyTakenCourses.add(savedSchedules[0].getCourseTrio()[i].getLecture().getName());

               if(savedSchedules[0].getCourseTrio()[i].getLecture().getCoReq() != null)
               {
                   String[] coreqs = savedSchedules[0].getCourseTrio()[i].getLecture().getCoReq().replaceAll("[ .()]","").split(",");
                   coReqList.addAll(Arrays.asList(coreqs));
               }
           }

        /*
         * checks if the courses user has and is taking are enough
         * to take the new courses.
         */
        for (String previouslyTakenCoure : previouslyTakenCourses) {
            if (coReqList.contains(previouslyTakenCoure)) {
                /*
                 * This line is a miracle since it never reaches here
                 * but should increment if corerequisites have been met
                 */
                success++;
            }
        }

        if(success == coReqList.size()) {
            System.out.println("Success");
            return true;
        }
        else
            System.out.println("Number of corequisites not met : " + (coReqList.size()-success));

            return false;
    }

    public String[] coursesTaken(){
        List<String> coursesTaken = new ArrayList<>();
        String[] courseSequence = {"COMP232","COMP248","ENGR201","ENGR213","General Elective","COMP249","ENGR233","SOEN228","SOEN287","Basic Science","COMP348","COMP352","ENCS282", "ENGR202", "Basic Science", "COMP346","ELEC275","ENGR371","SOEN331","SOEN341", "COMP335","ENGR391","SOEN342","SOEN343","SOEN384","SOEN344","SOEN345","SOEN357","SOEN390","Elective1","ENGR301","SOEN321","SOEN390","Elective2","Elective3","ENGR392","SOEN385","SOEN490","Elective4","Elective5"};
        String[] generalElectives = {"ECON201","ECON203","ADMI201","ADMI202","PHIL201","HIST202","COMMS360","ENGL243"};
        String[] basicScienceCourses = {"BIOL206","BIOL261","CHEM217","CHEM221","CIVI231","ELEC321","ENGR242","ENGR243","ENGR251","ENGR361","MECH221","PHYS252","PHYS284","PHYS385"};
        String[] electiveCourses = {"COMP345","COMP353","COMP371","COMP425","COMP426","COMP428","COMP442","COMP445","COMP451","COMP465","COMP472","COMP473","COMP474","COMP478","COMP479","SOEN298","SOEN422","SOEN423", "SOEN448", "SOEN491", "SOEN498","SOEN499","ENGR411"};
        int basicScience = 1;
        int elective = 1;
        List<String> currentCourses = new ArrayList<>();
        try {
            currentCourses = (List<String>) retriveUserInfo().getPrereqs().clone();
        } catch (Exception e){

        }
        for(int i=0;i<currentCourses.size();i++){
            for (int j=0;j<generalElectives.length;j++){
                if(currentCourses.get(i).equals(generalElectives[j]))
                    currentCourses.set(i,"General Elective");
            }
        }

        for(int i=0;i<currentCourses.size();i++){
            for (int j=0;j<basicScienceCourses.length;j++){
                if(currentCourses.get(i).equals(basicScienceCourses[j]))
                    currentCourses.set(i,"Basic Science "+(basicScience++));
            }
        }
        for(int i=0;i<currentCourses.size();i++){
            for (int j=0;j<electiveCourses.length;j++){
                if(currentCourses.get(i).equals(electiveCourses[j]))
                    currentCourses.set(i,"Elective "+(elective++));
            }
        }
        String[] returnArray = new String[coursesTaken.size()];
        return currentCourses.toArray(returnArray);

    }


    public String dropCourse(String courseName, String semester){
        User currentUser = retriveUserInfo();
        Schedule newSchedule = new Schedule();
        if (semester.equals("Fall")) {
            if(currentUser.getFallSchedule() == null)
                return "You are not enrolled in the Fall semester";
            Schedule currentSchedule = currentUser.getFallSchedule();
            int scheduleCount = 0;
            for (int i = 0; i < currentSchedule.getCourseTrio().length; i++) {
                if (!courseName.equals(currentSchedule.getCourseTrio()[i].getLecture().getName())) {
                    newSchedule.insertCourse(currentSchedule.getCourseTrio()[i]);
                    scheduleCount++;
                }

            }
            if(scheduleCount == currentSchedule.getSize())
                return "You are not enrolled in this course";
            newSchedule.adjustLength();
            currentUser.setFallSchedule(newSchedule);
            userRepo.saveAndFlush(currentUser);
            return "Course Dropped";
        }
        if (semester.equals("Winter")) {
            if(currentUser.getWinterSchedule() == null)
                return "You are not enrolled in the Winter semester";
            Schedule currentSchedule = currentUser.getWinterSchedule();
            int scheduleCount = 0;
            for (int i = 0; i < currentSchedule.getCourseTrio().length; i++) {
                if (!courseName.equals(currentSchedule.getCourseTrio()[i].getLecture().getName())) {
                    newSchedule.insertCourse(currentSchedule.getCourseTrio()[i]);
                    scheduleCount++;
                }
            }

            if(scheduleCount == currentSchedule.getSize())
                return "You are not enrolled in this course";
            newSchedule.adjustLength();
            currentUser.setWinterSchedule(newSchedule);
            userRepo.saveAndFlush(currentUser);
            return "Course Dropped";
        }
        if (semester.equals("Summer")) {
            if(currentUser.getSummerSchedule() == null)
                return "You are not enrolled in the Summer semester";
            Schedule currentSchedule = currentUser.getSummerSchedule();
            for (int i = 0; i < currentSchedule.getCourseTrio().length; i++) {
                if (!courseName.equals(currentSchedule.getCourseTrio()[i].getLecture().getName())) {
                    newSchedule.insertCourse(currentSchedule.getCourseTrio()[i]);
                    scheduleCount++;
                }
            }
            if(scheduleCount == currentSchedule.getSize())
                return "You are not enrolled in this course";
            newSchedule.adjustLength();
            currentUser.setSummerSchedule(newSchedule);
            userRepo.saveAndFlush(currentUser);
            return "Course Dropped";
        }
        return "Invalid semester selected";
    }

    public String addCourse(String course, String semester){
        User currentUser = retriveUserInfo();
        if(semester.equals("Fall")){
            if(currentUser.getFallSchedule() == null)
                return "You are not enrolled in the Fall Semester";
            savedSchedules = new Schedule[1];
            scheduleCount = 1;

            for(int i=0; i<currentUser.getFallSchedule().getCourseTrio().length;i++){
                if (currentUser.getFallSchedule().getCourseTrio()[i].getLecture().getName().equals(course))
                    return "You are already enrolled in this Course";
            }
            try {
                savedSchedules[0] = (Schedule) currentUser.getFallSchedule().clone();
            } catch(CloneNotSupportedException e){

            }
            if (!validatePrerequisites(course))
                return "Pre-requisites not Met";
            generateSchedules(course,semester);
            return "Success";

        }
        if(semester.equals("Winter")){
            if(currentUser.getWinterSchedule() == null)
                return "You are not enrolled in the Winter Semester";
            savedSchedules = new Schedule[1];
            scheduleCount = 1;

            for(int i=0; i<currentUser.getWinterSchedule().getCourseTrio().length;i++){
                if (currentUser.getWinterSchedule().getCourseTrio()[i].getLecture().getName().equals(course))
                    return "You are already enrolled in this Course";
            }
            try {
                savedSchedules[0] = (Schedule) currentUser.getWinterSchedule().clone();
            } catch(CloneNotSupportedException e){

            }
            if (validatePrerequisites(course))
                return "Pre-requisites not Met";
            generateSchedules(course,semester);
            return "Success";
        }
        if(semester.equals("Summer")){
            if(currentUser.getSummerSchedule() == null)
                return "You are not enrolled in the Summer Semester";
            savedSchedules = new Schedule[1];
            scheduleCount = 1;
            for(int i=0; i<currentUser.getSummerSchedule().getCourseTrio().length;i++){
                if (currentUser.getSummerSchedule().getCourseTrio()[i].getLecture().getName().equals(course))
                    return "You are already enrolled in this Course";
            }
            try {
                savedSchedules[0] = (Schedule) currentUser.getSummerSchedule().clone();
            } catch(CloneNotSupportedException e){

            }
            if (validatePrerequisites(course))
                return "Pre-requisites not Met";
            generateSchedules(course,semester);
            return "Success";
        }

        return "Invalid Semester Selected";
    }

    /*
       The preferredSchedule version of generateAndShowFirstSchedule, previousSchedule and nextSchedule
    */
    public Schedule generateAndShowFirstPrefSchedule() {
        System.out.println("PRINTING SCHEDULE : " + scheduleCount);
        scheduleCount = 0;
        while (!verifyScheduleForPrefs(savedSchedules[scheduleCount])) {
            scheduleCount++;
        }
        return savedSchedules[scheduleCount];

    }

    public Schedule nextPrefSchedule(){
        if(++scheduleCount < savedSchedules.length) {
            if(verifyScheduleForPrefs(savedSchedules[scheduleCount])) {
                System.out.println("PRINTING SCHEDULE : " + scheduleCount);
                return savedSchedules[scheduleCount];
            }
            else{
                return nextPrefSchedule();
            }
        }
        else{
            scheduleCount =0;
            if(verifyScheduleForPrefs(savedSchedules[scheduleCount])) {
                System.out.println("PRINTING SCHEDULE : " + scheduleCount);
                return savedSchedules[scheduleCount];
            }
            else{
                return nextPrefSchedule();
            }
        }
    }

    public Schedule previousPrefSchedule(){
        if(--scheduleCount > 0) {
            if(verifyScheduleForPrefs(savedSchedules[scheduleCount])) {
                System.out.println("PRINTING SCHEDULE : " + scheduleCount);
                return savedSchedules[scheduleCount];
            }
            else{
                return previousPrefSchedule();
            }
        }
        else {
            scheduleCount = savedSchedules.length-1;

            if(validateSchedule(savedSchedules[scheduleCount])) {
                System.out.println("PRINTING SCHEDULE : " + scheduleCount);
                return savedSchedules[scheduleCount];
            }
            else{
                return previousPrefSchedule();
            }
        }
    }
    /*
       compares preferences and course times for a courseTrio, returns either unaltered course trio if no preference/course conflict
       or null if there is a conflict between course and pref time
       */
    public boolean compareCourseAndPrefTime(Course course, int prefStart, int prefEnd){
        //course begins before or at the same time as pref and ends after or at the same time as the pref
        if (course.getStartTime() <= prefStart && course.getEndTime() >= prefEnd) {
            return true;
        }
        //course starts after pref starts but before it ends
        else if (course.getStartTime() > prefStart && prefEnd > course.getStartTime()) {
            return true;
        }
        //course starts before pref starts but ends after it starts
        else if (course.getStartTime() < prefStart && prefStart < course.getEndTime()) {
            return true;
        }
        //course starts after pref starts and ends before pref ends
        else if (course.getStartTime() > prefStart && prefEnd > course.getEndTime()) {
            return true;
        }
        return false;
    }

        /*
    gives specific times to booleans from fe and compares them to schedule values:
    morning is 420-720 (7am to noon)
    evening is 1020-1380 (5pm-11pm)
    all day is 420-1380 (7am-11pm)
     */

    public boolean hasOverlap(Course courseToVerify, boolean[] courseDays, UserPreferences preferences) {
        if(courseDays[0]) {
            if (preferences.isMall() || preferences.isMe() || preferences.isMm()) {
                int[] prefTimes = userPreferencesService.getMonday();
                for (int i = 0; i < prefTimes.length / 2; i = i + 2) {
                    if (compareCourseAndPrefTime(courseToVerify, prefTimes[i], prefTimes[i + 1])) {
                        return true;
                    }

                }
            }
        }
        if(courseDays[1]) {
            if (preferences.isTall() || preferences.isTe() || preferences.isTm()) {
                int[] prefTimes = userPreferencesService.getTuesday();
                for (int i = 0; i < prefTimes.length / 2; i = i + 2) {
                    if (compareCourseAndPrefTime(courseToVerify, prefTimes[i], prefTimes[i + 1])) {
                        return true;
                    }

                }
            }
        }
        if(courseDays[2]) {
            if (preferences.isWall() || preferences.isWe() || preferences.isWm()) {
                int[] prefTimes = userPreferencesService.getWednesday();
                for (int i = 0; i < prefTimes.length / 2; i = i + 2) {
                    if (compareCourseAndPrefTime(courseToVerify, prefTimes[i], prefTimes[i + 1])) {
                        return true;
                    }

                }
            }
        }
        if(courseDays[3]) {
            if (preferences.isThall() || preferences.isThe() || preferences.isThm()) {
                int[] prefTimes = userPreferencesService.getThursday();
                for (int i = 0; i < prefTimes.length / 2; i = i + 2) {
                    if (compareCourseAndPrefTime(courseToVerify, prefTimes[i], prefTimes[i + 1])) {
                        return true;
                    }

                }
            }
        }
        if(courseDays[4]) {
            if (preferences.isFall() || preferences.isFe() || preferences.isFm()) {
                int[] prefTimes = userPreferencesService.getFriday();
                for (int i = 0; i < prefTimes.length / 2; i = i + 2) {
                    if (compareCourseAndPrefTime(courseToVerify, prefTimes[i], prefTimes[i + 1])) {
                        return true;
                    }

                }
            }
        }
        return false;
    }

    @Override
    public boolean verifyScheduleForPrefs(Schedule scheduleToVerify) {
        //arraylist of courses in schedule
        ArrayList<Course> allCourses = new ArrayList<Course>();
        CourseTrio[] allTrios = scheduleToVerify.getCourseTrio();
        for (int i = 0; i < allTrios.length; i++) {
            if (allTrios[i] == null) {
                break;
            } else {
                allCourses.add(allTrios[i].getLecture());
                allCourses.add(allTrios[i].getTutorial());
                if (allTrios[i].isHasLab()) {
                    allCourses.add(allTrios[i].getLab());
                }
            }
        }
        Iterator<Course> itCourses = allCourses.iterator();
        while (itCourses.hasNext()) {
            Course courseToVerify = itCourses.next();
            boolean[] courseDays = courseToVerify.getClassDays();
            //logic to check overlap between a preference and a course
            preferences = userPreferencesService.getCurrentPreferences();
            boolean overlap = hasOverlap(courseToVerify, courseDays, preferences);
            if (overlap) {
                return false;
            }
        }
        return true;
    }

}
