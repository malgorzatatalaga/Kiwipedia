package com.example.kiwipedia.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KiwiController {

    @GetMapping("/gatunki")
    public String gatunkiKiwi() {
        return "gatunki"; // nazwa widoku (np. gatunki.html w folderze resources/templates, jeśli używasz Thymeleaf)
    }

    @GetMapping("/gatunki/kiwi-brunatny")
    public String kiwiBrunatny() {
        return "kiwibrunatny"; // nazwa widoku (np. gatunki.html w folderze resources/templates, jeśli używasz Thymeleaf)
    }
}

