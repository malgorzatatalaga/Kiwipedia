package com.example.kiwipedia.backend.service;

import com.example.kiwipedia.backend.model.EditHistory;
import com.example.kiwipedia.backend.repository.EditHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EditHistoryService {

    @Autowired
    private EditHistoryRepository editHistoryRepository;

    public List<EditHistory> getEditHistoryByPageName(String pageName) {
        return editHistoryRepository.findByPageName(pageName);
    }

    public Map<String, Integer> getPageEditCounts() {
        List<Object[]> results = editHistoryRepository.countEditsPerPage();
        Map<String, Integer> pageEditCounts = new HashMap<>();
        for (Object[] result : results) {
            pageEditCounts.put((String) result[0], ((Long) result[1]).intValue());
        }
        return pageEditCounts;
    }
}
