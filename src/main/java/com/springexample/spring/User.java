package com.springexample.spring;

import com.sun.xml.internal.ws.developer.Serialization;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;


//this class creates a user of MyPLS and gets/sets their information.
@Service
public class User {

    private String pw;
    private String email;
    private String type;
    private String user_id;
    private String verification_code = RandomString.make(64) ;







    public String getVerification_code() {
        return verification_code;
    }


    /*public void setVerification_code(String verification_code) {
        this.verification_code = RandomString.make(64);;
    }*/




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

