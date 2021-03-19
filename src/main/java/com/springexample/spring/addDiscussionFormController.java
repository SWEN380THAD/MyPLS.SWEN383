package com.springexample.spring;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

//controls the functionality of the form.ftlh page.
//the variables created here can be passed to the form.ftlh page.
@Controller
public class addDiscussionFormController
{


    @GetMapping("/addDiscussionForm")
    public String formGet(Model model) {
        return "addDiscussionForm";
    }//returns form page




    @PostMapping("/addDiscussionForm")
    public String formPost(DiscussionGroup discussion, RedirectAttributes redirectAttributes) { //this codes runs after a user submits the form on teh form.ftlh page

        Application.dl.connect();
        Application.dl.addDiscussion(discussion);
        ArrayList<Course> courseList = new ArrayList<>(Application.dl.getAllCourses());
        Application.dl.close();
        redirectAttributes.addFlashAttribute("user",Application.currentUser);
        redirectAttributes.addFlashAttribute("courseList",courseList);
        return "redirect:/dashboard";

    }


}
