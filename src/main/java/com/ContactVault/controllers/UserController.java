package com.ContactVault.controllers;

import com.ContactVault.entities.User;
import com.ContactVault.helpers.Helper;
import com.ContactVault.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


//    user dashboard page
    @RequestMapping(value="/dashboard" )
    public String userDashboardPage(){
        return "user/dashboard";
    }

    @RequestMapping(value="/profile" )
    public String userProfilePage(Model model, Authentication authentication){
       String userName= Helper.getEmailOfLoggedInUser(authentication);
        logger.info("User logged in  : {}", userName);

        //fetch data from database .i.e. get user from db
       User user=userService.getUserByEmail(userName);
        logger.info("User logged in with name : {}", user.getName());
        logger.info("User logged in with email : {}", user.getEmail());
        model.addAttribute("loggedInUser",user);
        return "user/profile";
    }

//    user add contacts page

//    user view contacts

//    user edit contacts

//    user delete contacts

}

