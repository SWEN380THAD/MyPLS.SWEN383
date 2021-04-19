package com.springexample.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller
public class addMultimediaController {

    @GetMapping("/addMultimediaForm")
    public String formGet(Model model) {
        model.addAttribute("user", Application.currentUser);
        return "addMultimediaForm";
    }//returns form page

    @PostMapping("/addMultimediaForm/{course_id}/{lesson_id}")
    public String formpost(@PathVariable("course_id") String _course_id, @PathVariable("lesson_id") String _lesson_id, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException { //this codes runs after a user submits the form on teh form.ftlh page

        String fileName = file.getOriginalFilename();

        String fileLocation = "src/main/resources/multimedia/" + fileName;

        FileUploadUtil.saveFile("src/main/resources/multimedia", fileName, file);

        Application.dl.connect();
        Application.dl.addMultimedia(fileLocation, Integer.parseInt(_lesson_id));
        redirectAttributes.addFlashAttribute("lessonList", Application.dl.getLearnerLessons(_course_id, Application.currentUser.getEmail()));
        Application.dl.close();
        redirectAttributes.addFlashAttribute("user",Application.currentUser);
        redirectAttributes.addFlashAttribute("course_id", _course_id);
        redirectAttributes.addFlashAttribute("lesson_id", _lesson_id);

        // redirectAttributes.addFlashAttribute("courseList",courseList);
        return "redirect:/lessonDashboard/";

    }
}
