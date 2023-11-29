package com.example.dealsplus.controller;

import com.example.dealsplus.dto.StructureDto;
import com.example.dealsplus.model.Structure;
import com.example.dealsplus.service.StructureService;
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

    @Autowired
    private StructureService structureService;

    public StructureController(StructureService structureService) {
        this.structureService = structureService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<String> getAllStructures() {
        List<String> structureName = structureService.getAll();
        String result = String.join("\n", structureName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/get-by-name/{structure}")
    public ResponseEntity<StructureDto> getStructureByName(@PathVariable String structure) {
        Structure struct = structureService.getStructure(structure);
        StructureDto structureDto = structureService.getStructureDto(struct);
        return new ResponseEntity<>(structureDto, HttpStatus.OK);
    }

    @PostMapping("/read/{structureName}")
    public ResponseEntity<StructureDto> readStructure(@PathVariable String structureName) {
        StructureDto structureDto = structureService.readStructure(structureName);
        return new ResponseEntity<>(structureDto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createStructure(@RequestBody StructureDto structure) {
        String structureName = structureService.createService(structure);
        return new ResponseEntity<>("structure created: " + structureName, HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<String> editStructure(@RequestBody StructureDto structureDto) {
        structureService.updateStructure(structureDto);
        return new ResponseEntity<>("structure edited.", HttpStatus.OK);
    }

    @PostMapping("/delete/{structureName}")
    public ResponseEntity<String> deleteStructure(@PathVariable String structureName) {
        structureService.deleteStructure(structureName);
        return new ResponseEntity<>("Structure deleted.", HttpStatus.OK);
    }
}
