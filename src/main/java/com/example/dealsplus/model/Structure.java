package com.example.dealsplus.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "structure")
public class Structure {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long structureId;

    @Column(unique = true)
    private String structureName;

    private String structureInfo;


    public Structure(String structureName) {
        this.structureName = structureName;
    }

    public Structure() {

    }
}
