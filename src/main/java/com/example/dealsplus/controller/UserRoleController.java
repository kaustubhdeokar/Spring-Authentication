package com.example.dealsplus.controller;

import com.example.dealsplus.dto.UserRoleDto;
import com.example.dealsplus.service.AuthService;
import com.example.dealsplus.service.UserService;
import com.example.dealsplus.utils.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/assign")
@CrossOrigin("*")
public class UserRoleController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @GetMapping("/get-roles/{username}")
    public ResponseEntity<String> getRolesForUser(@PathVariable String username) {
        userService.checkForAuthorization(ConstantUtils.ADMIN_ROLE);
        Set<SimpleGrantedAuthority> rolesForUser = userService.getRolesForUser(username);
        String roles = rolesForUser.stream().map(SimpleGrantedAuthority::getAuthority).collect(Collectors.joining());
        return ResponseEntity.status(HttpStatus.OK).body(roles);
    }

    @PostMapping("/")
    public ResponseEntity<String> assignRolesToUser(@RequestBody UserRoleDto userRoleDto) {
        userService.addRoleForUser(userRoleDto.getRole(), userRoleDto.getUser());
        return ResponseEntity.status(HttpStatus.OK).body("Role: " + userRoleDto.getRole() + " added for user: " + userRoleDto.getUser());
    }

}
