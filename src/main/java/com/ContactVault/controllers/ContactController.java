package com.ContactVault.controllers;

import com.ContactVault.entities.Contact;
import com.ContactVault.entities.User;
import com.ContactVault.forms.ContactForm;
import com.ContactVault.helpers.Helper;
import com.ContactVault.helpers.Message;
import com.ContactVault.helpers.MessageType;
import com.ContactVault.services.ContactService;
import com.ContactVault.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService ;

    @RequestMapping("/add")
    public String addContactView(Model model){
        ContactForm contactForm=new ContactForm();
        model.addAttribute("contactForm",contactForm);
        return "user/addContact";
    }

    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public String SaveContact(@Valid @ModelAttribute ContactForm contactForm , BindingResult bindingResult, Authentication authentication,HttpSession httpSession){
        //processing the form data


        //Validate the form data before saving
        if(bindingResult.hasErrors()){
            httpSession.setAttribute("message", Message.builder()
                            .content("Please correct the following errors.")
                            .type(MessageType.red)
                            .build());
            return "user/addContact" ;
        }


        String username= Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.getUserByEmail(username);

        //process the contact picture

        Contact contact=new Contact();
        contact.setName(contactForm.getName());
        contact.setFavorite(contactForm.isFavorite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setUser(user);
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());




        contactService.SaveContact(contact);
        System.out.println(contactForm);

        //setting the contact picture url


        //set message to be displayed on the view
        httpSession.setAttribute("message",Message.builder()
                .content("You have successfully added a new Contact.")
                .type(MessageType.green)
                .build());
        return "redirect:/user/contacts/add" ;

    }




}
