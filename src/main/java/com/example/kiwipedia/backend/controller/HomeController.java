package com.example.kiwipedia.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @PostMapping("/register")
    public String redirectToRegistration() {
        return "registration";
    }
}
