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
public class updateCourseFormController
{


    @GetMapping("/updateCourseForm")
    public String formGet(Model model) {
        return "updateCourseForm";
    }//returns form page




    @PostMapping("/updateCourseForm")
    public String updateFormPost(Course course, String professor_id, String user_id, RedirectAttributes redirectAttributes) { //this codes runs after a user submits the form on teh form.ftlh page

        Application.dl.connect();
        Application.dl.updateCourse(course);
        if(user_id != null && !professor_id.contains("Not") ) {
            Application.dl.removeProfessorFromCourse(course.getId(), professor_id);
            Application.dl.addProfessorToCourse(course.getId(), user_id);
        }else if(professor_id.contains("Not") && user_id != null) {
            Application.dl.addProfessorToCourse(course.getId(), user_id);

        }

        Application.courseList = new ArrayList<>(Application.dl.getAllCourses());
        Application.dl.close();
        redirectAttributes.addFlashAttribute("user",Application.currentUser);
        //redirectAttributes.addFlashAttribute("courseList",courseList);
        return "redirect:/dashboard";

    }


}
