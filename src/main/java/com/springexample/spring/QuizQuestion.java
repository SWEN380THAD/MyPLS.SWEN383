package com.springexample.spring;

import java.util.ArrayList;

//this class creates a user of MyPLS and gets/sets their information.
public class QuizQuestion {

    private int questionID;
    private int quizID;
    private String question;
    private ArrayList<QuizAnswer> answers = new ArrayList<QuizAnswer>();



    public int getQuestionID() {//return user password
        return questionID;
    }

    public void setQuestionID(int _questionID) {//set user password
        this.questionID = _questionID;
    }

    public int getQuizID() {//return user password
        return quizID;
    }

    public void setQuizID(int _quizID) {//set user password
        this.quizID = _quizID;
    }

    public String getQuestion() {//return user email
        return question;
    }

    public void setQuestion(String _question) {//set user email
        this.question = _question;
    }

    public ArrayList<QuizAnswer> getAnswers() {//return user email
        return answers;
    }

    public void setAnswers(QuizAnswer _answers) {//set user email
        this.answers.add(_answers);
    }


}

