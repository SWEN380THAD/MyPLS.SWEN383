package com.springexample.spring;


import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Array;
import java.util.ArrayList;

//controls the functionality of the form.ftlh page.
//the variables created here can be passed to the form.ftlh page.
@Controller
public class LessonDashboardController
{

    private ArrayList<String> answerList = new ArrayList<String>();

    @GetMapping("/lessonDashboard")
    public String formGet(Model model) {
        model.addAttribute("user", Application.currentUser);
        return "lessonDashboard";
    }//returns form page

    @GetMapping("/addMultimediaForm/{course_id}/{lesson_id}")
    public String addMultimedia( @PathVariable("course_id") String _course_id, @PathVariable("lesson_id") String _lesson_id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("user", Application.currentUser);
        redirectAttributes.addFlashAttribute("course_id", _course_id);
        redirectAttributes.addFlashAttribute("lesson_id",_lesson_id);

        return "redirect:/addMultimediaForm";
    }//returns form page

    @GetMapping("/viewMedia")
    public String viewMedia( Model model) {
//        model.addAttribute("user",Application.currentUser);
//
//        String path = new ClassPathResource("multimedia/Project2_Demo.mp4").getPath();
//
//        model.addAttribute("path", path);

        return "/viewMedia";
    } //returns index page

    @GetMapping("/viewMultimedia/{course_id}/{lesson_id}/{path}/{file}")
    public String viewMultimedia( @PathVariable("course_id") String _course_id, @PathVariable("lesson_id") String _lesson_id, @PathVariable("path") String _path,  @PathVariable("file") String _file, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("user", Application.currentUser);
        redirectAttributes.addFlashAttribute("course_id", _course_id);
        redirectAttributes.addFlashAttribute("lesson_id",_lesson_id);
        redirectAttributes.addFlashAttribute("path",_path+"/"+_file);


        /*Application.dl.connect();
        redirectAttributes.addFlashAttribute("paths",Application.dl.getAllMultimedia(_lesson_id));
        Application.dl.close();*/

        return "redirect:/viewMedia";
    }//returns form page

    @GetMapping("/takeQuiz")
    public String takeQuizform( Model model) {


        return "/takeQuizForm";
    }//returns form page

    @GetMapping("/takeQuiz/{course_id}/{lesson_id}/{quiz_id}/{question}")
    public String takeQuiz( @PathVariable("course_id") String _course_id, @PathVariable("lesson_id") String _lesson_id,  @PathVariable("quiz_id") String _quiz_id, @PathVariable("question") int question, RedirectAttributes redirectAttributes) {

        answerList.removeAll(answerList);
        redirectAttributes.addFlashAttribute("user", Application.currentUser);
        redirectAttributes.addFlashAttribute("course_id", _course_id);
        redirectAttributes.addFlashAttribute("lesson_id",_lesson_id);
        redirectAttributes.addFlashAttribute("question_number",1);
        redirectAttributes.addFlashAttribute("quiz_id",_quiz_id);

        Application.dl.connect();
        Quiz quiz = Application.dl.getQuiz(_quiz_id);
        redirectAttributes.addFlashAttribute("quizQuestion", quiz.getQuestions().get(question));
        Application.dl.close();



        /*Application.dl.connect();
        redirectAttributes.addFlashAttribute("paths",Application.dl.getAllMultimedia(_lesson_id));
        Application.dl.close();*/

        return "redirect:/takeQuiz";
    }//returns form page


    @RequestMapping(value = "/submitAnswer/{course_id}/{quiz_id}/{question_number}", method = RequestMethod.POST, params = "add")
    public String submitAnswer(@PathVariable("course_id") String _course_id, @PathVariable("quiz_id") String _quiz_id, @PathVariable("question_number") int question_number, @RequestParam(name="radio", required = true) String radio, RedirectAttributes redirectAttributes) { //this codes runs after a user submits the form on teh form.ftlh page

        answerList.add(radio);
        redirectAttributes.addFlashAttribute("user", Application.currentUser);
        Application.dl.connect();
        Quiz quiz = Application.dl.getQuiz(_quiz_id);
        redirectAttributes.addFlashAttribute("quizQuestion", quiz.getQuestions().get(question_number));
        Application.dl.close();
        redirectAttributes.addFlashAttribute("quiz_id",_quiz_id);
        redirectAttributes.addFlashAttribute("course_id", _course_id);
        if( question_number+1 == quiz.getQuestions().size()){

            redirectAttributes.addFlashAttribute("last_question", 1);
        }
        redirectAttributes.addFlashAttribute("question_number",question_number+1);
        return "redirect:/takeQuiz";
    }


    @RequestMapping(value = "/submitAnswer/{course_id}/{quiz_id}/{question_number}", method = RequestMethod.POST, params = "submit")
    public String submitQuiz(@PathVariable("course_id") String _course_id, @PathVariable("quiz_id") String _quiz_id, @RequestParam(name="radio", required = true) String radio, RedirectAttributes redirectAttributes) { //this codes runs after a user submits the form on teh form.ftlh page

        answerList.add(radio);
        Application.dl.connect();
        ArrayList<Lesson> lessons = Application.dl.getLearnerLessons(_course_id, Application.currentUser.getEmail());




        redirectAttributes.addFlashAttribute("lessonList", lessons);
        int correct = 0;
        ArrayList<String > cAnswerList = Application.dl.getCorrectAnswerList();
        for(String ans:answerList){
                for(String cans:cAnswerList){
                    if( ans.equals(cans)){
                        correct = correct +1;
                    }

                }

        }
        int listSize = answerList.size();

        double score = Math.round((double)correct/(double)listSize * 100.0);
        redirectAttributes.addFlashAttribute("user", Application.currentUser);
        redirectAttributes.addFlashAttribute("course_id", _course_id);


        //insert score
        Application.dl.saveQuiz(Application.currentUser.getUser_id(), _quiz_id, score + "");

        ArrayList<QuizGrades> quizGrades = Application.dl.getQuizScores(Application.currentUser.getUser_id());

        for(Lesson lesson : lessons){
            lesson.setQuiz_id(Application.dl.getLessonQuizID(lesson.getId()));
            lesson.setMedia(Application.dl.getAllMultimedia(lesson.getId()+""));


            for (QuizGrades grade : quizGrades) {
                if (grade.getLesson_id().equals(lesson.getId() + "")) {
                    lesson.setQuizScore(grade.getScore());
                }
            }
        }

        Application.dl.close();

        //Quiz quiz = Application.dl.getQuiz(_quiz_id);
        answerList.clear();
        return "redirect:/lessonDashboard";
    }

    @GetMapping("/addQuiz/{course_id}/{lesson_id}")
    public String formPost(@PathVariable("course_id") String _course_id, @PathVariable("lesson_id") String _lesson_id, RedirectAttributes redirectAttributes) { //this codes runs after a user submits the form on teh form.ftlh page

        Application.dl.connect();
        redirectAttributes.addFlashAttribute("quizzes",Application.dl.getAllQuizzes("0"));
        redirectAttributes.addFlashAttribute("course", _course_id);
        Application.dl.close();
        redirectAttributes.addFlashAttribute("lesson_id",_lesson_id);
        redirectAttributes.addFlashAttribute("user",Application.currentUser);

        return "redirect:/addQuizDashboard";

    }

    @GetMapping("/removeQuiz/{course_id}/{lesson_id}")
    public String removeQuiz(@PathVariable("course_id") String _course_id, @PathVariable("lesson_id") String _lesson_id, RedirectAttributes redirectAttributes) { //this codes runs after a user submits the form on teh form.ftlh page

        redirectAttributes.addFlashAttribute("user",Application.currentUser);
        redirectAttributes.addFlashAttribute("course_id", _course_id);
        Application.dl.connect();
        Application.dl.removeLessonFromQuiz( _lesson_id);
        ArrayList<Lesson> lessons = Application.dl.getLearnerLessons(_course_id, Application.currentUser.getEmail());
        redirectAttributes.addFlashAttribute("lessonList",lessons);

        for(Lesson lesson : lessons){
            lesson.setQuiz_id(Application.dl.getLessonQuizID(lesson.getId()));

        }
        Application.dl.close();

        return "redirect:/lessonDashboard";

    }

    @GetMapping("/addQuizDashboard")
    public String addQuiz(Model model) {
        model.addAttribute("user", Application.currentUser);
        return "addQuizDashboard";
    }//returns form page

    @GetMapping("/addQuizDashboard/{course_id}/{lesson_id}/{quiz_id}")
    public String assignQuizLesson(@PathVariable("course_id") String _course_id, @PathVariable("lesson_id") String _lesson_id, @PathVariable("quiz_id") String _quiz_id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("user",Application.currentUser);
        redirectAttributes.addFlashAttribute("course_id", _course_id);
        Application.dl.connect();
        Application.dl.addLessonToQuiz(_quiz_id, _lesson_id);
        ArrayList<Lesson> lessons = Application.dl.getLearnerLessons(_course_id, Application.currentUser.getEmail());
        redirectAttributes.addFlashAttribute("lessonList",lessons);

        for(Lesson lesson : lessons){
            lesson.setQuiz_id(Application.dl.getLessonQuizID(lesson.getId()));

        }
        Application.dl.close();

        return "redirect:/lessonDashboard";
    }//returns form page

    @GetMapping("/deleteLesson/{course_id}/{lesson_id}")
    public String deleteLesson(@PathVariable("course_id") String _course_id, @PathVariable("lesson_id") String _lesson_id, RedirectAttributes redirectAttributes) { //this codes runs after a user submits the form on teh form.ftlh page

        redirectAttributes.addFlashAttribute("user", Application.currentUser);
        redirectAttributes.addFlashAttribute("course_id", _course_id);
        Application.dl.connect();
        Application.dl.deleteLessonFromCourse(_course_id, _lesson_id);
        ArrayList<Lesson> lessons = Application.dl.getLearnerLessons(_course_id, Application.currentUser.getEmail());
        redirectAttributes.addFlashAttribute("lessonList", lessons);

        for (Lesson lesson : lessons) {
            lesson.setQuiz_id(Application.dl.getLessonQuizID(lesson.getId()));

        }
        Application.dl.close();

        return "redirect:/lessonDashboard";
    }
    @GetMapping("/editLesson/{lesson_id}")
    public String editLesson(@PathVariable("lesson_id") String _lesson_id, RedirectAttributes redirectAttributes) { //this codes runs after a user submits the form on teh form.ftlh page

        redirectAttributes.addFlashAttribute("user", Application.currentUser);
        Application.dl.connect();
        redirectAttributes.addFlashAttribute("lesson", Application.dl.getLesson(_lesson_id));
        Application.dl.close();

        return "redirect:/addLessonForm";
    }






}
