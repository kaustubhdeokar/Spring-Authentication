package com.example.dealsplus.controller;

import com.example.dealsplus.dto.StructureDto;
import com.example.dealsplus.repo.UserRepo;
import com.example.dealsplus.service.StructureService;
import com.example.dealsplus.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/api/structure")
@CrossOrigin("*")
public class StructureController {

    //todo: read why autowiring is not recommended ?
    //
    @Autowired
    private StructureService structureService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthUtil authUtil;

    public StructureController(StructureService structureService, UserRepo userRepo, UserDetailsServiceImpl userDetailsService) {
        this.structureService = structureService;
        this.userRepo = userRepo;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<String> getAllStructures() {
        List<String> structureName = structureService.getAll();
        String result = String.join("\n", structureName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/edit/{structureName}")
    public ResponseEntity<String> editStructure(@RequestBody StructureDto structureDto) {

        return new ResponseEntity<>("structure edited.", HttpStatus.OK);
    }

    @PostMapping("/delete/{structureName}")
    public ResponseEntity<String> deleteStructure(@PathVariable String structureName) {

        return new ResponseEntity<>("structure deleted.", HttpStatus.OK);
    }

    @PostMapping("/read/{structureName}")
    public ResponseEntity<String> readStructure(@PathVariable String structureName) {

        return new ResponseEntity<>("structure created.", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createStructure(@RequestBody StructureDto structure) {
        return new ResponseEntity<>("structure created.", HttpStatus.OK);
    }
}
