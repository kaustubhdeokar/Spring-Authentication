package com.example.dealsplus.controller;

import com.example.dealsplus.dto.UserRoleDto;
import com.example.dealsplus.service.AuthService;
import com.example.dealsplus.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assign")
@CrossOrigin("*")
public class UserRoleController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/get-roles/{username}")
    public ResponseEntity<String> getRolesForUser(@PathVariable String username) {
        authService.getRolesForUser(username);
        return ResponseEntity.status(HttpStatus.OK).body("Roles queried.");
    }

    @PostMapping("/")
    public ResponseEntity<String> assignRolesToUser(@RequestBody UserRoleDto userRoleDto) {
        String roleName = userRoleDto.getRoleName();
        String user = userRoleDto.getUsername();
        String structure = userRoleDto.getStructureName();
        userRoleService.createRoleForUserInStructure(roleName, user, structure);
        return ResponseEntity.status(HttpStatus.OK).body("Added role:"+roleName+" for user:"+ user+" for structure:"+structure);
    }

}
