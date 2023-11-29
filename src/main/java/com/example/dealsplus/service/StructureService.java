package com.example.dealsplus.service;

import com.example.dealsplus.dto.StructureDto;
import com.example.dealsplus.exception.CustomException;
import com.example.dealsplus.model.Structure;
import com.example.dealsplus.repo.StructureRepo;
import com.example.dealsplus.utils.ConstantUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StructureService {

    private final StructureRepo structureRepo;
    private final UserService userService;

    public StructureService(StructureRepo structureRepo, UserService userService) {
        this.structureRepo = structureRepo;
        this.userService = userService;
    }


    public Structure getStructure(String structureName) {
        Optional<Structure> optionalStructure = structureRepo.findByStructureName(structureName);
        if (optionalStructure.isEmpty()) {
            throw new CustomException("Structure not present");
        }
        return optionalStructure.get();
    }

    public StructureDto getStructureDto(Structure structure){
        return new StructureDto(structure.getStructureName(), structure.getStructureInfo());
    }

    public String createService(StructureDto structureDto) {
        userService.checkForAuthorization(ConstantUtils.createPermissionForEntity.apply(ConstantUtils.STRUCTURE_ENTITY, ConstantUtils.CREATE_PERM_STRING));
        Structure structure = new Structure(structureDto.getStructureName());
        structureRepo.save(structure);
        return structureDto.getStructureName();
    }

    public void deleteStructure(String structureName) {
        userService.checkForAuthorization(ConstantUtils.createPermissionForEntity.apply(ConstantUtils.STRUCTURE_ENTITY , ConstantUtils.DELETE_PERM_STRING));
        Structure structure = getStructure(structureName);
        structureRepo.delete(structure);
    }

    public StructureDto readStructure(String structureName) {
        userService.checkForAuthorization(ConstantUtils.createPermissionForEntity.apply(ConstantUtils.STRUCTURE_ENTITY , ConstantUtils.READ_PERM_STRING));
        Structure structure = getStructure(structureName);
        return new StructureDto(structure.getStructureName(), structure.getStructureInfo());
    }

    public List<String> getAll() {
        userService.checkForAuthorization(ConstantUtils.createPermissionForEntity.apply(ConstantUtils.STRUCTURE_ENTITY , ConstantUtils.READ_PERM_STRING));
        return structureRepo.findAll().stream().map(Structure::getStructureName).collect(Collectors.toList());
    }

    public void updateStructure(StructureDto structureDto) {
        userService.checkForAuthorization(ConstantUtils.createPermissionForEntity.apply(ConstantUtils.STRUCTURE_ENTITY , ConstantUtils.UPDATE_PERM_STRING));
        Structure structure = getStructure(structureDto.getStructureName());
        structure.setStructureInfo(structureDto.getStructureInfo());
        structureRepo.save(structure);
    }
}
