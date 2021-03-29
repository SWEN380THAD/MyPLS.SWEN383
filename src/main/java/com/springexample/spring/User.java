package com.springexample.spring;

//this class creates a user of MyPLS and gets/sets their information.
public class User {

    private String pw;
    private String email;
    private String type;
    private String user_id;



    public String getPw() {//return user password
        return pw;
    }

    public void setPw(String _pw) {//set user password
        this.pw = _pw;
    }
    public String getEmail() {//return user email
        return email;
    }

    public void setEmail(String _email) {//set user email
        this.email = _email;
    }

    public String getType() {//return user type
        return type;
    }

    public void setType(String _type) {//set user type
        this.type = _type;
    }

    public String getUser_id() {//return user type
        return user_id;
    }

    public void setUser_id(String _user_id) {//set user type
        this.user_id = _user_id;
    }
}

