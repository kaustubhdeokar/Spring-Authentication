package com.example.dealsplus.model;

public enum RoleEntity {


    USER(Permissions.USER_AUTHORITY),
    STRUCTURE_ADMIN(Permissions.STRUCTURE_AUTHORITY),
    SUPER_ADMIN(Permissions.ADMIN_AUTHORITY);

    private final String role;

    RoleEntity(String userAuthority) {
        role = userAuthority;
    }

    public String getRole() {
        return role;
    }
}
