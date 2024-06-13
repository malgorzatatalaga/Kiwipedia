package com.example.kiwipedia.backend.controller.kiwi;

import com.example.kiwipedia.backend.model.kiwi.Species;
import com.example.kiwipedia.backend.service.kiwi.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class KiwiController {

    @Autowired
    private SpeciesService speciesService;

    @GetMapping("/gatunki")
    public String gatunkiKiwi() {
        return "gatunki";
    }



    @GetMapping("/kiwi-brunatny/edit")
    public String editKiwiBrunatny(@RequestParam("id") Integer id, Model model) {
        Optional<Species> kiwiOptional = speciesService.getSpeciesById(id);
        if (kiwiOptional.isPresent()) {
            model.addAttribute("kiwi", kiwiOptional.get());
            return "kiwi-brunatny-edit";
        } else {
            model.addAttribute("errorMessage", "Kiwi species not found.");
            return "redirect:/gatunki";
        }
    }

    @PostMapping("/gatunki/update")
    public String updateSpecies(Species species, RedirectAttributes redirectAttributes) {
        speciesService.saveSpecies(species);
        redirectAttributes.addFlashAttribute("successMessage", "Species updated successfully!");
        return "redirect:/gatunki";
    }

    @PostMapping("/gatunki/delete/{id}")
    public String deleteSpecies(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        speciesService.deleteSpecies(id);
        redirectAttributes.addFlashAttribute("successMessage", "Species deleted successfully!");
        return "redirect:/gatunki";
    }

}

