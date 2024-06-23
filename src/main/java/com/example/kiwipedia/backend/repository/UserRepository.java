package com.example.kiwipedia.backend.repository;

import com.example.kiwipedia.backend.model.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);

    @Query("SELECT MONTH(u.registrationDate) as month, COUNT(u) as count " +
            "FROM UserEntity u WHERE YEAR(u.registrationDate) = :year " +
            "GROUP BY MONTH(u.registrationDate) " +
            "ORDER BY MONTH(u.registrationDate)")
    List<Object[]> findMonthlyRegistrations(int year);

}
