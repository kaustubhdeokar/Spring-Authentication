package com.example.dealsplus.repo;

import com.example.dealsplus.model.Company;
import com.example.dealsplus.model.Structure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepo extends JpaRepository<Company, Long> {

    Optional<Company> findByName(String companyName);
    @Override
    List<Company> findAll();
}