package com.springexample.spring;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

//controls the functionality of the form.ftlh page.
//the variables created here can be passed to the form.ftlh page.
@Controller
public class discussionDashboardController
{


    @GetMapping("/discussionDashboard")
    public String formGet(Model model) {
        return "discussionDashboard";
    }//returns form page




    @PostMapping("/discussionDashboard")
    public String formPost(DiscussionGroup discussion, RedirectAttributes redirectAttributes) { //this codes runs after a user submits the form on teh form.ftlh page

        Application.dl.connect();
        Application.dl.addDiscussion(discussion);
        ArrayList<Course> courseList = new ArrayList<>(Application.dl.getAllCourses());
        Application.dl.close();
        redirectAttributes.addFlashAttribute("user",Application.currentUser);
        redirectAttributes.addFlashAttribute("courseList",courseList);
        return "redirect:/dashboard";

    }
    @GetMapping("/manageGroupMembers/{group_id}")
    public String getGroupMembers(@PathVariable("group_id") String _group_id,RedirectAttributes redirectAttributes) {
        Application.dl.connect();
        redirectAttributes.addFlashAttribute("group", Application.dl.getGroup(_group_id));
        redirectAttributes.addFlashAttribute("allUsers", Application.dl.getAllUsers());

        redirectAttributes.addFlashAttribute(Application.currentUser);
        Application.dl.close();
        return "redirect:/groupManagement";
    }

    @GetMapping("/groupManagement")
    public String getGroupManagement(Model model) {
        return "groupManagement";
    }//returns form page


}
