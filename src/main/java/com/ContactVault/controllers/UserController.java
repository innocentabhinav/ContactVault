package com.ContactVault.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

//    user dashboard page
    @RequestMapping(value="/dashboard" ,method= RequestMethod.GET)
    public String userDashboardPage(){
        return "user/dashboard";
    }

    @RequestMapping(value="/profile" ,method= RequestMethod.GET)
    public String userProfilePage(){
        return "user/profile";
    }

//    user add contacts page

//    user view contacts

//    user edit contacts

//    user delete contacts

}

