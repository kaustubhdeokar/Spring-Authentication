package com.example.dealsplus.model;


import jakarta.persistence.*;

@Entity
@Table(name = "user_role_mapping")
public class UserRoleMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role roleId;

    @ManyToOne
    @JoinColumn(name = "structureId")
    private Structure structureId;

    public UserRoleMapping(Role roleId, User userId, Structure structureId) {
        this.roleId = roleId;
        this.userId = userId;
        this.structureId = structureId;
    }

    public UserRoleMapping() {

    }
}
