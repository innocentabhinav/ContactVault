package com.ContactVault.controllers;

import com.ContactVault.helpers.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger= LoggerFactory.getLogger(UserController.class);

//    user dashboard page
    @RequestMapping(value="/dashboard" )
    public String userDashboardPage(){
        return "user/dashboard";
    }

    @RequestMapping(value="/profile" )
    public String userProfilePage(Authentication authentication){
       String userName= Helper.getEmailOfLoggedInUser(authentication);
        logger.info("User logged in: {}", userName);
        return "user/profile";
    }

//    user add contacts page

//    user view contacts

//    user edit contacts

//    user delete contacts

}

