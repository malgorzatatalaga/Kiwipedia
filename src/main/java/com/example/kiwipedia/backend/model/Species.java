package com.example.kiwipedia.backend.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "species")
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "common_name")
    private String commonName;

    @Column(length = 1000)
    private String description;

    // Adding physical characteristics
    @Column(name = "female_weight")
    private Integer femaleWeight;

    @Column(name = "male_weight")
    private Integer maleWeight;

    @Column(name = "body_length")
    private Integer bodyLength;

    @Column(name = "female_beak_length")
    private Integer femaleBeakLength;

    @Column(name = "male_beak_length")
    private Integer maleBeakLength;

    @OneToMany(mappedBy = "species", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Subspecies> subspecies = new HashSet<>();

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFemaleWeight() {
        return femaleWeight;
    }

    public void setFemaleWeight(Integer femaleWeight) {
        this.femaleWeight = femaleWeight;
    }

    public Integer getMaleWeight() {
        return maleWeight;
    }

    public void setMaleWeight(Integer maleWeight) {
        this.maleWeight = maleWeight;
    }

    public Integer getBodyLength() {
        return bodyLength;
    }

    public void setBodyLength(Integer bodyLength) {
        this.bodyLength = bodyLength;
    }

    public Integer getFemaleBeakLength() {
        return femaleBeakLength;
    }

    public void setFemaleBeakLength(Integer femaleBeakLength) {
        this.femaleBeakLength = femaleBeakLength;
    }

    public Integer getMaleBeakLength() {
        return maleBeakLength;
    }

    public void setMaleBeakLength(Integer maleBeakLength) {
        this.maleBeakLength = maleBeakLength;
    }

    public Set<Subspecies> getSubspecies() {
        return subspecies;
    }

    public void setSubspecies(Set<Subspecies> subspecies) {
        this.subspecies = subspecies;
    }
}
