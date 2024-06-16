package com.example.dealsplus.controller;

import com.example.dealsplus.dto.CompanyDto;
import com.example.dealsplus.model.Company;
import com.example.dealsplus.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
@CrossOrigin("*")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CompanyDto companyDto) {
        companyService.createService(companyDto);
        return new ResponseEntity<>("Company created: " + companyDto.getCompanyName(), HttpStatus.OK);
    }


    @GetMapping("/get-all")
    public ResponseEntity<String> getAll() {
        String allCompanies = companyService.getAllCompanies();
        return new ResponseEntity<>(allCompanies, HttpStatus.OK);
    }

    @GetMapping("/get-by-name/{companyName}")
    public ResponseEntity<CompanyDto> getByName(@PathVariable String companyName) {
        Company company = companyService.getCompany(companyName);
        CompanyDto structureDto = companyService.getCompanyDto(company);
        return new ResponseEntity<>(structureDto, HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<String> edit(@RequestBody CompanyDto companyDto) {
        companyService.update(companyDto);
        return new ResponseEntity<>("Edited company: "+ companyDto.getCompanyName(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{companyName}")
    public ResponseEntity<String> delete(@PathVariable String companyName ) {
        companyService.delete(companyName);
        return new ResponseEntity<>("Deleted company: " + companyName, HttpStatus.OK);
    }


}
