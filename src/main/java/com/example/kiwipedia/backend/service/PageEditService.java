package com.example.kiwipedia.backend.service;

import com.example.kiwipedia.backend.model.EditHistory;
import com.example.kiwipedia.backend.model.UserEntity;
import com.example.kiwipedia.backend.repository.EditHistoryRepository;
import com.example.kiwipedia.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PageEditService {

    @Autowired
    private EditHistoryRepository editHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    public void saveEditHistory(String pageName, String oldText, String newText) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        EditHistory editHistory = new EditHistory();
        editHistory.setPageName(pageName);
        editHistory.setOldText(oldText);
        editHistory.setNewText(newText);
        editHistory.setEditTime(LocalDateTime.now());
        editHistory.setEditedBy(user);

        System.out.println("Saving edit history: " + editHistory);

        editHistoryRepository.save(editHistory);
    }

    public List<EditHistory> getEditHistoryByPageName(String pageName) {
        return editHistoryRepository.findByPageName(pageName);
    }
}
