package com.example.kiwipedia.backend.repository.kiwi;

import com.example.kiwipedia.backend.model.kiwi.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {
    Optional<Species> findByName(String name);
}
