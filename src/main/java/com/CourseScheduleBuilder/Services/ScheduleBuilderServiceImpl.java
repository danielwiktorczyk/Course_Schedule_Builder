package com.CourseScheduleBuilder.Services;

import com.CourseScheduleBuilder.Model.*;
import com.CourseScheduleBuilder.Repositories.CourseRepo;
import com.CourseScheduleBuilder.Repositories.PreferencesRepo;
import com.CourseScheduleBuilder.Repositories.UserRepo;
import com.CourseScheduleBuilder.Repositories.loggedInUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class ScheduleBuilderServiceImpl implements ScheduleBuilderService {

        private final CourseRepo courseRepo;
        private final UserRepo userRepo;
        private final loggedInUserRepo login;
        private static Schedule[] savedSchedules = new Schedule[5];
        private static int scheduleCount = 0;
        private final PreferencesRepo preferences;
        private static Schedule[] userPreferencesSchedule = new Schedule[5];
        private UserPreferencesService userPreferencesService;

    @Autowired
    public ScheduleBuilderServiceImpl(CourseRepo courseRepo, UserRepo userRepo, loggedInUserRepo login, PreferencesRepo preferences) {
        this.courseRepo = courseRepo;
        this.userRepo = userRepo;
        this.login = login;
        this.preferences = preferences;
    }



    private User retriveUserInfo()
    {
        loggedInUser loginUser;
        User user;

        loginUser = login.findByUser("user");

       user = userRepo.findByUsername(loginUser.getUsername());

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
        if (semester.equals("Winter"))
            user.setWinterSchedule(savedSchedules[scheduleCount]);
        if (semester.equals("Summer"))
            user.setSummerSchedule(savedSchedules[scheduleCount]);
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

    /*
    compares preferences and course times for a courseTrio, returns either unaltered course trio if no preference/course conflict
    or null if there is a conflict between course and pref time
    */
    public boolean checkCourseForUserPreferences(boolean[] prefDays, boolean[] courseDays, Course course, UserPreferences preference){
        //if preference and course are on the same day, check times for overlap
        if (prefDays[0] == courseDays[0] && prefDays[1] == courseDays[1] && prefDays[2] == courseDays[2] && prefDays[3] == courseDays[3] && prefDays[4] == courseDays[4]) {
            //course begins before or at the same time as pref and ends after or at the same time as the pref
            if (course.getStartTime() <= preference.getStartTime() && course.getEndTime() >= preference.getEndTime()) {
                System.out.println("trio removed (course begins before or at the same time as pref and ends after or at the same time as the pref)");
                return true;
            }
            //course starts after pref starts but before it ends
            else if (course.getStartTime() > preference.getStartTime() && preference.getEndTime() > course.getStartTime()) {
                System.out.println("trio removed (course starts after pref starts but before it ends ");
                return true;
            }
            //course starts before pref starts but ends after it starts
            else if (course.getStartTime() < preference.getStartTime() && preference.getStartTime() < course.getEndTime()) {
                System.out.println("trio removed (course starts before pref starts but ends after it starts");
                return true;
            }
            //course starts after pref starts and ends before pref ends
            else if (course.getStartTime() > preference.getStartTime() && preference.getEndTime() > course.getEndTime()) {
                System.out.println("trio removed (course starts after pref starts and ends before pref ends");
                return true;
            }
        }
        return false;
    }

    /*
            This method provides a schedule that takes into account the users time preferences
            if a conflict is found, the trio in conflict is removed from the courseList. The updated courseList is returned

    /*
    @Override
    public void preferredSchedule() {
        //array of previously generated schedules
        Schedule[] scheduleNoPrefs = savedSchedules.clone();
        System.out.println("savedSchedule is length " + scheduleNoPrefs.length);
        userPreferencesSchedule = savedSchedules.clone();
        System.out.println("preferredSchedule is length " + userPreferencesSchedule.length);

        //arraylist of courses
        System.out.println(" deconstructing savedSchedule, creating list of all courses");
        ArrayList<Course> allCourses = new ArrayList<Course>();
        for (int i=0; i<scheduleNoPrefs.length ;i++){
            System.out.println("in first for loop, i=" + i);
            CourseTrio[] allTrios = scheduleNoPrefs[i].getCourseTrio();
            for(int j=0; j < allTrios.length; j++) {
                System.out.println("in second for loop, j=" + j);
                if(allTrios[j] == null){
                    break;
                }
                else {
                    allCourses.add(allTrios[j].getLecture());
                }
                System.out.println("adding lecture" + allTrios[j].getLecture());
                allCourses.add(allTrios[j].getTutorial());
                System.out.println("adding tutorial" + allTrios[j].getTutorial());
                if (allTrios[j].isHasLab()) {
                    allCourses.add(allTrios[j].getLab());
                }
            }
        }
        System.out.println("arraylist of courses made.  Length = " + allCourses.size());

        //need to keep and update trio lists in parallel for recombination
        ArrayList<CourseTrio> courseType1 = new ArrayList<CourseTrio>(Arrays.asList(scheduleNoPrefs[0].getCourseTrio()));
        System.out.println("courseType1 has length " + courseType1.size());
        ArrayList<CourseTrio> courseType2 = new ArrayList<CourseTrio>(Arrays.asList(scheduleNoPrefs[1].getCourseTrio()));
        System.out.println("courseType2 has length " + courseType2.size());
        ArrayList<CourseTrio> courseType3 = new ArrayList<CourseTrio>(Arrays.asList(scheduleNoPrefs[2].getCourseTrio()));
        ArrayList<CourseTrio> courseType4 = new ArrayList<CourseTrio>(Arrays.asList(scheduleNoPrefs[3].getCourseTrio()));
        ArrayList<CourseTrio> courseType5 = new ArrayList<CourseTrio>(Arrays.asList(scheduleNoPrefs[4].getCourseTrio()));
        ArrayList<CourseTrio> courseType6 = new ArrayList<CourseTrio>(Arrays.asList(scheduleNoPrefs[5].getCourseTrio()));

        //Arraylist of user preferences
        System.out.print("arraylist of user preferences being made");
        ArrayList<UserPreferences> preferenceList = new ArrayList<UserPreferences>();
        preferenceList.addAll(preferences.findByMondayIsTrue());
        preferenceList.addAll(preferences.findByTuesdayIsTrue());
        preferenceList.addAll(preferences.findByWednesdayIsTrue());
        preferenceList.addAll(preferences.findByThursdayIsTrue());
        preferenceList.addAll(preferences.findByFridayIsTrue());
        System.out.println("size of prefList: " + preferenceList.size());
        Iterator<UserPreferences> itPrefs = preferenceList.iterator();
        Iterator<Course> itCourses = allCourses.iterator();

        while (itPrefs.hasNext()) {
            //first preference to verify
            UserPreferences prefToVerify = itPrefs.next();
            boolean[] prefDays = prefToVerify.getPreferenceDays();
            while (itCourses.hasNext()) {
                Course courseToVerify = itCourses.next();
                System.out.println("courseToVerify is " + courseToVerify.getName());
                boolean[] courseDays = courseToVerify.getClassDays();
                //logic to check overlap between a preference and a course
                boolean overlap = checkCourseForUserPreferences(prefDays, courseDays, courseToVerify, prefToVerify);
                if (overlap) {
                    System.out.println("Overlap identified");
                    System.out.println("checking courseType1.  size: "  + courseType1.size());
                    updateTrioList(courseToVerify, courseType1);
                    if(!courseType2.isEmpty()) {
                        System.out.println("checking courseType2");
                        updateTrioList(courseToVerify, courseType2);
                    }
                    if(!courseType3.isEmpty()) {
                        updateTrioList(courseToVerify, courseType3);
                    }
                    if(!courseType4.isEmpty()) {
                        updateTrioList(courseToVerify, courseType4);
                    }
                    if(!courseType5.isEmpty()){
                        updateTrioList(courseToVerify, courseType5);
                    }
                    if(!courseType6.isEmpty()){
                        updateTrioList(courseToVerify, courseType6);
                    }
                    System.out.println("eliminated course removed in trio lists");
                }
            }
        }
        System.out.println("all conflicting courses removed");
        System.out.println("recombining remaining courses into a new schedule");
        //store as lists for re combination
        System.out.println("converting ArrayList to Array");
        CourseTrio[] c1Array = courseType1.toArray(new CourseTrio[courseType1.size()]);
        System.out.println("courseType1 size is " + courseType1.size() + ", c1Array is length " + c1Array.length + " first value is" + c1Array[0]);
        CourseTrio[] c2Array = courseType2.toArray(new CourseTrio[courseType2.size()]);
        CourseTrio[] c3Array = courseType3.toArray(new CourseTrio[courseType3.size()]);
        CourseTrio[] c4Array = courseType4.toArray(new CourseTrio[courseType4.size()]);
        CourseTrio[] c5Array = courseType5.toArray(new CourseTrio[courseType5.size()]);
        CourseTrio[] c6Array = courseType6.toArray(new CourseTrio[courseType6.size()]);

        System.out.println("converting Array to List");
        List<CourseTrio> c1List = Arrays.asList(c1Array);
        System.out.println("c1List is length " + c1List.size() +  "first value is " + c1List.get(0));
        List<CourseTrio> c2List = Arrays.asList(c2Array);
        List<CourseTrio> c3List = Arrays.asList(c3Array);
        List<CourseTrio> c4List = Arrays.asList(c4Array);
        List<CourseTrio> c5List = Arrays.asList(c5Array);
        List<CourseTrio> c6List = Arrays.asList(c6Array);

        //recombine
        System.out.println("re combining, making userPreferencesSchedule");
        userPreferencesSchedule = new Schedule[c1List.size()];
        for (int m = 0; m < c1List.size(); m++) {
            userPreferencesSchedule[m] = new Schedule();
            userPreferencesSchedule[m].insertCourse(c1List.get(m));
        }
        if(!c2List.isEmpty()) {
            userPreferencesSchedule = addToSchedule(c2List, userPreferencesSchedule);
        }
        if(!c3List.isEmpty()) {
            userPreferencesSchedule = addToSchedule(c3List, userPreferencesSchedule);
        }
        if(!c4List.isEmpty()) {
            userPreferencesSchedule = addToSchedule(c4List, userPreferencesSchedule);
        }
        if(!c5List.isEmpty()) {
            userPreferencesSchedule = addToSchedule(c5List, userPreferencesSchedule);
        }
        if(!c6List.isEmpty()) {
            userPreferencesSchedule = addToSchedule(c6List, userPreferencesSchedule);
        }
        System.out.println("userpreferences schedule is length " + userPreferencesSchedule.length);
        System.out.println("SUCCESS!!!!!");
    }*/

    /*
    check schedule for the preference conflict
     */

    /*
    method to locate course to be removed
     */
    private boolean findPrefConflict(Course removed, CourseTrio[] ct){
        boolean found = false;
        if(removed.getComponent().equalsIgnoreCase("lec") && ct[0].getLecture().getName().equals(removed.getName())) {
            for (int l = 0; l < ct.length; l++) {
                if (ct[l].getLecture() == null){
                    break;
                }
                if (ct[1].getLecture().equals(removed)) {
                    found = true;
                }
            }
        }
        else if(removed.getComponent().equalsIgnoreCase("tut") && ct[0].getLecture().getName().equals(removed.getName())) {
            for (int l = 0; l < ct.length; l++) {
                System.out.println("tutorial being checked is: " + ct[l]);
                if (ct[l] == null){
                    break;
                }
                if (ct[l].getTutorial().equals(removed)) {
                    found = true;
                }
            }
        }
        else if(removed.getComponent().equalsIgnoreCase("lab") && ct[0].getLecture().getName().equals(removed.getName())) {
            for (int l = 0; l < ct.length; l++) {
                if (ct[l].getTutorial() == null){
                    break;
                }
                if (ct[l].getLab().equals(removed)) {
                    found = true;
                }
            }
        }
        return found;
    }

    /*
    The preferredSchedule version of generateAndShowFirstSchedule, previousSchedule and nextSchedule
     */

    public Schedule generateAndShowFirstPrefSchedule(){
        scheduleCount = 0;
        System.out.println("PRINTING SCHEDULE : " + scheduleCount);
        return userPreferencesSchedule[scheduleCount];
    }

    public Schedule nextPrefSchedule(){
        if(++scheduleCount < userPreferencesSchedule.length) {
            if(validateSchedule(userPreferencesSchedule[scheduleCount])) {
                System.out.println("PRINTING SCHEDULE : " + scheduleCount);
                return userPreferencesSchedule[scheduleCount];
            }
            else{
                return nextPrefSchedule();
            }
        }
        else{
            scheduleCount =0;
            if(validateSchedule(userPreferencesSchedule[scheduleCount])) {
                System.out.println("PRINTING SCHEDULE : " + scheduleCount);
                return userPreferencesSchedule[scheduleCount];
            }
            else{
                return nextPrefSchedule();
            }
        }
    }

    public Schedule previousPrefSchedule(){
        if(--scheduleCount > 0) {
            if(validateSchedule(userPreferencesSchedule[scheduleCount])) {
                System.out.println("PRINTING SCHEDULE : " + scheduleCount);
                return userPreferencesSchedule[scheduleCount];
            }
            else{
                return previousPrefSchedule();
            }
        }
        else {
            scheduleCount = userPreferencesSchedule.length-1;

            if(validateSchedule(userPreferencesSchedule[scheduleCount])) {
                System.out.println("PRINTING SCHEDULE : " + scheduleCount);
                return userPreferencesSchedule[scheduleCount];
            }
            else{
                return previousPrefSchedule();
            }
        }
    }

    @Override
    public void preferredSchedule() {
        //array of previously generated schedules
        Schedule[] scheduleNoPrefs = savedSchedules.clone();
        System.out.println("savedSchedule is length " + scheduleNoPrefs.length);
        userPreferencesSchedule = savedSchedules.clone();
        System.out.println("preferredSchedule is length " + userPreferencesSchedule.length);

        //arraylist of courses
        System.out.println(" deconstructing savedSchedule, creating list of all courses");
        ArrayList<Course> allCourses = new ArrayList<Course>();
        for (int i=0; i<scheduleNoPrefs.length ;i++){
            System.out.println("in first for loop, i=" + i);
            CourseTrio[] allTrios = scheduleNoPrefs[i].getCourseTrio();
            for(int j=0; j < allTrios.length; j++) {
                System.out.println("in second for loop, j=" + j);
                if(allTrios[j] == null){
                    break;
                }
                else {
                    allCourses.add(allTrios[j].getLecture());
                }
                System.out.println("adding lecture" + allTrios[j].getLecture());
                allCourses.add(allTrios[j].getTutorial());
                System.out.println("adding tutorial" + allTrios[j].getTutorial());
                if (allTrios[j].isHasLab()) {
                    allCourses.add(allTrios[j].getLab());
                }
            }
        }
        System.out.println("arraylist of courses made.  Length = " + allCourses.size());

        //turn schedule[] into an arraylist
        ArrayList<Schedule> updatedSchedule = new ArrayList<Schedule>(Arrays.asList(userPreferencesSchedule));
        System.out.println("arraylist of all schedules made.  Length = " + updatedSchedule.size());


        //Arraylist of user preferences
        System.out.print("arraylist of user preferences being made");
        ArrayList<UserPreferences> preferenceList = new ArrayList<UserPreferences>();
        preferenceList.addAll(preferences.findByMondayIsTrue());
        preferenceList.addAll(preferences.findByTuesdayIsTrue());
        preferenceList.addAll(preferences.findByWednesdayIsTrue());
        preferenceList.addAll(preferences.findByThursdayIsTrue());
        preferenceList.addAll(preferences.findByFridayIsTrue());
        System.out.println("size of prefList: " + preferenceList.size());
        Iterator<UserPreferences> itPrefs = preferenceList.iterator();
        Iterator<Course> itCourses = allCourses.iterator();

        while (itPrefs.hasNext()) {
            //first preference to verify
            UserPreferences prefToVerify = itPrefs.next();
            boolean[] prefDays = prefToVerify.getPreferenceDays();
            while (itCourses.hasNext()) {
                Course courseToVerify = itCourses.next();
                System.out.println("courseToVerify is " + courseToVerify.getName());
                boolean[] courseDays = courseToVerify.getClassDays();
                //logic to check overlap between a preference and a course
                boolean overlap = checkCourseForUserPreferences(prefDays, courseDays, courseToVerify, prefToVerify);
                if (overlap) {
                    System.out.println("Overlap identified");
                    System.out.println("checking updatedSchedule");
                    for(int p = 0; p < updatedSchedule.size(); p++){
                        CourseTrio[] trio = updatedSchedule.get(p).getCourseTrio();
                        for(int q = 0; q < trio.length; q++){
                            if(findPrefConflict(courseToVerify, trio)){
                                updatedSchedule.remove(p);
                            }
                        }
                    }
                    System.out.println("eliminated course schedule identified and removed");
                }
            }
        }
        System.out.println("all conflicting courses removed");
        System.out.println("recombining remaining courses into a new schedule");
        System.out.println("converting ArrayList updatedSchedule to Array userPreferencesSchedule");
        userPreferencesSchedule = updatedSchedule.toArray(new Schedule[updatedSchedule.size()]);
        System.out.println("userpreferences schedule is length " + userPreferencesSchedule.length);
        System.out.println("SUCCESS!!!!!");
    }


}
