package com.springexample.spring;

import org.omg.CORBA.Current;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

//controls the functionality of the index.ftlh page.
//the variables created here can be passed to the index.ftlh page.
@Controller
public class DashboardController
{





    @GetMapping("/dashboard")
    public String indexGet( Model model) {
        model.addAttribute("courseList",Application.courseList);
        model.addAttribute("user",Application.currentUser);

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

    @GetMapping("/dashboard/deleteCourse/{course_id}")
    public String deleteCourse(@PathVariable("course_id") String course_id,  RedirectAttributes redirectAttributes ) {
        Application.dl.connect();
        Application.dl.deleteCourse(course_id);
        redirectAttributes.addFlashAttribute("courseList",Application.dl.getAllCourses());
        redirectAttributes.addFlashAttribute("course", course_id);
        redirectAttributes.addFlashAttribute("user",Application.currentUser);
        Application.dl.close();
        return "redirect:/dashboard";
    } //returns lesson info page

    @PostMapping("/dashboard")
    public String indexPost(String email, String  pw, Model model) { //this codes runs after a user submits the form on teh index.ftlh page
        String _page = "dashboard";
        String _pw = Application.dl.encrypt(pw);//encrypt password given by user
        Application.dl.connect();
       // IncurrentUser =  Application.dl.getUser(email);//retreive all user information based on email

        Application.dl.close();
        if (!Application.currentUser.getPw().equals(_pw)) { //validate password given against password in database
            //if not true then send back the email and msg of "Wrong Email or Password" to the index page
            model.addAttribute("email", email);
            model.addAttribute("msg", "Wrong Email or Password");
            _page = "index";
        }else{
            //if true, set the user as variable to pass to user dashboard page
            model.addAttribute("user", Application.currentUser);

        }
        return _page;
    }

    @GetMapping("/lessonDashboard")
    public String lessonGet(Model model ) {
        return "lessonDashboard";
    } //returns lesson info page

    @GetMapping("/addCourseForm/{email}")
    public String addCourse(@PathVariable("email") String _email,RedirectAttributes redirectAttributes) {
        Application.dl.connect();
        redirectAttributes.addFlashAttribute("professors",(Application.dl.getUsersByType("Professor")));
        Application.dl.close();
        redirectAttributes.addFlashAttribute(Application.currentUser);
        return "redirect:/addCourseForm";
    } //returns lesson info page

    @GetMapping("/addDiscussionForm/{email}")
    public String addDiscussion(@PathVariable("email") String _email,RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(Application.currentUser);
        return "redirect:/addDiscussionForm";
    } //returns lesson info page

    @GetMapping("/updateCourse/{email}/{course_id}")
    public String updateCourse(@PathVariable("email") String _email,@PathVariable("course_id") String course_id,RedirectAttributes redirectAttributes) {
        Application.dl.connect();
        User CurrentProfessor = Application.dl.getCourseProfessor(course_id);
        redirectAttributes.addFlashAttribute("professors",Application.dl.getUsersByType("Professor"));
        redirectAttributes.addFlashAttribute("course",Application.dl.getCourse(course_id));
        redirectAttributes.addFlashAttribute("currentProf", CurrentProfessor);
        redirectAttributes.addFlashAttribute(Application.currentUser);
        Application.dl .close();
        return "redirect:/updateCourseForm";
    } //returns lesson info page

    @GetMapping("/discussionDashboard/{email}")
    public String discussionDashboard(@PathVariable("email") String _email,RedirectAttributes redirectAttributes) {
        Application.dl.connect();
        
        //add if statement to check if the user is an Admin or not.  that way we can limit the user view
        ArrayList<DiscussionGroup> groupList = new ArrayList<>(Application.dl.getAllDiscussions());
        Application.dl.close();
        redirectAttributes.addFlashAttribute("user",Application.currentUser);
        redirectAttributes.addFlashAttribute("groupList",groupList);
        Application.dl .close();
        return "redirect:/discussionDashboard";
    } //returns lesson info page



}
