package com.contactmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contactmanager.entities.User;
import com.contactmanager.forms.UserForm;
import com.contactmanager.helpers.Message;
import com.contactmanager.helpers.MessageType;
import com.contactmanager.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {

    // Constructor Injection
    private  UserService userService;
    @Autowired
    public PageController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

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
    public String registerProcessing(@Valid @ModelAttribute UserForm userForm,BindingResult rBindingResult,HttpSession session){
        System.out.println("Proccesing registration");
        // fetch data
        //userForm
        System.out.println(userForm);
        if(rBindingResult.hasErrors()){ 
            return "register";
        }

        // validate it


        // save to db
        // userService
        // UserForm --> User
        
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("")
        // .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("");


        User savedUser = userService.saveUser(user);
        System.out.println("User Saved");
        
        // message 
        // Message message = Message.builder().content("registration successful").type(MessageType.green).build();
        Message message = new Message();
        message.setContent("Registration Successful");
        
        session.setAttribute("message",message);

        // redirect to login page


        return "redirect:/register";
    }

}
