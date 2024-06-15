package com.example.dealsplus.repo;

import com.example.dealsplus.model.Structure;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StructureRepo extends JpaRepository<Structure, Long> {

    Optional<Structure> findByStructureName(String structureName);

    @Override
    List<Structure> findAll();
}
