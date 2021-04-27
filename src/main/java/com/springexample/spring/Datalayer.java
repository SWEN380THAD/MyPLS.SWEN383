package com.springexample.spring;

import org.springframework.core.io.ClassPathResource;

import javax.swing.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datalayer {


    private Statement stmt;
    private ResultSet rs;
    public Connection conn;
    private String sql;
    public  String DB = "swenproject";


    final String DEFAULT_DRIVER = "com.mysql.cj.jdbc.Driver";

    Datalayer(){//constructor

    }//end constructor

    //establish connection to database, retrun true if connected, else false
    public boolean connect() {

        String URL = "jdbc:mysql://localhost/";
        String USER = "root";
        String PASS = "Sambone11";
        conn = null;

        try {
            conn = DriverManager.getConnection(URL+DB+"?serverTimezone=UTC", USER, PASS);
        }catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }

        return (conn != null);

    }// end connect()

    //close connection to database if any
    public boolean close(){

        if (conn != null) {
            try {

                conn.close();

            } catch (SQLException ex) { /* ignored */}

        }

        return (conn != null);
    }// end close()

    public void testConnection() {
        boolean connected = false;

        try {
            if(!conn.isClosed()) {
              String temporary = "Your DB Connection works.  Team Name: T.H.A.D";
             System.out.println(temporary);
        } else {
            System.out.println("Unable to connect to data source");
        }// end of if
        }// end of try block
        catch (SQLException sqle) {
            System.out.println("\nERROR CAN NOT MAKE CONNECITON");
            System.out.println("ERROR WAS " + sqle + "\n");
            sqle.printStackTrace();
        }// end of catch
    }//end of testConnection method()

    public boolean userNotExist(String _user){
        boolean valid = true;
        try{
            stmt = conn.createStatement();

            sql = "SELECT  * FROM users where email = '"+_user+"'";


            //System.out.println("Statment to be Executed: "+ sql);
            rs = stmt.executeQuery(sql);
          if(rs.next()){

              JOptionPane.showMessageDialog(null, "Username already exists!");
              valid = false;

            }

        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Username already exists!");
            System.out.println("ERROR MESSAGE -> "+sqle);
            System.out.println("ERROR SQLException in getResultSet()");
        }// end catch
        return valid;

    }


    public User getUser(String _email){
        User user = new User();

        String pw = "";
        try{
            stmt = conn.createStatement();

            sql = "SELECT  * FROM users where email = '"+_email+"'";


            System.out.println("Statment to be Executed: "+ sql);
            rs = stmt.executeQuery(sql);
            rs.next();
            //System.out.println("PWD given: "+_pwd);
            //System.out.println("PWD pulled: "+rs.getString(2));
            user.setPw(rs.getString(2));
            user.setEmail(rs.getString(3));
            user.setType(rs.getString(4));
            user.setUser_id(rs.getString(1));






        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Username or Password do not match!");
            System.out.println("ERROR MESSAGE -> "+sqle);
            System.out.println("ERROR SQLException in getResultSet()");
        }// end catch
        return user;
    }// end validate user

    public ArrayList<User> getAllUsers(){

        ArrayList<User> allUsers = new ArrayList<>();

        try{
            stmt = conn.createStatement();

            sql = "SELECT  * FROM users";


            System.out.println("Statment to be Executed: "+ sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                User user = new User();
                //System.out.println("PWD given: "+_pwd);
                //System.out.println("PWD pulled: "+rs.getString(2));
                user.setUser_id(rs.getString(1));
                user.setEmail(rs.getString(3));
                user.setType(rs.getString(4));
                allUsers.add(user);
            }




        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Username or Password do not match!");
            System.out.println("ERROR MESSAGE -> "+sqle);
            System.out.println("ERROR SQLException in getResultSet()");
        }// end catch
        return allUsers;
    }// end validate user


    public ArrayList<User> getUsersByType(String _type){

        ArrayList<User> allUsers = new ArrayList<>();

        try{
            stmt = conn.createStatement();

            sql = "SELECT  * FROM users where type like '"+_type+"'";


            System.out.println("Statment to be Executed: "+ sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                User user = new User();
                //System.out.println("PWD given: "+_pwd);
                //System.out.println("PWD pulled: "+rs.getString(2));
                user.setUser_id(rs.getString(1));
                user.setEmail(rs.getString(3));
                user.setType(rs.getString(4));
                user.setlName(rs.getString(7));
                user.setfName(rs.getString(8));
                allUsers.add(user);
            }




        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Issue with finding Uer type of "+_type);
            System.out.println("ERROR MESSAGE -> "+sqle);
            System.out.println("ERROR SQLException in getResultSet()");
        }// end catch
        return allUsers;
    }// end validate user


    public void verifyUser(String _verificationCode){
        boolean valid = false;

        try{
            stmt = conn.createStatement();

                sql = "SELECT  verificationCode FROM users where verificationCode = '"+_verificationCode+"'";


            System.out.println("Statment to be Executed: "+ sql);
            rs = stmt.executeQuery(sql);
            rs.next();
            //System.out.println("PWD given: "+_pwd);
            //System.out.println("PWD pulled: "+rs.getString(2));
            if(rs.getString(1).equals(_verificationCode)){
                valid = true;

                PreparedStatement prepState = conn.prepareStatement("update users set status =  ?");

                prepState.setString(1, "0");
                System.out.println("Statment to be Executed: " + prepState);

               prepState.executeUpdate();

            }else{
                JOptionPane.showMessageDialog(null, "Username or Password do not match!");
            }



        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Username or Password do not match!");
            System.out.println("ERROR MESSAGE -> "+sqle);
            System.out.println("ERROR SQLException in getResultSet()");
        }// end catch
        //return valid;
    }// end validate user


    //insert learner to table
    public int addUser(  String _pw, String _email, String _type, String _verificationCode){

        int i = 0;

        if(userNotExist(_email)) {
            try {

                PreparedStatement prepState = conn.prepareStatement("INSERT INTO users (status,Password, email, type, verificationCode) values(  ?, ?, ?, ?, ?)");

                prepState.setString(1, "1");

                prepState.setString(2, _pw);
                prepState.setString(3, _email);
                prepState.setString(4, _type);
                prepState.setString(5, _verificationCode);
                System.out.println("Statment to be Executed: " + prepState);

                i = prepState.executeUpdate();
                JOptionPane.showMessageDialog(null, "You have added " + i + " row");


            } catch (SQLException sqle) {
                System.out.println("\nERROR CAN NOT EXECUTE STATMENT");
                System.out.println("ERROR MESSAGE-> " + sqle + "\n");
                sqle.printStackTrace();
            }// end of catch
        }
        return i;

    }// end addUser

    public int addProfessorToCourse(  String _pw, String _email, String _type){
        int i = 0;

        if(userNotExist(_email)) {
            try {

                PreparedStatement prepState = conn.prepareStatement("INSERT INTO professor (status,Password, email, type) values(  ?, ?, ?, ?)");

                prepState.setString(1, "0");

                prepState.setString(2, _pw);
                prepState.setString(3, _email);
                prepState.setString(4, _type);
                System.out.println("Statment to be Executed: " + prepState);

                i = prepState.executeUpdate();
                JOptionPane.showMessageDialog(null, "You have added " + i + " row");


            } catch (SQLException sqle) {
                System.out.println("\nERROR CAN NOT EXECUTE STATMENT");
                System.out.println("ERROR MESSAGE-> " + sqle + "\n");
                sqle.printStackTrace();
            }// end of catch
        }
        return i;

    }// end addProfessor

    public void removeProfessorFromCourse(String _course_id, String _user_id){

        try {

            PreparedStatement prepState = conn.prepareStatement("delete from swenproject.user_courses where user_id = ? and course_id = ?");

            prepState.setString(1, _user_id);

            prepState.setString(2, _course_id);
            System.out.println("Statment to be Executed: " + prepState);

            prepState.executeUpdate();
            //JOptionPane.showMessageDialog(null, "You have added " + i + " row");


        } catch (SQLException sqle) {
            System.out.println("\nERROR CAN NOT EXECUTE STATMENT");
            System.out.println("ERROR MESSAGE-> " + sqle + "\n");
            sqle.printStackTrace();
        }// end of catch
    }

    public void addProfessorToCourse(String _course_id, String _user_id){

        try {

            PreparedStatement prepState = conn.prepareStatement("insert into swenproject.user_courses (user_id, course_id) values (?, ?)");

            prepState.setString(1, _user_id);

            prepState.setString(2, _course_id);
            System.out.println("Statment to be Executed: " + prepState);

            prepState.executeUpdate();
            //JOptionPane.showMessageDialog(null, "You have added " + i + " row");


        } catch (SQLException sqle) {
            System.out.println("\nERROR CAN NOT EXECUTE STATMENT");
            System.out.println("ERROR MESSAGE-> " + sqle + "\n");
            sqle.printStackTrace();
        }// end of catch
    }

    public ArrayList<Course> getUserCourses(String _email){
        ArrayList<Course> _cList = new ArrayList<Course>();



        try{
            stmt = conn.createStatement();

            sql = "SELECT DISTINCT \n" +
                    "name\n" +
                    ",description\n" +
                    ",requierments\n" +
                    ",prereqs \n" +
                    ",c.course_id \n" +
                    "FROM swenproject.users u\n" +
                    " join swenproject.user_courses uc on u.user_id = uc.user_id\n" +
                    " join swenproject.courses c on uc.course_id = c.course_id\n" +
                    " where email like'"+_email+"'";


            System.out.println("Statment to be Executed: "+ sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Course course = new Course();
                course.setName(rs.getString(1));
                course.setDescription(rs.getString(2));
                course.setRequirements(rs.getString(3));
                course.setPrerequisites(rs.getString(4));
                course.setId(rs.getString(5));

                _cList.add(course);

            }






        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Username or Password do not match!");
            System.out.println("ERROR MESSAGE -> "+sqle);
            System.out.println("ERROR SQLException in getResultSet()");
        }// end catch

        return _cList;

    }

    public ArrayList<Course> getAllCourses(){
        ArrayList<Course> _cList = new ArrayList<Course>();


        try{
            stmt = conn.createStatement();

            sql = "SELECT DISTINCT \n" +
                    "name\n" +
                    ",description\n" +
                    ",requierments\n" +
                    ",prereqs \n" +
                    ",c.course_id \n" +
                    ",min(u.user_id)\n" +
                    ",min(u.FName)\n" +
                    ",min(u.LName)\n" +
                    "\n" +
                    "FROM swenproject.courses c\n" +
                    "left join swenproject.user_courses uc on c.course_id = uc.course_id \n" +
                    "left join swenproject.users u on uc.user_id = u.user_id and u.type = 'Professor'\n" +
                    "group by\n" +
                    "name\n" +
                    ",description\n" +
                    ",requierments\n" +
                    ",prereqs \n" +
                    ",c.course_id ";


            System.out.println("Statment to be Executed: "+ sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Course course = new Course();
                course.setName(rs.getString(1));
                course.setDescription(rs.getString(2));
                course.setRequirements(rs.getString(3));
                course.setPrerequisites(rs.getString(4));
                course.setId(rs.getString(5));
                course.setProfessor(rs.getString(6));
                course.setProfessor_fName(rs.getString(7));
                course.setProfessor_lName(rs.getString(8));

                _cList.add(course);

            }






        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Username or Password do not match!");
            System.out.println("ERROR MESSAGE -> "+sqle);
            System.out.println("ERROR SQLException in getResultSet()");
        }// end catch

        return _cList;

    }
    public Course getCourse(String course_id){
        Course course = new Course();


        try{
            stmt = conn.createStatement();

            sql = "SELECT DISTINCT \n" +
                    "name\n" +
                    ",description\n" +
                    ",requierments\n" +
                    ",prereqs \n" +
                    ",c.course_id \n" +
                    ",CONCAT(u.FName,\" \",u.LName) fullName\n" +
                    "\n" +
                    "FROM swenproject.courses c\n" +
                    "left join swenproject.user_courses uc on c.course_id = uc.course_id \n" +
                    "left join swenproject.users u on uc.user_id = u.user_id and u.type = 'Professor'"+
                    "WHERE c.course_id = "+course_id;


            System.out.println("Statment to be Executed: "+ sql);
            rs = stmt.executeQuery(sql);
            rs.next();


                course.setName(rs.getString(1));
                course.setDescription(rs.getString(2));
                course.setRequirements(rs.getString(3));
                course.setPrerequisites(rs.getString(4));
                course.setId(rs.getString(5));










        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Username or Password do not match!");
            System.out.println("ERROR MESSAGE -> "+sqle);
            System.out.println("ERROR SQLException in getResultSet()");
        }// end catch

        return course;

    }
    public User getCourseProfessor(String course_id){
        User user = new User();


        try{
            stmt = conn.createStatement();

            sql = "SELECT DISTINCT \n" +
                    "u.user_id\n" +
                    ",u.email\n" +
                    ",u.LName\n" +
                    ",u.FName\n" +
                    "FROM swenproject.courses c\n" +
                    "left join swenproject.user_courses uc on c.course_id = uc.course_id\n" +
                    "left join swenproject.users u on uc.user_id = u.user_id and u.type = 'Professor'\n" +
                    "WHERE c.course_id = "+course_id+"\n"+
                    "and u.user_id is not null";


            System.out.println("Statment to be Executed: "+ sql);
            rs = stmt.executeQuery(sql);
            rs.next();


            user.setUser_id(rs.getString(1));
            user.setEmail(rs.getString(2));

            user.setlName(rs.getString(3));
            user.setfName(rs.getString(4));










        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Username or Password do not match!");
            System.out.println("ERROR MESSAGE -> "+sqle);
            System.out.println("ERROR SQLException in getResultSet()");
        }// end catch

        return user;

    }
    public void deleteCourse(String courseid){



        try{
            stmt = conn.createStatement();

            sql = "DELETE \n" +
                    "FROM swenproject.courses\n"+
                    "WHERE course_id = "+courseid
                    ;


            System.out.println("Statment to be Executed: "+ sql);
             stmt.executeUpdate(sql);







        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Username or Password do not match!");
            System.out.println("ERROR MESSAGE -> "+sqle);
            System.out.println("ERROR SQLException in getResultSet()");
        }// end catch



    }
    public void updateCourse(Course _course){



        try{
            stmt = conn.createStatement();

            sql = "UPDATE swenproject.courses\n"+
                    "SET "+
                    "name = '"+_course.getName()+"'\n" +
                    ",description = '"+_course.getDescription()+"'\n" +
                    ",requierments = '"+_course.getRequirements()+"'\n" +
                    ",prereqs = '"+_course.getPrerequisites()+"' \n" +
                    "WHERE course_id = "+_course.getId()
            ;


            System.out.println("Statment to be Executed: "+ sql);
            stmt.executeUpdate(sql);







        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Username or Password do not match!");
            System.out.println("ERROR MESSAGE -> "+sqle);
            System.out.println("ERROR SQLException in getResultSet()");
        }// end catch



    }



    public ArrayList<Lesson> getAllLessons(){
        ArrayList<Lesson> _lessonsList = new ArrayList<Lesson>();
        try{
            stmt = conn.createStatement();

            sql =   "  SELECT\n" +
                    "  l.lesson_id\n" +
                    ",l.lesson_name\n" +
                    ",l.minimum_score\n" +
                    ",l.lesson_description\n"+
                    ",l.startDate\n"+
                    ",l.endDate\n" +
                    "    FROM swenproject.lessons l \n"+
                    "    where l.course_id = 0 \n" +
                    "    order by l.lesson_order;";


            System.out.println("Statment to be Executed: "+ sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(rs.getInt(1));
                lesson.setName(rs.getString(2));
                //lesson.setMinimumScore(rs.getString(3));
                lesson.setDescription(rs.getString(4));
                lesson.setStartDate(rs.getString(5));
                lesson.setEndDate(rs.getString(6));


                _lessonsList.add(lesson);

            }






        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Username or Password do not match!");
            System.out.println("ERROR MESSAGE -> "+sqle);
            System.out.println("ERROR SQLException in getResultSet()");
        }// end catch

        return _lessonsList;

    }

    public Lesson getLesson(String _lesson_id){
      Lesson lesson = new Lesson();
        try{
            stmt = conn.createStatement();

            sql =   "  SELECT\n" +
                    "  l.lesson_id\n" +
                    ",l.lesson_name\n" +
                    ",l.minimum_score\n" +
                    ",l.lesson_description\n"+
                    ",l.startDate\n"+
                    ",l.endDate\n" +
                    "    FROM swenproject.lessons l \n"+
                    "    where l.lesson_id = "+_lesson_id+" \n" +
                    "    order by l.lesson_order;";


            System.out.println("Statment to be Executed: "+ sql);
            rs = stmt.executeQuery(sql);
            rs.next();

                lesson.setId(rs.getInt(1));
                lesson.setName(rs.getString(2));
                //lesson.setMinimumScore(rs.getString(3));
                lesson.setDescription(rs.getString(4));
                lesson.setStartDate(rs.getString(5));
                lesson.setEndDate(rs.getString(6));

        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Username or Password do not match!");
            System.out.println("ERROR MESSAGE -> "+sqle);
            System.out.println("ERROR SQLException in getResultSet()");
        }// end catch

        return lesson;

    }

    public ArrayList<Lesson> getLearnerLessons(String _course, String _email){
        ArrayList<Lesson> _lessonsList = new ArrayList<Lesson>();
        try{
            stmt = conn.createStatement();

            sql =   "  SELECT\n" +
                    "    l.lesson_id\n" +
                    ",l.lesson_name\n" +
                    ",l.minimum_score\n" +
                    ",l.lesson_description\n"+
                    ",l.startDate\n"+
                    ",l.endDate\n" +

                    "    FROM swenproject.users u\n" +
                    "    join swenproject.user_courses uc on u.user_id = uc.user_id\n" +
                    "    join swenproject.courses c on uc.course_id = c.course_id\n" +
                    "    join swenproject.lessons l on c.course_id = l.course_id\n" +
                    "    where l.course_id ='"+_course+"'\n" +
                    "    AND u.email ='"+_email+"'\n" +
                    "    order by l.lesson_order;";


            System.out.println("Statement to be Executed: "+ sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(rs.getInt(1));
                lesson.setName(rs.getString(2));
                //lesson.setMinimumScore(rs.getInt(3));
                lesson.setDescription(rs.getString(4));
                lesson.setStartDate(rs.getString(5));
                lesson.setEndDate(rs.getString(6));


                _lessonsList.add(lesson);

            }






        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Username or Password do not match!");
            System.out.println("ERROR MESSAGE -> "+sqle);
            System.out.println("ERROR SQLException in getResultSet()");
        }// end catch

        return _lessonsList;

    }


    public String addCourse(Course _course){
       String cid = "";
        try{
        PreparedStatement prepState = conn.prepareStatement("INSERT INTO courses (description,requierments, prereqs, name) values(  ?, ?, ?, ?)");



        prepState.setString(1, _course.getDescription());
        prepState.setString(2, _course.getRequirements());
        prepState.setString(3, _course.getPrerequisites());
        prepState.setString(4, _course.getName());
        System.out.println("Statment to be Executed: " + prepState);
        prepState.executeUpdate();

           stmt = conn.createStatement();

           sql =   "  SELECT\n" +
                   " Max(course_id)\n" +
                   "FROM courses";



           System.out.println("Statment to be Executed: "+ sql);
           rs = stmt.executeQuery(sql);
           rs.next();


           cid = rs.getString(1);

       } catch (SQLException sqle) {
           System.out.println("\nERROR CAN NOT EXECUTE STATMENT");
           System.out.println("ERROR MESSAGE-> " + sqle + "\n");
           sqle.printStackTrace();
       }// end of catch
      return cid;
    }

    public ArrayList<DiscussionGroup> getAllDiscussions(String _filter){

        ArrayList<DiscussionGroup> _discussionList = new ArrayList<DiscussionGroup>();
        try{
            stmt = conn.createStatement();

            sql =   "  SELECT\n" +
                    " g.group_id\n" +
                    " ,g.group_name\n" +
                    ",g.group_desc\n" +
                    ",g.created\n" +

                    "    FROM swenproject.discussiongroups g\n" +
                    "    WHERE g.group_name like '%"+_filter+"%'\n"+
                    "    or g.group_desc like '%"+_filter+"%'\n"+
                    "    order by g.created;";


            System.out.println("Statement to be Executed: "+ sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                DiscussionGroup group = new DiscussionGroup();
                group.setId(rs.getString(1));
                group.setName(rs.getString(2));
                group.setDescription(rs.getString(3));
                group.setCreateDate(rs.getString(4));


                _discussionList.add(group);

            }






        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Username or Password do not match!");
            System.out.println("ERROR MESSAGE -> "+sqle);
            System.out.println("ERROR SQLException in getResultSet()");
        }// end catch

        return _discussionList;
    }



    public void addDiscussion(DiscussionGroup _discussion){
        try{
            PreparedStatement prepState = conn.prepareStatement("INSERT INTO discussiongroups (group_name, group_desc) values(  ?, ?)");




            prepState.setString(1, _discussion.getName());
            prepState.setString(2, _discussion.getDescription());

            System.out.println("Statement to be Executed: " + prepState);
            prepState.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("\nERROR CAN NOT EXECUTE STATMENT");
            System.out.println("ERROR MESSAGE-> " + sqle + "\n");
            sqle.printStackTrace();
        }// end of catch

    }//end AddDiscussion



    public DiscussionGroup getGroup(String _group_id){
        DiscussionGroup group = new DiscussionGroup();
        ArrayList<User> members = new ArrayList<>();

        try{
            stmt = conn.createStatement();

            sql = "SELECT  \n" +
                    "dg.group_id\n" +
                    ",dg.group_name\n" +
                    ",dg.group_desc\n" +
                    ",dg.created\n" +
                    ",dg.status\n" +
                    ",dg.is_public\n" +
                    ",u.user_id\n" +
                    ",u.email\n" +
                    ",u.type\n" +
                    ",u.status\n" +
                    "FROM\n" +
                    "swenproject.discussiongroups dg\n" +
                    "left join swenproject.group_users gu on dg.group_id = gu.group_id_fk\n" +
                    "left join swenproject.users u on gu.user_id_fk = u.user_id\n" +
                    "where dg.group_id = "+_group_id+";";


            System.out.println("Statment to be Executed: "+ sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                User user = new User();
                user.setUser_id(rs.getString(7));
                user.setEmail(rs.getString(8));
                user.setType(rs.getString(9));
                members.add(user);
                group.setId(rs.getString(1));
                group.setName(rs.getString(2));
                group.setDescription(rs.getString(3));
                group.setCreateDate(rs.getString(4));
                group.setIsPublic(rs.getBoolean(6));
            }
            group.setGroupMembers(members);





        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Username or Password do not match!");
            System.out.println("ERROR MESSAGE -> "+sqle);
            System.out.println("ERROR SQLException in getResultSet()");
        }// end catch
        return group;
    }// end validate user


    public void removeGroupMember(String _group_ID, String _user_id){

        try {

            PreparedStatement prepState = conn.prepareStatement("delete from swenproject.group_users where user_id_fk = ? and group_id_fk = ?");

            prepState.setString(1, _user_id);

            prepState.setString(2, _group_ID);
            System.out.println("Statment to be Executed: " + prepState);

             prepState.executeUpdate();
            //JOptionPane.showMessageDialog(null, "You have added " + i + " row");


        } catch (SQLException sqle) {
            System.out.println("\nERROR CAN NOT EXECUTE STATMENT");
            System.out.println("ERROR MESSAGE-> " + sqle + "\n");
            sqle.printStackTrace();
        }// end of catch
    }

    public void addGroupMember(String _group_ID, String _user_id){

        try {

            PreparedStatement prepState = conn.prepareStatement("insert into swenproject.group_users (user_id_fk, group_id_fk) values (?, ?)");

            prepState.setString(1, _user_id);

            prepState.setString(2, _group_ID);
            System.out.println("Statment to be Executed: " + prepState);

             prepState.executeUpdate();
            //JOptionPane.showMessageDialog(null, "You have added " + i + " row");


        } catch (SQLException sqle) {
            System.out.println("\nERROR CAN NOT EXECUTE STATMENT");
            System.out.println("ERROR MESSAGE-> " + sqle + "\n");
            sqle.printStackTrace();
        }// end of catch
    }

    public ArrayList<Feedback> getAllFeedback(){

        ArrayList<Feedback> _feedbackList = new ArrayList<Feedback>();
        try{
            stmt = conn.createStatement();

            sql =   " SELECT f.feedback\n" +
                    ",f.rating\n" +
                    ",f.type\n" +
                    ",c.name\n" +
                    "FROM swenproject.feedback f\n" +
                    "  join swenproject.courses c on f.feedback_key = c.course_id and f.type = 'course';";


            System.out.println("Statment to be Executed: "+ sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setDescription(rs.getString(1));
                feedback.setRating(rs.getString(2));
                feedback.setType(rs.getString(3));
                feedback.setName(rs.getString(4));


                _feedbackList.add(feedback);

            }


            sql =   " SELECT f.feedback\n" +
                    ",f.rating\n" +
                    ",f.type\n" +
                    ",l.name\n" +
                    "FROM swenproject.feedback f\n" +
                    "  join swenproject.lessons l on f.feedback_key = l.lesson_id and f.type = 'lesson';";


            System.out.println("Statment to be Executed: "+ sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setDescription(rs.getString(1));
                feedback.setRating(rs.getString(2));
                feedback.setType(rs.getString(3));
                feedback.setName(rs.getString(4));


                _feedbackList.add(feedback);

            }






        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Username or Password do not match!");
            System.out.println("ERROR MESSAGE -> "+sqle);
            System.out.println("ERROR SQLException in getResultSet()");
        }// end catch

        return _feedbackList;
    }

    //insert learner to table
    public void addQuiz(  Quiz _quiz){

            int i = 0;

            try {
                stmt = conn.createStatement();
               sql = "INSERT INTO quizzes (quiz_name, lesson_id) values('"+_quiz.getName()+"', 0)";


                System.out.println("Statment to be Executed: " + sql);
                stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                System.out.println("return key: " + i);
                ResultSet rs=stmt.getGeneratedKeys();
                int quizid = 0;
                if(rs.next()){
                    quizid =rs.getInt(1);
                }

                for(QuizQuestion _question: _quiz.getQuestions()){
                    sql = "INSERT INTO quiz_questions (question, quiz_id) values('" + _question.getQuestion() + "'," + quizid + ")";
                    System.out.println("Statment to be Executed: " + sql);
                    stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

                    ResultSet rs1=stmt.getGeneratedKeys();
                    int questionID = 0;
                    if(rs1.next()){
                        questionID=rs1.getInt(1);
                    }
                    for(QuizAnswer _answers: _question.getAnswers()){

                        int myBool = _answers.getIsCorrect()?1:0;
                        sql = "INSERT INTO quiz_answers (question_id, answer, correct) values(" + questionID + ",'" + _answers.getAnswer() + "'," + myBool + ")";
                        System.out.println("Statment to be Executed: " + sql);
                        stmt.executeUpdate(sql);
                    }

                }







            } catch (SQLException sqle) {
                System.out.println("\nERROR CAN NOT EXECUTE STATMENT");
                System.out.println("ERROR MESSAGE-> " + sqle + "\n");
                sqle.printStackTrace();
            }// end of catch




    }// end quiz

    public void addLessonToQuiz(String qid, String lid){
        try{
            PreparedStatement prepState = conn.prepareStatement("UPDATE quizzes SET lesson_id = ? where quiz_id = ?");

            prepState.setString(1, lid);
            prepState.setString(2, qid);

            System.out.println("Statment to be Executed: " + prepState);
            prepState.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("\nERROR CAN NOT EXECUTE STATEMENT");
            System.out.println("ERROR MESSAGE-> " + sqle + "\n");
            sqle.printStackTrace();
        }// end of catch

    }//end addLessonToQuiz

    public void removeLessonFromQuiz( String lid){
        try{
            PreparedStatement prepState = conn.prepareStatement("UPDATE quizzes SET lesson_id = ?  where lesson_id = ?");

            prepState.setString(1, "0");
            prepState.setString(2, lid);


            System.out.println("Statment to be Executed: " + prepState);
            prepState.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("\nERROR CAN NOT EXECUTE STATMENT");
            System.out.println("ERROR MESSAGE-> " + sqle + "\n");
            sqle.printStackTrace();
        }// end of catch

    }//end addLessonToQuiz


    public String getLessonQuizID(int lessonid){
        String quizid = null;
        try{
            stmt = conn.createStatement();
            sql = "SELECT quiz_id \n" +
                    "FROM swenproject.quizzes\n"+
                    "WHERE lesson_id = "+lessonid
            ;
            System.out.println("Statment to be Executed: "+ sql);
            rs = stmt.executeQuery(sql);
            if(rs.next()) {
                quizid = rs.getString(1);
            }

        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Username or Password do not match!");
            System.out.println("ERROR MESSAGE -> "+sqle);
            System.out.println("ERROR SQLException in getResultSet()");
        }// end catch

         return quizid;

    }

    public ArrayList<Quiz> getAllQuizzes(String _lesson_id){

        ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
        if(_lesson_id == ""){_lesson_id = "%";}

        try {
            stmt = conn.createStatement();
            sql = "SELECT * FROM quizzes where lesson_id like '"+_lesson_id+"';";


            System.out.println("Statment to be Executed: " + sql);
            ResultSet rs=stmt.executeQuery(sql);

            while(rs.next()) {
                Quiz q = new Quiz();
                q.setQuizID(rs.getInt(1));
                q.setName(rs.getString(2));
                q.setLessonID(rs.getInt(3));
                quizzes.add(q);

            }


                for(Quiz quiz: quizzes) {
                    sql = "SELECT * FROM quiz_questions where quiz_id = " + quiz.getQuizID() + ";";
                    System.out.println("Statment to be Executed: " + sql);
                    ResultSet rs1 = stmt.executeQuery(sql);

                    while(rs1.next()) {
                        QuizQuestion qq = new QuizQuestion();
                        qq.setQuestionID(rs1.getInt(1));
                        qq.setQuestion(rs1.getString(2));

                        quiz.setQuestions(qq);
                    }
                }

            for(Quiz quiz: quizzes) {
                for(QuizQuestion quizquest : quiz.getQuestions()){
                    sql = "SELECT * FROM quiz_answers where question_id = "+quizquest.getQuestionID()+";";
                    System.out.println("Statment to be Executed: " + sql);
                    ResultSet rs2=stmt.executeQuery(sql);

                    while(rs2.next()) {

                        QuizAnswer qa = new QuizAnswer();
                        qa.setAnswer(rs2.getString(3));
                        qa.setIsCorrect(rs2.getBoolean(4));
                        quizquest.setAnswers(qa);

                    }

                }

            }

        } catch (SQLException sqle) {
            System.out.println("\nERROR CAN NOT EXECUTE STATMENT");
            System.out.println("ERROR MESSAGE-> " + sqle + "\n");
            sqle.printStackTrace();
        }// end of catch


   return quizzes;

    }// end addQuiz



    public Quiz getQuiz(String _quiz_id){

        Quiz q = new Quiz();

        try {
            stmt = conn.createStatement();
            sql = "SELECT * FROM quizzes where quiz_id like '"+_quiz_id+"';";


            System.out.println("Statment to be Executed: " + sql);
            ResultSet rs=stmt.executeQuery(sql);

            while(rs.next()) {

                q.setQuizID(rs.getInt(1));
                q.setName(rs.getString(2));
                q.setLessonID(rs.getInt(3));

            }


                sql = "SELECT * FROM quiz_questions where quiz_id = " + _quiz_id+ ";";
                System.out.println("Statment to be Executed: " + sql);
                ResultSet rs1 = stmt.executeQuery(sql);

                while(rs1.next()) {
                    QuizQuestion qq = new QuizQuestion();
                    qq.setQuestionID(rs1.getInt(1));
                    qq.setQuestion(rs1.getString(2));

                    q.setQuestions(qq);
                }



                for(QuizQuestion quizquest : q.getQuestions()){
                    sql = "SELECT * FROM quiz_answers where question_id = "+quizquest.getQuestionID()+";";
                    System.out.println("Statment to be Executed: " + sql);
                    ResultSet rs2=stmt.executeQuery(sql);

                    while(rs2.next()) {

                        QuizAnswer qa = new QuizAnswer();
                        qa.setAnswerID(rs2.getInt(1));
                        qa.setQuestionID(rs2.getInt(2));
                        qa.setAnswer(rs2.getString(3));
                        qa.setIsCorrect(rs2.getBoolean(4));
                        quizquest.setAnswers(qa);

                    }

                }



        } catch (SQLException sqle) {
            System.out.println("\nERROR CAN NOT EXECUTE STATMENT");
            System.out.println("ERROR MESSAGE-> " + sqle + "\n");
            sqle.printStackTrace();
        }// end of catch


        return q;

    }// end addProfessor

    public ArrayList<String> getCorrectAnswerList(){

        ArrayList<String> correctAnswerList = new ArrayList<String>();

        try {
            stmt = conn.createStatement();

                sql = "SELECT * FROM quiz_answers where correct = 1;";
                System.out.println("Statment to be Executed: " + sql);
                ResultSet rs2=stmt.executeQuery(sql);

                while(rs2.next()) {


                    correctAnswerList.add(rs2.getString(1));


                }





        } catch (SQLException sqle) {
            System.out.println("\nERROR CAN NOT EXECUTE STATMENT");
            System.out.println("ERROR MESSAGE-> " + sqle + "\n");
            sqle.printStackTrace();
        }// end of catch


        return correctAnswerList;

    }// end addProfessor

    public  String encrypt(String secret){//Encrypt password
        String sha1 = "";
        String value = new String(secret);
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(value.getBytes("utf8"));
            sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (Exception e){
            e.printStackTrace();
        }// end of catch

        System.out.println( "The sha1 of \""+ value + "\" is:");
        System.out.println("--->" + sha1 );
        System.out.println();
        return sha1;
    }//end of encrypt

    //insert multimedia to table
    public void addMultimedia(String fileName, int id) {

        try {
            stmt = conn.createStatement();
            sql = "INSERT INTO media (media_link, lecture_id) values('"+ fileName + "', "+ id +" )";
            System.out.println("Statment to be Executed: " + sql);

            stmt.executeUpdate(sql);

        } catch (SQLException sqle) {
            System.out.println("\nERROR CAN NOT EXECUTE STATMENT");
            System.out.println("ERROR MESSAGE-> " + sqle + "\n");
            sqle.printStackTrace();
        }// end of catch




    }// end multimedia
    public void deleteLessonFromCourse( String _course_id, String _lesson_id){
        try{
            PreparedStatement prepState = conn.prepareStatement("delete from lessons where lesson_id = ?  and course_id = ?");

            prepState.setString(1, _lesson_id);
            prepState.setString(2, _course_id);


            System.out.println("Statment to be Executed: " + prepState);
            prepState.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("\nERROR CAN NOT EXECUTE STATMENT");
            System.out.println("ERROR MESSAGE-> " + sqle + "\n");
            sqle.printStackTrace();
        }// end of catch

    }//end addLessonToQuiz

    public int addLesson(  Lesson lesson){

        int i = 0;


            try {

                PreparedStatement prepState = conn.prepareStatement("INSERT INTO lessons (lesson_name,minimum_score, lesson_order, startDate, endDate, lesson_description) values(  ?, ?, ?, ?, ?, ?)");

                prepState.setString(1, lesson.getName());
                prepState.setString(2, "" + lesson.getMinimumScore());
                prepState.setString(3, "0");
                prepState.setString(4, lesson.getStartDate());
                prepState.setString(5, lesson.getEndDate());
                prepState.setString(6, lesson.getDescription());
                System.out.println("Statment to be Executed: " + prepState);

                i = prepState.executeUpdate();
                //JOptionPane.showMessageDialog(null, "You have added " + i + " row");


            } catch (SQLException sqle) {
                System.out.println("\nERROR CAN NOT EXECUTE STATMENT");
                System.out.println("ERROR MESSAGE-> " + sqle + "\n");
                sqle.printStackTrace();
            }// end of catch

        return i;

    }// end addLesson

    public int linkLessonToCourse(  String _lesson_id, String _course_id){

        int i = 0;


        try {

            PreparedStatement prepState = conn.prepareStatement("UPDATE lessons set course_id =  ? WHERE lesson_id = ?");

            prepState.setString(1, _course_id);
            prepState.setString(2, _lesson_id);

            System.out.println("Statment to be Executed: " + prepState);

            i = prepState.executeUpdate();
            //JOptionPane.showMessageDialog(null, "You have added " + i + " row");


        } catch (SQLException sqle) {
            System.out.println("\nERROR CAN NOT EXECUTE STATMENT");
            System.out.println("ERROR MESSAGE-> " + sqle + "\n");
            sqle.printStackTrace();
        }// end of catch

        return i;

    }// end addLesson

    public void updateLesson(Lesson lesson){
        try{
            PreparedStatement prepState = conn.prepareStatement("UPDATE lessons SET lesson_name = ?,minimum_score = ?, startDate = ?, endDate = ?, lesson_description = ? where lesson_id = ?");

            prepState.setString(1, lesson.getName());
            prepState.setString(2, "" + lesson.getMinimumScore());
            prepState.setString(3, lesson.getStartDate());
            prepState.setString(4, lesson.getEndDate());
            prepState.setString(5, lesson.getDescription());
            prepState.setInt(6, lesson.getId());

            System.out.println("Statment to be Executed: " + prepState);
            prepState.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("\nERROR CAN NOT EXECUTE STATMENT");
            System.out.println("ERROR MESSAGE-> " + sqle + "\n");
            sqle.printStackTrace();
        }// end of catch

    }//end addLessonToQuiz

    public ArrayList<String> getMediaLocation(int lesson_id){
        ArrayList<String> media_link = null;
        try{
            stmt = conn.createStatement();
            sql = "SELECT media_link \n" +
                    "FROM swenproject.media\n"+
                    "WHERE lesson_id = "+lesson_id
            ;
            System.out.println("Statment to be Executed: "+ sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                media_link.add(rs.getString(1));
            }

        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Username or Password do not match!");
            System.out.println("ERROR MESSAGE -> "+sqle);
            System.out.println("ERROR SQLException in getResultSet()");
        }// end catch

        return media_link;

    }

    public ArrayList<String> getAllMultimedia(String _leson_id){
        ArrayList<String> _mmList = new ArrayList<String>();

        try{
            stmt = conn.createStatement();

            sql = "SELECT * FROM swenproject.media WHERE lecture_id = " + _leson_id;


            System.out.println("Statment to be Executed: "+ sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()) {

                String[] urlSplit = rs.getString(3).split("static");
                String url = new ClassPathResource(urlSplit[1].substring(1)).getPath();
                _mmList.add(url);
            }

        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Username or Password do not match!");
            System.out.println("ERROR MESSAGE -> "+sqle);
            System.out.println("ERROR SQLException in getResultSet()");
        }// end catch

        return _mmList;

    }

    //insert quiz scores into database
    public void saveQuiz(String user_id, String quiz_id, String quizScore) {

        try {
            stmt = conn.createStatement();
            sql = "INSERT INTO quiz_scores (user_id, quiz_id, quiz_score) values("+ user_id +", " + quiz_id +", " + quizScore + " )";
            System.out.println("Statment to be Executed: " + sql);

            stmt.executeUpdate(sql);

        } catch (SQLException sqle) {
            System.out.println("\nERROR CAN NOT EXECUTE STATMENT");
            System.out.println("ERROR MESSAGE-> " + sqle + "\n");
            sqle.printStackTrace();
        }// end of catch

    }// end multimedia

    public ArrayList<QuizGrades> getQuizScores(String user_id){
        ArrayList<QuizGrades> quizGrades = new ArrayList<QuizGrades>();
        try{
            stmt = conn.createStatement();
            sql = "SELECT * FROM swenproject.quizzes q \n" +
            "join swenproject.quiz_scores qs on q.quiz_id = qs.quiz_id \n" +
            "where qs.user_id = " + user_id;

            System.out.println("Statment to be Executed: "+ sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                QuizGrades grade = new QuizGrades();
                grade.setQuiz_id(rs.getString(1));
                grade.setName(rs.getString(2));
                grade.setLesson_id(rs.getString(3));
                grade.setScore(rs.getString(6));

                quizGrades.add(grade);
            }

        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Username or Password do not match!");
            System.out.println("ERROR MESSAGE -> "+sqle);
            System.out.println("ERROR SQLException in getResultSet()");
        }// end catch

        return quizGrades;

    }

}//end class