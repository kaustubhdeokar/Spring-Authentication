package com.example.dealsplus.model;

public class Permissions {

    public static final String ROLE_PREFIX = "ROLE_";
    public static final String AUTHORITY_DELIMITER = ",";
    public static final String EMPTY_VALUE = "empty";
    public static final String AUTHORITIES = "authorities";
    public static final String ROLE = "role";

    public static final String USER_AUTHORITY = "user:create,user:read,user:update,user:delete";
    public static final String STRUCTURE_AUTHORITY = "structure:create,structure:read,structure:update,structure:delete";

    public static final String ADMIN_AUTHORITY = USER_AUTHORITY + AUTHORITY_DELIMITER + STRUCTURE_AUTHORITY;

}