package com.example.kiwipedia.backend.controller.kiwi;

import com.example.kiwipedia.backend.model.EditHistory;
import com.example.kiwipedia.backend.model.kiwi.Species;
import com.example.kiwipedia.backend.model.kiwi.Subspecies;
import com.example.kiwipedia.backend.service.PageEditService;
import com.example.kiwipedia.backend.service.kiwi.SpeciesService;
import com.example.kiwipedia.backend.service.kiwi.SubspeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/gatunki/kiwi-brunatny")
public class KiwiBrunatnyController {

    @Autowired
    private SpeciesService speciesService;

    @Autowired
    private SubspeciesService subspeciesService;

    @Autowired
    private PageEditService pageEditService;

    @GetMapping
    public String kiwiBrunatny(Model model) {
        Integer fixedId = 1;
        Optional<Species> kiwiOptional = speciesService.getSpeciesById(fixedId);
        if (kiwiOptional.isPresent()) {
            Species kiwi = kiwiOptional.get();
            model.addAttribute("kiwi", kiwi);

            List<Subspecies> subspeciesList = subspeciesService.getSubspeciesBySpeciesId(fixedId);
            model.addAttribute("subspeciesList", subspeciesList);
        } else {
            model.addAttribute("errorMessage", "Kiwi species not found.");
        }
        return "kiwi-brunatny";
    }

    @GetMapping("/edit")
    public String editKiwiBrunatny(Model model) {
        Integer fixedId = 1;
        Optional<Species> kiwiOptional = speciesService.getSpeciesById(fixedId);
        if (kiwiOptional.isPresent()) {
            Species kiwi = kiwiOptional.get();
            model.addAttribute("kiwi", kiwi);

            List<Subspecies> subspeciesList = subspeciesService.getSubspeciesBySpeciesId(fixedId);
            model.addAttribute("subspeciesList", subspeciesList);
            return "kiwi-brunatny-edit";
        } else {
            model.addAttribute("errorMessage", "Species not found.");
            return "redirect:/gatunki";
        }
    }

    @PostMapping("/update")
    public String updateKiwiBrunatny(@ModelAttribute("kiwi") Species species, @RequestParam("subspeciesId") List<Integer> subspeciesIds, @RequestParam("subspeciesName") List<String> subspeciesNames, @RequestParam("subspeciesDescription") List<String> subspeciesDescriptions) {
        Integer fixedId = 1;
        Optional<Species> kiwiOptional = speciesService.getSpeciesById(fixedId);
        if (kiwiOptional.isPresent()) {
            Species oldKiwi = kiwiOptional.get();
            String oldContent = oldKiwi.getDescription();
            String newContent = species.getDescription();
            if (!oldContent.equals(newContent)) {
                pageEditService.saveEditHistory("kiwi-brunatny", oldContent, newContent);
            }
            oldKiwi.setDescription(newContent);
            speciesService.saveSpecies(oldKiwi);

            for (int i = 0; i < subspeciesIds.size(); i++) {
                Subspecies oldSubspecies = subspeciesService.getSubspeciesById(subspeciesIds.get(i)).orElse(new Subspecies());
                Subspecies newSubspecies = new Subspecies();
                newSubspecies.setId(subspeciesIds.get(i));
                newSubspecies.setName(subspeciesNames.get(i));
                newSubspecies.setDescription(subspeciesDescriptions.get(i));
                newSubspecies.setSpeciesId(fixedId);

                pageEditService.saveSubspeciesEditHistory(oldSubspecies, newSubspecies);
                subspeciesService.saveSubspecies(newSubspecies);
            }
        }
        return "redirect:/gatunki/kiwi-brunatny";
    }

    @GetMapping("/edit-history")
    public String showEditHistory(Model model) {
        List<EditHistory> editHistoryList = pageEditService.getEditHistoryByPageName("kiwi-brunatny");
        model.addAttribute("editHistoryList", editHistoryList);
        return "historia-edycji";
    }
}
