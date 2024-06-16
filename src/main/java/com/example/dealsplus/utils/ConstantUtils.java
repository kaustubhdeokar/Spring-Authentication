package com.example.dealsplus.utils;

import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class ConstantUtils {

    public static final String USER_ROLE = "user";
    public static final String ADMIN_ROLE = "admin";

    public static final String READ_PERM_STRING = "read";
    public static final String UPDATE_PERM_STRING = "update";
    public static final String CREATE_PERM_STRING = "create";
    public static final String DELETE_PERM_STRING = "delete";
    public static final String STRUCTURE_ENTITY = "structure";
    public static final String COMPANY_ENTITY = "company";

    public static final BiFunction<String, String, String> createPermissionForEntity = (entity, permission) -> String.join(":", entity, permission);

}
