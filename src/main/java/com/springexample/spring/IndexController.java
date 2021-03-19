package com.springexample.spring;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

//controls the functionality of the index.ftlh page.
//the variables created here can be passed to the index.ftlh page.
@Controller
public class IndexController
{
    public ArrayList<Course> courseList = new ArrayList<Course>();


    @GetMapping("/")
    public String index() { //redirect page "/" to "/index.ftlh"
      //return "index";
        return "redirect:/index";
    }




    @GetMapping("/index")
    public String indexGet() {
        return "index";
    } //returns index page



    @PostMapping("/index")
    public String indexPost(String email, String  pw, Model model, RedirectAttributes redirectAttributes) { //this codes runs after a user submits the form on teh index.ftlh page
        String _page = "dashboard";
        String _pw = Application.dl.encrypt(pw);//encrypt password given by user
        Application.dl.connect();
        Application.currentUser = Application.dl.getUser(email);//retreive all user information based on email

        if (!Application.currentUser.getPw().equals(_pw)) { //validate password given against password in database
            //if not true then send back the email and msg of "Wrong Email or Password" to the index page
            model.addAttribute("email", email);
            model.addAttribute("msg", "Wrong Email or Password");
            _page = "index";
        }else{

            //if true, set the user as variable to pass to user dashboard page
           // redirectAttributes.addFlashAttribute("user", Application.currentUser);
            if(Application.currentUser.getType().equals("Admin")){
                Application.courseList = Application.dl.getAllCourses();
                //redirectAttributes.addFlashAttribute("courseList", Application.dl.getAllCourses());
            }else {
                Application.courseList=Application.dl.getUserCourses(email);
              //  redirectAttributes.addFlashAttribute("courseList", Application.dl.getUserCourses(email));
            }

        }
        Application.dl.close();
        return "redirect:/"+_page;
    }



}
