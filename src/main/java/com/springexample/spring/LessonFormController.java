package com.springexample.spring;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//controls the functionality of the form.ftlh page.
//the variables created here can be passed to the form.ftlh page.
@Controller
public class LessonFormController
{


    @GetMapping("/addLessonForm")
    public String formGet(Model model) {

        return "addLessonForm";
    }//returns form page

    @GetMapping("/linkLessonForm")
    public String formlinkGet(Model model) {

        return "addLessonForm";
    }//returns form page

    @PostMapping("/addLessonForm")
    public String formPost( Lesson lesson, RedirectAttributes redirectAttributes) { //this codes runs after a user submits the form on teh form.ftlh page

        Application.dl.connect();
        Application.dl.addLesson(lesson);
        redirectAttributes.addFlashAttribute("user",Application.currentUser);
        redirectAttributes.addFlashAttribute("courseList",Application.dl.getAllCourses());
        Application.dl.close();
        return "redirect:/dashboard";

    }

    @RequestMapping(value = "/updateLessonForm/{lesson_id}")
    public String formUpdatePost( @PathVariable("lesson_id") String _lesson_id, Lesson lesson, RedirectAttributes redirectAttributes) { //this codes runs after a user submits the form on teh form.ftlh page
        lesson.setId( Integer.parseInt(_lesson_id));
        Application.dl.connect();
        Application.dl.updateLesson(lesson);
        redirectAttributes.addFlashAttribute("user", Application.currentUser);
        redirectAttributes.addFlashAttribute("courseList", Application.dl.getAllCourses());
        Application.dl.close();
        return "redirect:/dashboard";
    }


    @RequestMapping(value = "/linkLessonForm/{course_id}", method = RequestMethod.POST, params = "add")
    public String linkLessonFormPost( @PathVariable("course_id") String _course_id, @RequestParam("lesson_id") String _lesson_id, RedirectAttributes redirectAttributes) { //this codes runs after a user submits the form on teh form.ftlh page

        Application.dl.connect();
        Application.dl.linkLessonToCourse(_lesson_id, _course_id);
        redirectAttributes.addFlashAttribute("user",Application.currentUser);
        redirectAttributes.addFlashAttribute("courseList",Application.dl.getAllCourses());
        Application.dl.close();
        return "redirect:/dashboard";

    }



}
