package com.example.dealsplus.controller;

import com.example.dealsplus.dto.StructureDto;
import com.example.dealsplus.dto.StructurePermissionDto;
import com.example.dealsplus.exception.CustomException;
import com.example.dealsplus.model.Role;
import com.example.dealsplus.model.Structure;
import com.example.dealsplus.model.User;
import com.example.dealsplus.repo.UserRepo;
import com.example.dealsplus.service.AuthService;
import com.example.dealsplus.service.StructureService;
import com.example.dealsplus.service.UserDetailsServiceImpl;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/structure")
@AllArgsConstructor
@CrossOrigin("*")
public class StructureController {

    @Autowired
    StructureService structureService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    public User isUserAdmin(String username) {
        Optional<User> optionalUser = userRepo.findByUsername(username);
        User user = optionalUser.get();
        Optional<Role> optionalAdminUser = user.getRoles().stream().filter(role -> role.getName().equals("ADMIN")).findFirst();
        if (optionalAdminUser.isPresent()) {
            return user;
        }
        return null;
    }

    private ResponseEntity<String> editStructure(StructureDto structureDto, Structure structure) {
        structure.setStructureName(structureDto.getStructureName());
        structure.setPrivateData(structureDto.getPrivateData());
        structureService.saveStructure(structure);
        return new ResponseEntity<>("Edited structure:" + structure.getStructureName(), HttpStatus.OK);
    }

    @PostMapping("/edit/{structureName}")
    public ResponseEntity<String> editStructure(@RequestBody StructureDto structureDto) {

        Structure structure = structureService.getStructure(structureDto.getStructureName());
        Set<User> userWithWritePerm = structure.getUserWithWritePerm();
        User principalUser = userDetailsService.getPrincipalUser();
        User adminUser = isUserAdmin(principalUser.getUsername());
        if (adminUser != null) {
            return editStructure(structureDto, structure);
        }

        Optional<User> optionalUserWithEditPerm = userWithWritePerm.stream().filter(user1 -> user1.getUsername().equals(principalUser.getUsername())).findFirst();
        if (optionalUserWithEditPerm.isPresent()) {
            return editStructure(structureDto, structure);
        } else {
            return new ResponseEntity<>("Cannot edit structure:" + structure.getStructureName(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete/{structureName}")
    public ResponseEntity<String> deleteStructure(@PathVariable String structureName) {
        Structure structure = structureService.getStructure(structureName);
        User principalUser = userDetailsService.getPrincipalUser();
        User adminUser = isUserAdmin(principalUser.getUsername());
        if (adminUser != null) {
            structureService.deleteStructure(structure);
            return new ResponseEntity<>("Deleted structure:" + structureName, HttpStatus.OK);
        }

        Set<User> userWithDeletePerm = structure.getUserWithDeletePerm();
        Optional<User> optionalUser = userWithDeletePerm.stream().filter(user -> user.getUsername().equals(principalUser.getUsername())).findFirst();
        if (optionalUser.isPresent()) {
            structureService.deleteStructure(structure);
            return new ResponseEntity<>("Deleted structure:" + structureName, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot delete structure:" + structureName, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/read/{structureName}")
    public ResponseEntity<String> readStructure(@PathVariable String structureName) {
        Structure structure = structureService.getStructure(structureName);
        User principalUser = userDetailsService.getPrincipalUser();
        User adminUser = isUserAdmin(principalUser.getUsername());
        if (adminUser != null) {
            return implReadStructure(structureName);
        }
        Set<User> userWithReadPerm = structure.getUserWithReadPerm();
        Optional<User> optionalUser = userWithReadPerm.stream().filter(user -> user.getUsername().equals(principalUser.getUsername())).findFirst();
        if (optionalUser.isPresent()) {
            return implReadStructure(structureName);
        } else {
            return new ResponseEntity<>("Cannot read structure:" + structureName, HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<String> implReadStructure(String structureName) {
        StructureDto structureDto = structureService.readStructure(structureName);
        return new ResponseEntity<>("Structure:" + structureDto.toString(), HttpStatus.OK);
    }

    @PostMapping("/addpermission")
    public ResponseEntity<String> addPermission(@RequestBody StructurePermissionDto structurePermissionDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User adminUser = isUserAdmin(name);
        String userName = adminUser!=null ? adminUser.getUsername() : null;
        Structure structure = structureService.getStructure(structurePermissionDto.getStructureName());
        Optional<User> userHasStructureAdminPerm = structure.getUserWithAdminPerm().stream().filter(user -> user.getUsername().equals(name)).findFirst();
        if (adminUser != null || userHasStructureAdminPerm.isPresent()) {
            userName = userName==null ? userHasStructureAdminPerm.get().getUsername() : null;

            String permission = structurePermissionDto.getPermission();
            if (permission.equalsIgnoreCase("read")) {
                structureService.addReadPermissionForUser(structure, structurePermissionDto.getUsername());
            } else if (permission.equalsIgnoreCase("edit")) {
                structureService.addWritePermissionForUser(structure, structurePermissionDto.getUsername());
            } else if (permission.equalsIgnoreCase("delete")) {
                structureService.addDeletePermissionForUser(structure, structurePermissionDto.getUsername());
            } else if (permission.equalsIgnoreCase("admin")) {
                structureService.addAdminPermissionForUser(structure, structurePermissionDto.getUsername());
            } else {
                return new ResponseEntity<>("wrong permission.", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(permission + " permission added for user: " + structurePermissionDto.getUsername(), HttpStatus.OK);

        }

        return new ResponseEntity<>("Admin privileges needed for permission manipulation.", HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/create")
    public ResponseEntity<String> createStructure(@RequestBody StructureDto structure) {

        User principalUser = userDetailsService.getPrincipalUser();
        if (isUserAdmin(principalUser.getUsername()) != null) {
            try {
                structureService.createService(structure);
            }
            catch (DataIntegrityViolationException th){
                return new ResponseEntity<>("Structure with similar name already exists.", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("structure created", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Only users with admin privilege can create structure.", HttpStatus.BAD_REQUEST);
        }
    }
}
