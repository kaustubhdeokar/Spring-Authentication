package com.example.dealsplus.service;

import com.example.dealsplus.dto.StructureDto;
import com.example.dealsplus.exception.CustomException;
import com.example.dealsplus.model.Structure;
import com.example.dealsplus.model.User;
import com.example.dealsplus.repo.StructureRepo;
import com.example.dealsplus.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StructureService {

    private final StructureRepo structureRepo;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    public StructureService(StructureRepo structureRepo, UserRepo userRepo, ModelMapper modelMapper) {
        this.structureRepo = structureRepo;
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    public void createService(StructureDto structure) {
        Structure map = new Structure(structure.getStructureName());
        saveStructure(map);
    }

    public Structure getStructure(String structureName) {
        Optional<Structure> optionalStructure = structureRepo.findByStructureName(structureName);
        if (optionalStructure.isEmpty()) {
            throw new CustomException("Structure not present");
        }
        return optionalStructure.get();
    }

    public void saveStructure(Structure structure) {
        structureRepo.save(structure);
    }

    public void deleteStructure(Structure structure) {
        structureRepo.delete(structure);
    }

    public StructureDto readStructure(String structureName) {
        Structure structure = getStructure(structureName);
        return new StructureDto(structure.getStructureName());
    }

    private Optional<User> getUser(String username) {
        Optional<User> optionalUser = userRepo.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new CustomException("User not present");
        }
        return optionalUser;
    }

    public List<String> getAll() {
        return structureRepo.findAll().stream().map(Structure::getStructureName).collect(Collectors.toList());
    }
}
