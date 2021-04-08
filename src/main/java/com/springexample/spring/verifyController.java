package com.springexample.spring;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

//controls the functionality of the index.ftlh page.
//the variables created here can be passed to the index.ftlh page.
@Controller
public class verifyController
{
    private static Datalayer dl1 = new Datalayer();


    @GetMapping("/verify/{vercode}")
    public String verify(@PathVariable("vercode") String _verCode, RedirectAttributes redirectAttributes) {
        dl1.connect();
       dl1.verifyUser(_verCode);
       dl1.close();

        return "redirect:/";
    } //returns lesson info page






}
