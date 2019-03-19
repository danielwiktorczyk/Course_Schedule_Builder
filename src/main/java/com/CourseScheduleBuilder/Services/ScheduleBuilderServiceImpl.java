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





    //    List<String> course = courseRepo.findByName(info.getCourses());


      //  return false;
    }


    private User retriveUserInfo()
    {
        loggedInUser loginUser;
        User user;

        loginUser = login.findByUser("user");

       user = userRepo.findByEmail(loginUser.getEmail());

  //       nameOfCourseToTake = info.getCourses().get(0);

        //retrieves all the users course already taken
        return user;
    }

    public Schedule[] scheduleGenerator(String[] string){
        int size = string.length;
        List<Course>[] lectureList = new List[size];
        List<Course>[] tutorialList = new List[size];
        List<Course>[] labList = new List[size];
        boolean[] labRequired = new boolean[size];
        int possibilities = 1;
        System.out.println("PHASE 1 -------------------------------");
        for(int i=0;i<size;i++){
            lectureList[i] = courseRepo.findByNameAndComponent(string[i],"LEC");
            System.out.println(lectureList[i].size());
            possibilities *= lectureList[i].size();
            System.out.println(possibilities);
            tutorialList[i] = courseRepo.findByNameAndComponent(string[i],"TUT");
            System.out.println(tutorialList[i].size());
            possibilities *= tutorialList[i].size();
          //  for (int k=0;k<tutorialList[i].size();k++)
            //System.out.println(tutorialList[i].get(k).getName() +"  " +tutorialList[i].get(k).getComponent() + "  "+tutorialList[i].get(k).getAssociation() + "  " +tutorialList[i].get(k).getId());
            System.out.println(possibilities);
            if (lectureList[i].get(0).getLabRequired().equals("TRUE")){
                labList[i] = courseRepo.findByNameAndComponent(string[i], "LAB");
                possibilities *= labList[i].size();
                System.out.println(possibilities);
           //     System.out.println(labList[i].size());
                labRequired[i] = true;
            }
            System.out.println("---------------------------------");
        }
        System.out.println("PHASE 2 -------------------------------");
        System.out.println(possibilities);
        Schedule[] schedules = new Schedule[possibilities];
        for(int i=0;i<possibilities;i++)
            schedules[i] = new Schedule();
        for(int i=0;i<size;i++){
            schedules = addToSchedule(schedules,lectureList[i],possibilities);
            schedules = addToSchedule(schedules,tutorialList[i],possibilities);
            if(labRequired[i]) {
               schedules = addToSchedule(schedules, labList[i], possibilities);
            }

        }

        for(int i=0;i<possibilities;i++)
            schedules[i].adjustLength();
        schedules = verifyTutorials(schedules,string.length);
        System.out.println("PHASE 3 -------------------------------");
        for (int j=0;j<schedules.length;j++) {
            for(int k = 0; k<schedules[j].getCourses().length; k++);
           // System.out.println(schedules[j].getCourses()[k].getName() + "  " + schedules[j].getCourses()[k].getComponent() + "   " + schedules[j].getCourses()[k].getStartTime() + "   " + schedules[j].getCourses()[k].getEndTime() + "   Association Number: " +  schedules[j].getCourses()[k].getAssociation() + "  " +  schedules[j].getCourses()[k].getId());
           // System.out.println("----------------------");
        }
        System.out.println(schedules.length);
        return schedules;
    }
    @Override
    public Schedule[] addToSchedule(Schedule[] schedule, List<Course> course, int possibilities){
                        int size = course.size();
                        for(int i=0;i<possibilities;i++){
                        schedule[i].insertCourse(course.get(i%size));
                    }
                        return schedule;

    }


    public Schedule[] verifyTutorials(Schedule[] schedules, int courses){
        List<Schedule> newSchedule= new ArrayList();
        int valid;
        for(int i=0;i<schedules.length;i++){
            valid = 0;

            for(int j=0;j<schedules[i].getCourses().length;j++){
                if(schedules[i].getCourses()[j].getComponent().equals("LAB") || schedules[i].getCourses()[j].getComponent().equals("TUT"))
                    continue;
                for (int k=j+1;k<schedules[i].getCourses().length;k++) {
                    if (schedules[i].getCourses()[j].getName().equals(schedules[i].getCourses()[k].getName()) && schedules[i].getCourses()[j].getAssociation().equals(schedules[i].getCourses()[k].getAssociation())) {
                    //    System.out.println(schedules[i].getCourses()[j].getName() + "    " + schedules[i].getCourses()[k].getName() +"  " +  schedules[i].getCourses()[j].getAssociation() + "   " + schedules[i].getCourses()[k].getAssociation());
                        valid++;
                    }
                }
             //   System.out.println("Valid value : " + valid);
            }
         //   System.out.println(valid);
            if(valid == courses)
            newSchedule.add(schedules[i]);
         //   System.out.println("----------------------------------");
        }
        Schedule[] ReturnSchedule = new Schedule[newSchedule.size()];
        ReturnSchedule = newSchedule.toArray(ReturnSchedule);

        return ReturnSchedule;
    }
}
