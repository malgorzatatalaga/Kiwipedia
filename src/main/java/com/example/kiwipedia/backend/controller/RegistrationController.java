package com.example.kiwipedia.backend.controller;

import com.example.kiwipedia.backend.dto.RegisterDTO;
import com.example.kiwipedia.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;


    @GetMapping("/register")
    public String redirectToRegistration() {
        return "rejestracja";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute RegisterDTO registerDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "rejestracja";
        }

        userService.register(registerDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Registration successful");
        return "redirect:/login";
    }
}
