package com.example.dealsplus.model;

import static com.example.dealsplus.utils.ConstantUtils.*;

public enum PermissionsEnum {

    USER(USER_ROLE),
    STRUCTURE_READ_AUTHORITY("structure:read"),
    STRUCTURE_EDIT_AUTHORITY("structure:read,structure:update"),
    STRUCTURE_ADMIN_AUTHORITY("structure:read,structure:update,structure:create,structure:delete"),
    ADMIN(ADMIN_ROLE);

    private final String value;

    PermissionsEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}