package com.bomibear.fellowship.controllers;

import com.bomibear.fellowship.models.ApplicationUser;
import com.bomibear.fellowship.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/")
    public String getRoot(Principal p, Model m){
        ApplicationUser applicationUser = null;
        if(p != null){
            applicationUser = applicationUserRepository.findByUsername(p.getName());
            m.addAttribute("loggedInUser", applicationUser);
            return "myprofile";
        }
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
