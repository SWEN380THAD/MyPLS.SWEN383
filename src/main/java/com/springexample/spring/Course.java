package com.springexample.spring;

//this class creates a user of MyPLS and gets/sets their information.
public class Course {

    private int id;
    private String name;
    private String description;
    private String prerequisites;
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

    public String getPrerequisites() {//return user type
        return prerequisites;
    }

    public void setPrereqs(String _prereq) {
        this.prerequisites = _prereq;
    }

    public String getRequirements() {//return user type
        return requirements;
    }

    public void setRequirements(String _requirements) {//set user type
        this.requirements = _requirements;
    }
}

