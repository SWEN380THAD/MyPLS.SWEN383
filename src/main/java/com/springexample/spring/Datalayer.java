package com.springexample.spring;

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
        String USER = "student";
        String PASS = "student";
        conn = null;

        try {
            conn = DriverManager.getConnection(URL+DB+"?serverTimezone=UTC", USER, PASS);
        }catch (SQLException ex) {throw new RuntimeException("Error connecting to the database", ex);}

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

    public boolean validUser(String _user, String _pwd, String _type){
        boolean valid = false;
        String pw = "";
        try{
            stmt = conn.createStatement();

                sql = "SELECT  "+_type+"ID, Password FROM "+_type+" where email = '"+_user+"'";


            System.out.println("Statment to be Executed: "+ sql);
            rs = stmt.executeQuery(sql);
            rs.next();
            //System.out.println("PWD given: "+_pwd);
            //System.out.println("PWD pulled: "+rs.getString(2));
            if(rs.getString(2).equals(_pwd)){
                valid = true;

            }else{
                JOptionPane.showMessageDialog(null, "Username or Password do not match!");
            }

        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Username or Password do not match!");
            System.out.println("ERROR MESSAGE -> "+sqle);
            System.out.println("ERROR SQLException in getResultSet()");
        }// end catch
        return valid;
    }// end validate user


    //insert learner to table
    public int addUser(  String _pw, String _email, String _type){
        int i = 0;

        if(userNotExist(_email)) {
            try {

                PreparedStatement prepState = conn.prepareStatement("INSERT INTO users (status,Password, email, type) values(  ?, ?, ?, ?)");

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
                    "FROM swenproject.courses c";


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
                    "FROM swenproject.courses c\n"+
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
    public ArrayList<Lesson> getLearnerLessons(String _course){
        ArrayList<Lesson> _lessonsList = new ArrayList<Lesson>();
        try{
            stmt = conn.createStatement();

            sql =   "  SELECT\n" +
                    "    l.lesson_id\n" +
                    ",l.name\n" +
                    ",l.minimum_score\n" +

                    "    FROM swenproject.users u\n" +
                    "    join swenproject.user_courses uc on u.user_id = uc.user_id\n" +
                    "    join swenproject.courses c on uc.course_id = c.course_id\n" +
                    "    join swenproject.lessons l on c.course_id = l.course_id\n" +
                    "    where l.course_id ='"+_course+"'\n" +
                    "    order by l.order;";


            System.out.println("Statment to be Executed: "+ sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(rs.getInt(1));
                lesson.setName(rs.getString(2));
                lesson.setMinimumScore(rs.getString(3));


                _lessonsList.add(lesson);

            }






        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Username or Password do not match!");
            System.out.println("ERROR MESSAGE -> "+sqle);
            System.out.println("ERROR SQLException in getResultSet()");
        }// end catch

        return _lessonsList;

    }


    public void addCourse(Course _course){
       try{
        PreparedStatement prepState = conn.prepareStatement("INSERT INTO courses (description,requierments, prereqs, name) values(  ?, ?, ?, ?)");



        prepState.setString(1, _course.getDescription());
        prepState.setString(2, _course.getRequirements());
        prepState.setString(3, _course.getPrerequisites());
        prepState.setString(4, _course.getName());
        System.out.println("Statment to be Executed: " + prepState);
        prepState.executeUpdate();

       } catch (SQLException sqle) {
           System.out.println("\nERROR CAN NOT EXECUTE STATMENT");
           System.out.println("ERROR MESSAGE-> " + sqle + "\n");
           sqle.printStackTrace();
       }// end of catch

    }

    public ArrayList<DiscussionGroup> getAllDiscussions(){

        ArrayList<DiscussionGroup> _discussionList = new ArrayList<DiscussionGroup>();
        try{
            stmt = conn.createStatement();

            sql =   "  SELECT\n" +
                    " g.group_id\n" +
                    " ,g.group_name\n" +
                    ",g.group_desc\n" +
                    ",g.created\n" +

                    "    FROM swenproject.discussiongroups g\n" +
                    "    order by g.created;";


            System.out.println("Statment to be Executed: "+ sql);
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

            System.out.println("Statment to be Executed: " + prepState);
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

}//end class