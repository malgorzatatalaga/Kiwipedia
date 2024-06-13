package com.example.kiwipedia.backend.service.kiwi;

import com.example.kiwipedia.backend.model.kiwi.Subspecies;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.kiwipedia.backend.repository.kiwi.SubspeciesRepository;
import java.util.List;

@Service
public class SubspeciesService {
    @Autowired
    private SubspeciesRepository subspeciesRepository;

    public List<Subspecies> findAllSubspecies() {
        return subspeciesRepository.findAll();
    }
}

