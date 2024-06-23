package com.example.kiwipedia.backend.model.kiwi;

import jakarta.persistence.*;

@Entity
@Table(name = "taxonomy")
public class Taxonomy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "info")
    private String info;

    @Column(name = "species_id")
    private Integer speciesId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(Integer speciesId) {
        this.speciesId = speciesId;
    }
}
