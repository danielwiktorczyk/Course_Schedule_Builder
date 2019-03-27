The courseData.csv file should be used to populate the Course repository with SOEN, ENGR, ENCS, COMP and ELEC 275 classes.

Most of the data is pretty self explainatory just a couple of notes:
association is a unique 4 digit integer that a course shares with it's associated tutorials, labs are not associated with particular classes so any lad for that class in that semester will do
requireEngineer has three possible values "NOENG" indicates a course that does not have to be taught by an engineer, 
"Y" indicates a course that does have to be taught by an engineer and is taught by one, 
"N" indicates a course that does have to be taught by an engineer and is not. 
startTime and endTime are an integer in minutes from midnight at which the course starts and ends

To add the data:
Download the courseData.csv file and save it to your computer
Launch the backend of the project from intellij
got to localhost:8080/h2
in the JDBC URL box, go to jdbc:h2:~/db and then connect
because we have an existing COURSE database, you won't be able to add the data there so type the command DROP TABLE COURSE.  This removes the existing database
run command CREATE TABLE COURSE AS SELECT * FROM CSVREAD('C:/pathToYourFile/CourseInfo1.csv');
click the "Run" button
to view the data, click on COURSE where it appears in the menu and click run again (you may need to clear previous command input)

