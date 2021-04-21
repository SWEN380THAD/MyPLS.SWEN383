package com.springexample.spring;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//controls the functionality of the form.ftlh page.
//the variables created here can be passed to the form.ftlh page.
@Controller
public class addLessonFormController
{


    @GetMapping("/addLessonForm")
    public String formGet(Model model) {

        return "addLessonForm";
    }//returns form page

    @PostMapping("/addLessonForm")
    public String formPost(String course_id, Lesson lesson, RedirectAttributes redirectAttributes) { //this codes runs after a user submits the form on teh form.ftlh page

        Application.dl.connect();
        Application.dl.addLesson(lesson, course_id);
        redirectAttributes.addFlashAttribute("user",Application.currentUser);
        redirectAttributes.addFlashAttribute("courseList",Application.dl.getAllCourses());
        Application.dl.close();
        return "redirect:/dashboard";

    }


}
