package com.example.kiwipedia.backend.controller.users;

import com.example.kiwipedia.backend.model.UserEntity;
import com.example.kiwipedia.backend.repository.UserRepository;
import com.example.kiwipedia.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/dashboard")
    public String admin() {
        return "admin";
    }

    @GetMapping("/manage-users")
    public String manageUsers(Model model) {
        List<UserEntity> users = (List<UserEntity>) userRepository.findAll();
        model.addAttribute("users", users);
        return "manageUsers";
    }

    @Autowired
    private UserService userService;

    @PostMapping("/manage-users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        userService.deleteUserById(id);
        redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully!");
        return "redirect:/admin/manage-users";
    }

}
