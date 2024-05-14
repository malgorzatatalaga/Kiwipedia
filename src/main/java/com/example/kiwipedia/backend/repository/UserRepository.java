package com.example.kiwipedia.backend.repository;

import com.example.kiwipedia.backend.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
}
