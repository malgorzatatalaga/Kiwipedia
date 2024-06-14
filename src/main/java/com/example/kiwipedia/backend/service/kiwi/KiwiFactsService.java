package com.example.kiwipedia.backend.service.kiwi;

import com.example.kiwipedia.backend.model.kiwi.KiwiFact;
import com.example.kiwipedia.backend.repository.kiwi.KiwiFactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KiwiFactsService {

    private final KiwiFactsRepository kiwiFactsRepository;

    @Autowired
    public KiwiFactsService(KiwiFactsRepository kiwiFactsRepository) {
        this.kiwiFactsRepository = kiwiFactsRepository;
    }

    public List<KiwiFact> getAllKiwiFacts() {
        return kiwiFactsRepository.findAll();
    }

    public void saveKiwiFact(KiwiFact kiwiFact) {
        kiwiFactsRepository.save(kiwiFact);
    }
}
