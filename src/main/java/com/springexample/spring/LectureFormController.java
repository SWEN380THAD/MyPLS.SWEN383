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
public class LectureFormController {

    /*@GetMapping("/lectureDashboard")
    public String formGet(Model model) {
        model.addAttribute("user", Application.currentUser);
        return "lectureDashboard";
    }//returns form page



    @GetMapping("/addLecture/{course_id}/{lecture_id}")
    public String formPost(@PathVariable("course_id") String _course_id, @PathVariable("lecture_id") String _lecture_id, RedirectAttributes redirectAttributes) { //this codes runs after a user submits the form on teh form.ftlh page

        Application.dl.connect();
        redirectAttributes.addFlashAttribute("course", _course_id);
        Application.dl.close();
        redirectAttributes.addFlashAttribute("lecture_id",_lecture_id);
        redirectAttributes.addFlashAttribute("user",Application.currentUser);

        return "redirect:/addLectureDashboard";

    }

    @GetMapping("/addLectureDashboard")
    public String addLecture(Model model) {
        model.addAttribute("user", Application.currentUser);
        return "addLectureDashboard";
    }//returns form page*/

}
