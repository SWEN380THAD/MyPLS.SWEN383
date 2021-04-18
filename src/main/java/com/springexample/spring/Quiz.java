package com.springexample.spring;

import java.util.ArrayList;

//this class creates a user of MyPLS and gets/sets their information.
public class Quiz {

    private int lessonID;
    private int quizID;
    private String name;
    private String minimumScore;
    private ArrayList<QuizQuestion> questions = new ArrayList<QuizQuestion>() ;




    public int getLessonID() {//return user password
        return lessonID;
    }

    public void setLessonID(int _lessonID) {//set user password
        this.lessonID = _lessonID;
    }

    public int getQuizID() {//return user password
        return quizID;
    }

    public void setQuizID(int _quizID) {//set user password
        this.quizID = _quizID;
    }

    public String getName() {//return user password
        return name;
    }

    public void setName(String _name) {//set user password
        this.name = _name;
    }

    public String getMinimumScore() {//return user email
        return minimumScore;
    }

    public void setMinimumScore(String _minimumScore) {//set user email
        this.minimumScore = _minimumScore;
    }

    public ArrayList<QuizQuestion> getQuestions() {//return user email
        return questions;
    }

    public void setQuestions(QuizQuestion _questions) {//set user email
        this.questions.add( _questions);
    }


}

