package com.example.kiwipedia.backend.repository;

import com.example.kiwipedia.backend.model.EditHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EditHistoryRepository extends JpaRepository<EditHistory, Integer> {
    List<EditHistory> findByPageName(String pageName);
}
