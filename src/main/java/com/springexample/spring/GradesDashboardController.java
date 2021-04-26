package com.springexample.spring;

import com.springexample.spring.Application;
import com.springexample.spring.Lesson;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

public class GradesDashboardController {


    @GetMapping("/gradesDashboard")
    public String formGet(Model model) {
        model.addAttribute("user", Application.currentUser);
        return "gradesDashboard";
    }//returns form page



    @GetMapping("/viewGrades")
    public String viewGrades( Model model) {


        return "/viewGrades";
    } //returns index page

    @GetMapping("/viewGrades/{course_id}/{lesson_id}")
    public String viewGrades( @PathVariable("course_id") String _course_id, @PathVariable("lesson_id") String _lesson_id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("user", Application.currentUser);
        redirectAttributes.addFlashAttribute("course_id", _course_id);
        redirectAttributes.addFlashAttribute("lesson_id",_lesson_id);

        Application.dl.connect();
        redirectAttributes.addFlashAttribute("paths",Application.dl.getAllMultimedia(_lesson_id));
        Application.dl.close();

        return "redirect:/viewGrades";
    }//returns form page
}
