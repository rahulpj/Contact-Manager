package com.contactmanager.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contactmanager.helpers.Helper;

@Controller
@RequestMapping("/user")
public class UserController {
    
    Logger logger = LoggerFactory.getLogger(UserController.class);
    // user dashboard
    @RequestMapping(value="/dashboard")
    public String userDashboard(){
        System.out.println("User Dashboard");
        return "user/dashboard";
    }

    // user profile
    @RequestMapping("/profile")
    public String userProfile(Authentication authentication){
        String username=Helper.getEmailOfLoggedInUser(authentication);
        // logger.info("email of logger in user:- {}",username);
        System.out.println(username);
        // now get userdata from db



        // System.out.println("User Profile");
        return "user/profile";
    }

}
