package com.example.kiwipedia.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.kiwipedia.backend.model.Species;
import com.example.kiwipedia.backend.repository.SpeciesRepository;

import java.util.Optional;

@Service
public class SpeciesService {

    @Autowired
    private SpeciesRepository speciesRepository;

    public Optional<Species> getSpeciesByName(String name) {
        return speciesRepository.findByName(name);
    }

    public Species saveSpecies(Species species) {
        return speciesRepository.save(species);
    }

    public void deleteSpecies(Integer id) {
        speciesRepository.deleteById(id);
    }
}


