package com.example.kiwipedia.backend.controller.kiwi;

import com.example.kiwipedia.backend.model.kiwi.KiwiFact;
import com.example.kiwipedia.backend.service.kiwi.KiwiFactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/kiwi-facts")
public class KiwiFactsController {

    private final KiwiFactsService kiwiFactsService;

    @Autowired
    public KiwiFactsController(KiwiFactsService kiwiFactsService) {
        this.kiwiFactsService = kiwiFactsService;
    }

    @GetMapping
    public String showKiwiFacts(Model model) {
        List<KiwiFact> facts = kiwiFactsService.getAllKiwiFacts();
        model.addAttribute("facts", facts);
        return "kiwi-facts";
    }

    @GetMapping("/add-fact")
    public String showAddFactForm() {
        return "add-kiwi-fact";
    }

    @PostMapping("/add-fact")
    public String addKiwiFact(@ModelAttribute KiwiFact kiwiFact, Model model) {
        kiwiFactsService.saveKiwiFact(kiwiFact);
        model.addAttribute("successMessage", "Ciekawostka o kiwi została dodana!");
        return "redirect:/kiwi-facts";
    }

}
