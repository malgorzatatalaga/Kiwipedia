package com.example.kiwipedia.backend.service.kiwi;

import com.example.kiwipedia.backend.model.kiwi.Species;
import com.example.kiwipedia.backend.repository.kiwi.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpeciesService {

    @Autowired
    private SpeciesRepository speciesRepository;
    public Optional<Species> getSpeciesByName(String name) {
        return speciesRepository.findByName(name);
    }

    public Optional<Species> getSpeciesById(Integer id) {
        return speciesRepository.findById(id);
    }

    public Species saveSpecies(Species species) {
        return speciesRepository.save(species);
    }

    public void deleteSpecies(Integer id) {
        speciesRepository.deleteById(id);
    }
}


