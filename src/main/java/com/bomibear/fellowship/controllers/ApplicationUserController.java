package com.bomibear.fellowship.controllers;

import com.bomibear.fellowship.models.ApplicationUser;
import com.bomibear.fellowship.models.ApplicationUserRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class ApplicationUserController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/myprofile")
    public String getMyProfile(Principal p, Model m){
        m.addAttribute("loggedInUser", applicationUserRepository.findByUsername(p.getName()));
        return "myprofile";
    }

    @PostMapping("/users")
    public RedirectView createUser(String username, String firstName, String lastName, String password, String bio){
        ApplicationUser newUser = new ApplicationUser(username, firstName, lastName, encoder.encode(password), bio);

        applicationUserRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/");
    }

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable long id, Principal p, Model m){
        ApplicationUser viewedUser = applicationUserRepository.findById(id).get();

        m.addAttribute("viewedUser", viewedUser);
        m.addAttribute("loggedInUser", applicationUserRepository.findByUsername(p.getName()));
        return "userProfile";
    }

    @GetMapping("/users")
    public String getAllUsers(Principal p, Model m){
        m.addAttribute("allUsers", applicationUserRepository.findAll());
        m.addAttribute("loggedInUser", applicationUserRepository.findByUsername(p.getName()));
        return "allUsers";
    }

    @PostMapping("/users/{id}/following")
    public RedirectView addFollower(@PathVariable long id, Principal p){
        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
        ApplicationUser follower = applicationUserRepository.findById(id).get();

        currentUser.getFollowing().add(follower);
        applicationUserRepository.save(currentUser);

        return new RedirectView("/feed");
    }

    @GetMapping("/feed")
    public String getFeed(Principal p, Model m){
        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("followed", loggedInUser.getFollowing());
        m.addAttribute("loggedInUser", applicationUserRepository.findByUsername(p.getName()));

        return "/feed";
    }
}
