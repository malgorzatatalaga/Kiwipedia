package com.example.kiwipedia.backend.service.kiwi;

import com.example.kiwipedia.backend.model.kiwi.Taxonomy;
import com.example.kiwipedia.backend.repository.kiwi.TaxonomyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxonomyService {
    @Autowired
    private TaxonomyRepository taxonomyRepository;

    public List<Taxonomy> findAllTaxonomies() {
        return taxonomyRepository.findAll();
    }
}
