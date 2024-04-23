package com.wap.kiwipedia.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user-roles", joinColumns = @JoinColumn(name = "user-id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role-id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();


}
