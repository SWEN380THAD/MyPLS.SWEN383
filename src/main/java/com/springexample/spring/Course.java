package com.springexample.spring;

//this class creates a user of MyPLS and gets/sets their information.
public class Course {

    private int id;
    private String name;
    private String description;
    private String prereqs;
    private String requirements;


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

    public String getDescription() {//return user email
        return description;
    }

    public void setDescription(String _description) {//set user email
        this.description = _description;
    }

    public String getPrereqs() {//return user type
        return prereqs;
    }

    public void setPrereqs(String _prereqs) {//set user type
        this.prereqs = _prereqs;
    }

    public String getRequirements() {//return user type
        return requirements;
    }

    public void setRequirements(String _requirements) {//set user type
        this.requirements = _requirements;
    }
}

