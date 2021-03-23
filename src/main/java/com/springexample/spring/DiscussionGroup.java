package com.springexample.spring;

//this class creates a user of MyPLS and gets/sets their information.
public class DiscussionGroup {

    private  int group_id;
    private String name;
    private String description;
    private String createDate;



    public int getId() {//return user password
        return group_id;
    }
    public void setId(int _id) {//set user password
        this.group_id = _id;
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

    public String getCreateDate() {//return user type
        return createDate;
    }
    public void setCreateDate(String _createDate) {this.createDate = _createDate;}
}

