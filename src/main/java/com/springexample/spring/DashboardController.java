package com.springexample.spring;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//controls the functionality of the index.ftlh page.
//the variables created here can be passed to the index.ftlh page.
@Controller
public class DashboardController
{
    /*@GetMapping("/")
    public String index() { //redirect page "/" to "/index.ftlh"
      //return "index";
        return "redirect:/index";
    }*/




    @GetMapping("/dashboard")
    public String indexGet( Model model) {

        return "dashboard";
    } //returns index page

    @GetMapping("/dashboard/{email}/{course_id}")
    public String courseGet(@PathVariable("email") String _email,@PathVariable("course_id") String course_id,  RedirectAttributes redirectAttributes ) {
        Application.dl.connect();
        redirectAttributes.addFlashAttribute("course", course_id);
        redirectAttributes.addFlashAttribute("email", _email);
        redirectAttributes.addFlashAttribute("lessonList",Application.dl.getLearnerLessons(course_id));
        Application.dl.close();
        return "redirect:/lessonDashboard";
    } //returns lesson info page

    @PostMapping("/dashboard")
    public String indexPost(String email, String  pw, Model model) { //this codes runs after a user submits the form on teh index.ftlh page
        String _page = "dashboard";
        String _pw = Application.dl.encrypt(pw);//encrypt password given by user
        Application.dl.connect();
        User _user = Application.dl.getUser(email);//retreive all user information based on email
        Application.dl.close();
        if (!_user.getPw().equals(_pw)) { //validate password given against password in database
            //if not true then send back the email and msg of "Wrong Email or Password" to the index page
            model.addAttribute("email", email);
            model.addAttribute("msg", "Wrong Email or Password");
            _page = "index";
        }else{
            //if true, set the user as variable to pass to user dashboard page
            model.addAttribute("user", _user);

        }
        return _page;
    }

    @GetMapping("/lessonDashboard")
    public String lessonGet(Model model ) {
        return "lessonDashboard";
    } //returns lesson info page


}
