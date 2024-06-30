package com.ContactVault.controllers;

import com.ContactVault.entities.User;
import com.ContactVault.helpers.Helper;
import com.ContactVault.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class RootController {

    @Autowired
    private UserService userService;

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @ModelAttribute
    public void addLoggedInUserInfo(Model model, Authentication authentication){
        if(authentication==null){
            return;
        }
        System.out.println("Adding LoggedIn user info to the model.");
        String userName= Helper.getEmailOfLoggedInUser(authentication);

        //fetch data from database .i.e. get user from db
        User user=userService.getUserByEmail(userName);
        logger.info("User logged in with name : {}", user.getName());
        logger.info("User logged in with email : {}", user.getEmail());
        model.addAttribute("loggedInUser", user);

        }


}
