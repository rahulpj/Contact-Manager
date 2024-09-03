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

}
