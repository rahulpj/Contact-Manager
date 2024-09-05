package com.contactmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contactmanager.forms.UserForm;

@Controller
public class PageController {
    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    // about

    @RequestMapping("/about")
    public String aboutPage(){
        System.out.println("aboutPage");
        return "about";
    }


    // services

    @RequestMapping("/services")
    public String servicePage(){
        System.out.println("servicePage");
        return "services";
    }

    //contact page

    @RequestMapping("/contact")
    public String contact(){
        System.out.println("contact page");
        return "contact";
    }

    //login page

    @RequestMapping("/login")
    public String login(){
        System.out.println("login page");
        return "login";
    }

    //register page

    @RequestMapping("/register")
    public String register(Model model){
        System.out.println("register page");
        UserForm userForm = new UserForm();
        model.addAttribute("userForm",userForm);

        return "register";
    }

    // register processing
    @PostMapping("/do-register")
    public String registerProcessing(@ModelAttribute UserForm userForm){
        System.out.println("Proccesing registration");
        System.out.println(userForm);
        // fetch data
        //userForm


        // validate it


        // save to db
        
        
        // message 
        // redirect to login page


        return "redirect:/register";
    }

}
