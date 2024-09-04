package com.contactmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String signup(){
        System.out.println("register page");
        return "register";
    }

}
