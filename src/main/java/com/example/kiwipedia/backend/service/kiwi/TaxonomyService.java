package com.example.kiwipedia.backend.service.kiwi;

import com.example.kiwipedia.backend.model.kiwi.Taxonomy;
import com.example.kiwipedia.backend.repository.kiwi.TaxonomyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaxonomyService {

    @Autowired
    private TaxonomyRepository taxonomyRepository;

    @Transactional
    public List<Taxonomy> getTaxonomyBySpeciesId(Integer speciesId) {
        return taxonomyRepository.findBySpeciesId(speciesId);
    }

    @Transactional
    public Taxonomy saveTaxonomy(Taxonomy taxonomy) {
        return taxonomyRepository.save(taxonomy);
    }

    @Transactional
    public Optional<Taxonomy> getTaxonomyById(Integer id) {
        return taxonomyRepository.findById(id);
    }
}
