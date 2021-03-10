package com.springexample.spring;

import javax.swing.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;

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