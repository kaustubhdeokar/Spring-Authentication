package com.example.dealsplus.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "structure")
public class Structure {

    @Id
    @GeneratedValue
    private Long structureId;

    @Column(unique = true)
    private String structureName;

//    @OneToMany(mappedBy = "structureId", cascade = CascadeType.ALL)
//    private Set<UserRoleMapping> userRoleMappings;

    public Structure(String structureName) {
        this.structureName = structureName;
    }

    public Structure() {

    }
}
