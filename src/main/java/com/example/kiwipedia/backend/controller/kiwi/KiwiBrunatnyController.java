package com.example.kiwipedia.backend.controller.kiwi;

import com.example.kiwipedia.backend.model.EditHistory;
import com.example.kiwipedia.backend.model.kiwi.Species;
import com.example.kiwipedia.backend.service.PageEditService;
import com.example.kiwipedia.backend.service.kiwi.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/gatunki/kiwi-brunatny")
public class KiwiBrunatnyController {

    @Autowired
    private SpeciesService speciesService;

    @Autowired
    private PageEditService pageEditService;

    @GetMapping
    public String kiwiBrunatny(Model model) {
        Optional<Species> kiwiOptional = speciesService.getSpeciesByName("Apteryx australis");
        if (kiwiOptional.isPresent()) {
            model.addAttribute("kiwi", kiwiOptional.get());
        } else {
            model.addAttribute("errorMessage", "Kiwi species not found.");
        }
        return "kiwi-brunatny";
    }

    @GetMapping("/edit")
    public String editKiwiBrunatny(@RequestParam("id") Integer id, Model model) {
        Optional<Species> kiwiOptional = speciesService.getSpeciesById(id);
        if (kiwiOptional.isPresent()) {
            Species kiwi = kiwiOptional.get();
            model.addAttribute("kiwi", kiwi);
            return "kiwi-brunatny-edit";
        } else {
            model.addAttribute("errorMessage", "Species not found.");
            return "redirect:/gatunki";
        }
    }

    @PostMapping("/update")
    public String updateData(@ModelAttribute("kiwi") Species species) {
        Optional<Species> kiwiOptional = speciesService.getSpeciesById(species.getId());
        if (kiwiOptional.isPresent()) {
            Species oldKiwi = kiwiOptional.get();
            String oldContent = oldKiwi.getDescription();
            String newContent = species.getDescription();
            if (!oldContent.equals(newContent)) {
                pageEditService.saveEditHistory("kiwi-brunatny", oldContent, newContent);
            }
            speciesService.saveSpecies(species);
        }
        return "redirect:/gatunki/kiwi-brunatny";
    }

    @PostMapping("/delete/{id}")
    public String deleteKiwiBrunatny(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        speciesService.deleteSpecies(id);
        redirectAttributes.addFlashAttribute("successMessage", "Kiwi brunatny deleted successfully!");
        return "redirect:/gatunki/kiwi-brunatny";
    }

    @GetMapping("/edit-history")
    public String showEditHistory(Model model) {
        List<EditHistory> editHistoryList = pageEditService.getEditHistoryByPageName("kiwi-brunatny");
        model.addAttribute("editHistoryList", editHistoryList);
        return "edit-history";
    }
}
