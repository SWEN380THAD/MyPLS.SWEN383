package com.springexample.spring;

import java.util.ArrayList;

//this class creates a user of MyPLS and gets/sets their information.
public class Lesson {

    private int id;
    private String name;
    private String minimumScore;





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


}

