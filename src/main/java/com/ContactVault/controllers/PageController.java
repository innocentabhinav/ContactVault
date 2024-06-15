package com.ContactVault.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

//If you want to send a dynamic data to html from controller then u can use a Model object in the parameter in the controller method .


    @RequestMapping("/home")
    public String Home(Model model){
        model.addAttribute("name","Abhinav");
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage(Model model){
        model.addAttribute("isLogin","false");
        return "about";

    }

    @RequestMapping("/services")
    public String servicesPage(){
        return "services";

    }





}
