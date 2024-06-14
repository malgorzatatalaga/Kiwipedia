package com.example.kiwipedia.backend.repository.kiwi;

import com.example.kiwipedia.backend.model.kiwi.KiwiFact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KiwiFactsRepository extends JpaRepository<KiwiFact, Integer> {
}
