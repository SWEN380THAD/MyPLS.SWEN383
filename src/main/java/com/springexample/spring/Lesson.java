package com.springexample.spring;

import java.util.ArrayList;

//this class creates a user of MyPLS and gets/sets their information.
public class Lesson {

    private int id;
    private String name;
    private String description;
    private final int minimumScore = 65;
    private String quiz_id;
    private String startDate;
    private String endDate;
    private ArrayList<String> media;
    private Quiz lesson_quiz;
    private String quizScore;

    public Quiz getLesson_quiz() {  return lesson_quiz; }
    public void setLesson_quiz(Quiz lesson_quiz) {  this.lesson_quiz = lesson_quiz;  }

    public ArrayList<String> getMedia() {  return media; }
    public void setMedia(ArrayList<String> media) {   this.media = media;  }

    public String getEndDate() {  return endDate;  }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getId() {//return user password
        return id;
    }
    public void setId(int _id) {//set user password
        this.id = _id;
    }

    public String getName() {//return user password
        return name;
    }
    public void setName(String _name) {//set user password
        this.name = _name;
    }

    public int getMinimumScore() {
        return minimumScore;
    }

    public String getQuiz_id() {
        return quiz_id;
    }
    public void setQuiz_id(String quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getQuizScore() {
        return quizScore;
    }

    public void setQuizScore(String quizScore) {
        this.quizScore = quizScore;
    }
}

