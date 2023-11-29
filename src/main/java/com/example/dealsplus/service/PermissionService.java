package com.example.dealsplus.service;

import com.example.dealsplus.model.PermissionsEnum;
import com.example.dealsplus.model.Role;
import com.example.dealsplus.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionService {

    private final AuthService authService;


    public PermissionService(AuthService authService) {
        this.authService = authService;

    }

    public String getPermissionFromRole(String role) {
        return PermissionsEnum.valueOf(role).getValue();
    }

    public String getPermissionFromUser(){
        String currentUser = UserService.getNameForCurrentUser();
        User user = authService.getUser(currentUser);
        List<Role> roles = user.getRoles();
        String permissions = roles.stream().map(role -> getPermissionFromRole(role.getName())).collect(Collectors.joining());
        System.out.println("All permissions for user:"+permissions);
        return permissions;
    }

}
