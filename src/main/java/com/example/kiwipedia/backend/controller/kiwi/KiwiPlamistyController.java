package com.example.kiwipedia.backend.controller.kiwi;

import com.example.kiwipedia.backend.model.EditHistory;
import com.example.kiwipedia.backend.model.kiwi.Species;
import com.example.kiwipedia.backend.model.kiwi.Taxonomy;
import com.example.kiwipedia.backend.service.PageEditService;
import com.example.kiwipedia.backend.service.kiwi.TaxonomyService;
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
    private TaxonomyService taxonomyService;

    @Autowired
    private PageEditService pageEditService;

    @GetMapping
    public String kiwiPlamisty(Model model) {
        Integer fixedId = 2;
        Optional<Species> kiwiOptional = speciesService.getSpeciesById(fixedId);
        if (kiwiOptional.isPresent()) {
            Species kiwi = kiwiOptional.get();
            model.addAttribute("kiwi", kiwi);

            List<Taxonomy> taxonomyList = taxonomyService.getTaxonomyBySpeciesId(fixedId);
            model.addAttribute("taxonomyList", taxonomyList);
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

            List<Taxonomy> taxonomyList = taxonomyService.getTaxonomyBySpeciesId(fixedId);
            model.addAttribute("taxonomyList", taxonomyList);
            return "kiwi-plamisty-edit";
        } else {
            model.addAttribute("errorMessage", "Species not found.");
            return "redirect:/gatunki";
        }
    }

    @PostMapping("/update")
    public String updateKiwiPlamisty(@ModelAttribute("kiwi") Species species, @RequestParam("taxonomyId") List<Integer> taxonomyIds, @RequestParam("additionalInfo") List<String> additionalInfoList, @RequestParam("info") List<String> infoList) {
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

            // Update taxonomy info
            for (int i = 0; i < taxonomyIds.size(); i++) {
                Taxonomy taxonomy = taxonomyService.getTaxonomyById(taxonomyIds.get(i)).orElse(new Taxonomy());
                String oldAdditionalInfo = taxonomy.getAdditionalInfo();
                String oldInfo = taxonomy.getInfo();

                taxonomy.setAdditionalInfo(additionalInfoList.get(i));
                taxonomy.setInfo(infoList.get(i));
                taxonomy.setSpeciesId(fixedId);

                if (!oldAdditionalInfo.equals(taxonomy.getAdditionalInfo()) || !oldInfo.equals(taxonomy.getInfo())) {
                    pageEditService.saveEditHistory("kiwi-plamisty", oldAdditionalInfo + ", " + oldInfo, taxonomy.getAdditionalInfo() + ", " + taxonomy.getInfo());
                }
                taxonomyService.saveTaxonomy(taxonomy);
            }
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
