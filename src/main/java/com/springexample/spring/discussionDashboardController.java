package com.springexample.spring;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

//controls the functionality of the form.ftlh page.
//the variables created here can be passed to the form.ftlh page.
@Controller
public class discussionDashboardController
{


    @GetMapping("/discussionDashboard")
    public String formGet(Model model) {
        return "discussionDashboard";
    }//returns form page




    @PostMapping("/groupManagement/{group_id}")

    public String formPost( @PathVariable("group_id") String _group_id, @RequestParam("userChecked") List<String> checkedUser, RedirectAttributes redirectAttributes) { //this codes runs after a user submits the form on teh form.ftlh page

        String reDir = "redirect:/discussionDashboard/"+Application.currentUser.getEmail();
        Application.dl.connect();

        DiscussionGroup group = Application.dl.getGroup(_group_id);
        for(User _user : group.getGroupMembers()){
            if(!checkedUser.contains(_user.getUser_id())){
                Application.dl.removeGroupMember(_group_id, _user.getUser_id());
            }
        }
        for(String uid: checkedUser){
            Application.dl.addGroupMember(_group_id,uid);
            System.out.println(uid);
        }


        return reDir;

    }
    @GetMapping("/manageGroupMembers/{group_id}")
    public String getGroupMembers(@PathVariable("group_id") String _group_id,RedirectAttributes redirectAttributes) {
        Application.dl.connect();
        redirectAttributes.addFlashAttribute("group", Application.dl.getGroup(_group_id));
        redirectAttributes.addFlashAttribute("groupID", _group_id);
        redirectAttributes.addFlashAttribute("allUsers", Application.dl.getAllUsers());

        redirectAttributes.addFlashAttribute(Application.currentUser);
        Application.dl.close();
        return "redirect:/groupManagement";
    }

    @GetMapping("/addGroupMembers/{group_id}/{user_id}")
    public String addToGroup(@PathVariable("group_id") String _group_id, @PathVariable("user_id") String _user_id, RedirectAttributes redirectAttributes) {
        Application.dl.connect();
        ArrayList<DiscussionGroup> groupList = new ArrayList<>(Application.dl.getAllDiscussions("%"));
        redirectAttributes.addFlashAttribute("group", Application.dl.getGroup(_group_id));
        Application.dl.addGroupMember(_group_id,_user_id);
        redirectAttributes.addFlashAttribute("user",Application.currentUser);
        redirectAttributes.addFlashAttribute("groupList",groupList);
        Application.dl .close();
        return "redirect:/discussionDashboard";
    }

    @GetMapping("/groupManagement")
    public String getGroupManagement(Model model) {
        return "groupManagement";
    }//returns form page

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String getsearch(@RequestParam("filter") String _filter, RedirectAttributes redirectAttributes ) {

       if(_filter == ""){_filter = "%";}
        Application.dl.connect();

        //add if statement to check if the user is an Admin or not.  that way we can limit the user view
        ArrayList<DiscussionGroup> groupList = new ArrayList<>(Application.dl.getAllDiscussions(_filter));
        Application.dl.close();
        redirectAttributes.addFlashAttribute("user",Application.currentUser);
        redirectAttributes.addFlashAttribute("groupList",groupList);
        Application.dl .close();
        return "redirect:/discussionDashboard";
    }//returns form page
}
