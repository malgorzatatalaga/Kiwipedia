package com.example.kiwipedia.backend.repository.kiwi;

import com.example.kiwipedia.backend.model.kiwi.Subspecies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubspeciesRepository extends JpaRepository<Subspecies, Integer> {
    List<Subspecies> findBySpeciesId(Integer speciesId);
}
