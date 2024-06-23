package com.example.kiwipedia.backend.repository;

import com.example.kiwipedia.backend.model.EditHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EditHistoryRepository extends JpaRepository<EditHistory, Integer> {
    List<EditHistory> findByPageName(String pageName);

    @Query("SELECT eh.pageName, COUNT(eh.id) FROM EditHistory eh GROUP BY eh.pageName")
    List<Object[]> countEditsPerPage();
}
