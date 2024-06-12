package com.example.kiwipedia.backend.service;

import com.example.kiwipedia.backend.model.Species;
import com.example.kiwipedia.backend.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpeciesService {

    @Autowired
    private SpeciesRepository speciesRepository;

    // Istniejąca metoda do wyszukiwania gatunków po nazwie
    public Optional<Species> getSpeciesByName(String name) {
        return speciesRepository.findByName(name);
    }

    // Metoda do wyszukiwania gatunku po ID
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


