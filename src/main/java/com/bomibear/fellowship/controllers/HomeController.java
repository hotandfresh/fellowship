package com.bomibear.fellowship.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getRoot(Principal p, Model m){
        m.addAttribute("loggedInUser", p);
        return "root";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/signup")
    public String getSignUp(){
        return "signup";
    }
}
