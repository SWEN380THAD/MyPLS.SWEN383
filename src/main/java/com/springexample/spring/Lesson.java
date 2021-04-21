package com.springexample.spring;

import java.util.ArrayList;

//this class creates a user of MyPLS and gets/sets their information.
public class Lesson {

    private int id;
    private String name;
    private String description;
    private String minimumScore;
    private String quiz_id;
    private String startDate;
    private String endDate;

    public String getEndDate() {
        return endDate;
    }

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

    public String getMinimumScore() {//return user email
        return minimumScore;
    }

    public void setMinimumScore(String _minimumScore) {//set user email
        this.minimumScore = _minimumScore;
    }


    public String getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(String quiz_id) {
        this.quiz_id = quiz_id;
    }
}

