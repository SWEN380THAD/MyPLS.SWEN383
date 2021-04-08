package com.springexample.spring;


import com.icegreen.greenmail.util.GreenMail;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.*;

//controls the functionality of the form.ftlh page.
//the variables created here can be passed to the form.ftlh page.
@Controller
public class FormController
{

    @Autowired
    private MailClient mailClient;

    @GetMapping("/form")
    public String formGet() {
        return "form";
    }//returns form page




    @PostMapping("/form")
    public String formPost(User user, String pw2, Model model)  { //this codes runs after a user submits the form on teh form.ftlh page

        String vercode = user.getVerification_code();
       String _page = "index";
        if(user.getPw().equals((pw2))) { //validate that the two passwords on the form match

            //if match add user to system
            Application.dl.connect();
            Application.dl.addUser(Application.dl.encrypt((user.getPw())), user.getEmail(), user.getType(), vercode );//we can change teh datalayer to accept a user as a parameter instead of individual info
            Application.dl.close();
           model.addAttribute("user", user);

       }else{
            // if no match, send user and message back to teh form.ftlh page
            model.addAttribute("user", user);
            model.addAttribute("msg","Passwords do not match");
            _page = "form";
        }

        try {

        }catch(Exception ex){
            System.out.println(ex);
        }
        String message = "please follow this link to verify account localhost:8080/verify/"+vercode;
        //when
        mailClient.prepareAndSend(user.getEmail(), message);
        //then
      //  String content = "<span>" + message + "</span>";
        //assertReceivedMessageContains(content);
        return _page;
    }



}
