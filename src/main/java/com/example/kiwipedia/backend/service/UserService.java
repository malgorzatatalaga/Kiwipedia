package com.example.kiwipedia.backend.service;

import com.example.kiwipedia.backend.dto.RegisterDTO;
import com.example.kiwipedia.backend.model.Role;
import com.example.kiwipedia.backend.model.UserEntity;
import com.example.kiwipedia.backend.repository.RoleRepository;
import com.example.kiwipedia.backend.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    public void register(RegisterDTO registerDTO) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(registerDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        newUser.setRegistrationDate(LocalDateTime.now());

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Error: Role USER is not found."));
        newUser.setRoles(List.of(userRole));

        userRepository.save(newUser);

    }

    @Transactional
    public void deleteUserById(Integer id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        user.getRoles().clear();
        entityManager.merge(user);
        userRepository.deleteById(id);
    }

    public UserEntity getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public void updateUsernameAndRole(int id, String username, String roleName) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(username);

        Role newRole = roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRoles(new ArrayList<>(Collections.singletonList(newRole)));

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<Object[]> getMonthlyRegistrations(int year) {
        return userRepository.findMonthlyRegistrations(year);
    }
}
