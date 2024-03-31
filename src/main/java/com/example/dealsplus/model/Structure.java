package com.example.dealsplus.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "structure")
public class Structure {

    @Id
    @GeneratedValue
    private Long structureid;

    private String structureName;

    private String privateData = "privateData";

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "structure_read_users",
            joinColumns = {@JoinColumn(name = "structureid", referencedColumnName = "structureid")},
            inverseJoinColumns = {@JoinColumn(name = "userid", referencedColumnName = "userid")})
    private Set<User> userWithReadPerm = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "structure_write_users",
            joinColumns = {@JoinColumn(name = "structureid", referencedColumnName = "structureid")},
            inverseJoinColumns = {@JoinColumn(name = "userid", referencedColumnName = "userid")})
    private Set<User> userWithWritePerm = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "structure_delete_users",
            joinColumns = {@JoinColumn(name = "structureid", referencedColumnName = "structureid")},
            inverseJoinColumns = {@JoinColumn(name = "userid", referencedColumnName = "userid")})
    private Set<User> userWithDeletePerm = new HashSet<>();

    public Structure(String structureName, String privateData) {
        this.structureName = structureName;
        this.privateData = privateData;
    }

    public Structure() {

    }
}
