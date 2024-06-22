package com.ContactVault.controllers;

import com.ContactVault.entities.User;
import com.ContactVault.forms.UserForm;
import com.ContactVault.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {
    @Autowired
    private UserService userService;

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

    @RequestMapping("/contact")
    public String contactUsPage(){
        return "contact";

    }
    @GetMapping ("/register")
    public String registerPage(Model model){
        UserForm userForm=new UserForm();
//        userForm.setName("abhinav");
        model.addAttribute("userForm",userForm);
        return "register";

    }

    @GetMapping ("/login")
    public String loginPage(){
        return "login";
    }


    @RequestMapping(value ="/do-register", method= RequestMethod.POST)
    public String processRegister(@ModelAttribute UserForm userForm){

       // System.out.println(userForm);
//                1. fetch formdata
//                2. validate Formdata

//                3.Save to database
//                4. userService

        //we are creating user from UserForm
        User user=User.builder()
                .name(userForm.getName())
                .email(userForm.getEmail())
                .password((userForm.getPassword()))
                .about(userForm.getAbout())
                .phoneNumber(userForm.getPhoneNumber())
                .profilePic("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.vecteezy.com%2Ffree-vector%2Fdefault-profile-picture&psig=AOvVaw0UrvFNdn99x2NOGj5_fyJX&ust=1719121983705000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCKCwoMvC7oYDFQAAAAAdAAAAABAE")
                .build();

        User savedUser=userService.saveUser(user);
        System.out.println("User Saved");


//                5.show message : registration successfull
//                6.redirect to login page




        return "redirect:/register";

    }


}
