package com.bomibear.fellowship.controllers;

import com.bomibear.fellowship.models.ApplicationUserRepository;
import com.bomibear.fellowship.models.Post;
import com.bomibear.fellowship.models.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/post")
    public String getAllPosts(Principal p, Model m){
        m.addAttribute("posts", postRepository.findAll());
        m.addAttribute("loggedInUser", applicationUserRepository.findByUsername(p.getName()));
        return "post";
    }

    @PostMapping("/post")
    public RedirectView addPosts(String body, Principal p){
        Post post = new Post(body, new Date(System.currentTimeMillis()), applicationUserRepository.findByUsername(p.getName()));
        postRepository.save(post);

        return new RedirectView("/myprofile");
    }
}
