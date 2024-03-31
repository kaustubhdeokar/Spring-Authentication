package com.example.dealsplus.repo;

import com.example.dealsplus.model.Structure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StructureRepo extends JpaRepository<Structure, Long> {

    public Optional<Structure> findByStructureName(String structureName);

}
