package com.example.kiwipedia.backend.service.kiwi;

import com.example.kiwipedia.backend.model.kiwi.Subspecies;
import com.example.kiwipedia.backend.repository.kiwi.SubspeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SubspeciesService {

    @Autowired
    private SubspeciesRepository subspeciesRepository;

    @Transactional
    public List<Subspecies> getSubspeciesBySpeciesId(Integer speciesId) {
        return subspeciesRepository.findBySpeciesId(speciesId);
    }

    @Transactional
    public Optional<Subspecies> getSubspeciesById(Integer id) {
        return subspeciesRepository.findById(id);
    }

    @Transactional
    public Subspecies saveSubspecies(Subspecies subspecies) {
        return subspeciesRepository.save(subspecies);
    }
}
