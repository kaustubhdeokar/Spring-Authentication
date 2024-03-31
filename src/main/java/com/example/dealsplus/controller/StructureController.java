package com.example.dealsplus.controller;

import com.example.dealsplus.dto.StructureDto;
import com.example.dealsplus.dto.StructurePermissionDto;
import com.example.dealsplus.model.Role;
import com.example.dealsplus.model.Structure;
import com.example.dealsplus.model.User;
import com.example.dealsplus.repo.UserRepo;
import com.example.dealsplus.service.StructureService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    public User isUserAdmin(String username){
        Optional<User> optionalUser = userRepo.findByUsername(username);
        User user = optionalUser.get();
        Optional<Role> optionalAdminUser = user.getRoles().stream().filter(role -> role.getName().equals("ADMIN")).findFirst();
        if(optionalAdminUser.isPresent()){
            return user;
        }
        return null;
    }

    @PostMapping("/edit/{structureName}/user/{username}")
    public ResponseEntity<String> editStructure(@RequestBody StructureDto structureDto, @PathVariable String username) {
        Structure structure = structureService.getStructure(structureDto.getStructureName());
        Set<User> userWithWritePerm = structure.getUserWithWritePerm();

        User adminUser = isUserAdmin(username);
        if(adminUser!=null){
            return editStructure(structureDto, structure);
        }

        Optional<User> optionalUserWithEditPerm = userWithWritePerm.stream().filter(user1 -> user1.getUsername().equals(username)).findFirst();
        if (optionalUserWithEditPerm.isPresent()) {
            return editStructure(structureDto, structure);
        } else {
            return new ResponseEntity<>("Cannot edit structure:" + structure.getStructureName(), HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<String> editStructure(StructureDto structureDto, Structure structure) {
        structure.setStructureName(structureDto.getStructureName());
        structure.setPrivateData(structureDto.getPrivateData());
        structureService.saveStructure(structure);
        return new ResponseEntity<>("Edited structure:" + structure.getStructureName(), HttpStatus.OK);
    }

    @PostMapping("/delete/{structureName}/user/{username}")
    public ResponseEntity<String> deleteStructure(@PathVariable String structureName, @PathVariable String username) {
        Structure structure = structureService.getStructure(structureName);

        User adminUser = isUserAdmin(username);
        if(adminUser!=null){
            structureService.deleteStructure(structure);
            return new ResponseEntity<>("Deleted structure:" + structureName, HttpStatus.OK);
        }

        Set<User> userWithDeletePerm = structure.getUserWithDeletePerm();
        Optional<User> optionalUser = userWithDeletePerm.stream().filter(user -> user.getUsername().equals(username)).findFirst();
        if (optionalUser.isPresent()) {
            structureService.deleteStructure(structure);
            return new ResponseEntity<>("Deleted structure:" + structureName, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot delete structure:" + structureName, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/read/{structureName}/user/{username}")
    public ResponseEntity<String> readStructure(@PathVariable String structureName, @PathVariable String username) {
        Structure structure = structureService.getStructure(structureName);

        User adminUser = isUserAdmin(username);
        if(adminUser!=null){
            return readStructure(structureName);
        }

        Set<User> userWithReadPerm = structure.getUserWithReadPerm();
        Optional<User> optionalUser = userWithReadPerm.stream().filter(user -> user.getUsername().equals(username)).findFirst();
        if (optionalUser.isPresent()) {
            return readStructure(structureName);
        } else {
            return new ResponseEntity<>("Cannot read structure:" + structureName, HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<String> readStructure(String structureName) {
        StructureDto structureDto = structureService.readStructure(structureName);
        return new ResponseEntity<>("Structure:" + structureDto.toString(), HttpStatus.OK);
    }

    @PostMapping("/addpermission")
    public ResponseEntity<String> addPermission(@RequestBody StructurePermissionDto structurePermissionDto){
        Structure structure = structureService.getStructure(structurePermissionDto.getStructureName());
        String permission = structurePermissionDto.getPermission();
        if(permission.equalsIgnoreCase("read")){
            structureService.addReadPermissionForUser(structure, structurePermissionDto.getUsername());
        }
        else if(permission.equalsIgnoreCase("edit")){
            structureService.addWritePermissionForUser(structure, structurePermissionDto.getUsername());
        }
        else if(permission.equalsIgnoreCase("delete")){
            structureService.addDeletePermissionForUser(structure, structurePermissionDto.getUsername());
        }
        else{
            return new ResponseEntity<>("wrong permission.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("structure created", HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<String> createStructure(@RequestBody StructureDto structure) {
        structureService.createService(structure);
        return new ResponseEntity<>("structure created", HttpStatus.OK);
    }
}
