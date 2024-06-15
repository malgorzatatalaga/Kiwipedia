package com.example.kiwipedia.backend.service;

import com.example.kiwipedia.backend.model.EditHistory;
import com.example.kiwipedia.backend.repository.EditHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditHistoryService {

    @Autowired
    private EditHistoryRepository editHistoryRepository;

    public List<EditHistory> getEditHistoryByPageName(String pageName) {
        return editHistoryRepository.findByPageName(pageName);
    }
}
