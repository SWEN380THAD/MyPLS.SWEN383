package com.springexample.spring;

import java.util.ArrayList;

//this class creates a user of MyPLS and gets/sets their information.
public class DiscussionGroup {

    private  int group_id;
    private String name;
    private String description;
    private String createDate;
    private ArrayList<User> groupMembers = new ArrayList<>();
    private boolean is_public;



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

    public boolean getIsPublic() {//return user type
        return is_public;
    }
    public void setIsPublic(boolean _is_public) {this.is_public = _is_public;}

    public void addGroupMember(User user ) {
        groupMembers.add(user);
    }

    public void removeroupMember(User user ) {
        groupMembers.remove(user);
    }

    public ArrayList<User> getGroupMembers() {
        return this.groupMembers;
    }
    public void setGroupMembers(ArrayList _groupMembers) {this.groupMembers = _groupMembers;}
}

