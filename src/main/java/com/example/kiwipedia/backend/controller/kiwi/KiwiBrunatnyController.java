package com.example.kiwipedia.backend.controller.kiwi;

import com.example.kiwipedia.backend.model.kiwi.Species;
import com.example.kiwipedia.backend.service.kiwi.SpeciesService;
import com.example.kiwipedia.backend.service.kiwi.TaxonomyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/gatunki/kiwi-brunatny")
public class KiwiBrunatnyController {

    @Autowired
    private SpeciesService speciesService;
    @Autowired
    private TaxonomyService taxonomyService;

    @GetMapping
    public String kiwiBrunatny(Model model) {
        Optional<Species> kiwiOptional = speciesService.getSpeciesByName("Apteryx australis");
        if (kiwiOptional.isPresent()) {
            model.addAttribute("kiwi", kiwiOptional.get());
        } else {
            model.addAttribute("errorMessage", "Kiwi species not found.");
        }
        model.addAttribute("taxonomies", taxonomyService.findAllTaxonomies());
        return "kiwi-brunatny";
    }

    @GetMapping("/edit")
    public String editKiwiBrunatny(@RequestParam("id") Integer id, Model model) {
        Optional<Species> kiwiOptional = speciesService.getSpeciesById(id);
        if (kiwiOptional.isPresent()) {
            model.addAttribute("kiwi", kiwiOptional.get());
            return "kiwi-brunatny-edit";
        } else {
            model.addAttribute("errorMessage", "Kiwi species not found.");
            return "redirect:/gatunki/kiwi-brunatny";
        }
    }

    @PostMapping("/update")
    public String updateKiwiBrunatny(Species species, RedirectAttributes redirectAttributes) {
        speciesService.saveSpecies(species);
        redirectAttributes.addFlashAttribute("successMessage", "Kiwi brunatny updated successfully!");
        return "redirect:/gatunki/kiwi-brunatny";
    }

    @PostMapping("/delete/{id}")
    public String deleteKiwiBrunatny(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        speciesService.deleteSpecies(id);
        redirectAttributes.addFlashAttribute("successMessage", "Kiwi brunatny deleted successfully!");
        return "redirect:/gatunki/kiwi-brunatny";
    }
}
