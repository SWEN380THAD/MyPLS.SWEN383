package com.springexample.spring;

//this class creates a user of MyPLS and gets/sets their information.
public class QuizAnswer {

    private int answerID;
    private int questionID;
    private String answer;
    private boolean isCorrect;






    public int getAnswerID() {//return user password
        return answerID;
    }

    public void setAnswerID(int _answerID) {//set user password
        this.answerID = _answerID;
    }

    public int getQuestionID() {//return user password
        return questionID;
    }
    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public void setName(int _questionID) {//set user password
        this.questionID = _questionID;
    }

    public String getAnswer() {//return user email
        return answer;
    }

    public void setAnswer(String _answer) {//set user email
        this.answer = _answer;
    }

    public boolean getIsCorrect() {//return user email
        return isCorrect;
    }

    public void setIsCorrect(boolean _isCorrect) {//set user email
        this.isCorrect = _isCorrect;
    }



}

