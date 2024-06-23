package com.example.kiwipedia.backend.controller.kiwi;

import com.example.kiwipedia.backend.model.EditHistory;
import com.example.kiwipedia.backend.model.kiwi.Species;
import com.example.kiwipedia.backend.service.PageEditService;
import com.example.kiwipedia.backend.service.kiwi.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/gatunki/kiwi-plamisty")
public class KiwiPlamistyController {

    @Autowired
    private SpeciesService speciesService;

    @Autowired
    private PageEditService pageEditService;

    @GetMapping
    public String kiwiPlamisty(Model model) {
        Integer fixedId = 2;
        Optional<Species> kiwiOptional = speciesService.getSpeciesById(fixedId);
        if (kiwiOptional.isPresent()) {
            model.addAttribute("kiwi", kiwiOptional.get());
        } else {
            model.addAttribute("errorMessage", "Kiwi species not found.");
        }
        return "kiwi-plamisty";
    }

    @GetMapping("/edit")
    public String editKiwiPlamisty(Model model) {
        Integer fixedId = 2;
        Optional<Species> kiwiOptional = speciesService.getSpeciesById(fixedId);
        if (kiwiOptional.isPresent()) {
            Species kiwi = kiwiOptional.get();
            model.addAttribute("kiwi", kiwi);
            return "kiwi-plamisty-edit";
        } else {
            model.addAttribute("errorMessage", "Species not found.");
            return "redirect:/gatunki";
        }
    }

    @PostMapping("/update")
    public String updateKiwiPlamisty(@ModelAttribute("kiwi") Species species) {
        Integer fixedId = 2;
        Optional<Species> kiwiOptional = speciesService.getSpeciesById(fixedId);
        if (kiwiOptional.isPresent()) {
            Species oldKiwi = kiwiOptional.get();
            String oldContent = oldKiwi.getDescription();
            String newContent = species.getDescription();
            if (!oldContent.equals(newContent)) {
                pageEditService.saveEditHistory("kiwi-plamisty", oldContent, newContent);
            }
            oldKiwi.setDescription(newContent);
            speciesService.saveSpecies(oldKiwi);
        }
        return "redirect:/gatunki/kiwi-plamisty";
    }

    @GetMapping("/edit-history")
    public String showEditHistory(Model model) {
        List<EditHistory> editHistoryList = pageEditService.getEditHistoryByPageName("kiwi-plamisty");
        model.addAttribute("editHistoryList", editHistoryList);
        return "historia-edycji";
    }
}