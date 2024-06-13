package com.example.kiwipedia.backend.repository.kiwi;

import com.example.kiwipedia.backend.model.kiwi.Taxonomy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxonomyRepository extends JpaRepository<Taxonomy, Integer> {
}
