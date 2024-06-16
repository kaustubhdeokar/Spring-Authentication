package com.example.dealsplus.service;

import com.example.dealsplus.dto.CompanyDto;
import com.example.dealsplus.exception.CustomException;
import com.example.dealsplus.model.Company;
import com.example.dealsplus.repo.CompanyRepo;
import com.example.dealsplus.utils.ConstantUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyRepo companyRepo;
    private final UserService userService;

    public CompanyService(CompanyRepo companyRepo, UserService userService) {
        this.companyRepo = companyRepo;
        this.userService = userService;
    }

    public void createService(CompanyDto companyDto) {
        userService.checkForAuthorization(ConstantUtils.createPermissionForEntity.apply(ConstantUtils.COMPANY_ENTITY , ConstantUtils.CREATE_PERM_STRING));
        Company company = new Company(companyDto.getCompanyName(), companyDto.getCompanyInfo());
        companyRepo.save(company);
    }

    public Company getCompany(String companyName) {
        Optional<Company> optionalCompany = companyRepo.findByName(companyName);
        if (optionalCompany.isEmpty()) {
            throw new CustomException("Company not present");
        }
        return optionalCompany.get();
    }

    public String getAllCompanies() {
        List<Company> allComps = companyRepo.findAll();
        return allComps.stream().map(Company::getName).collect(Collectors.joining(","));
    }

    public CompanyDto getCompanyDto(Company company) {
        return new CompanyDto(company.getName(), company.getCompanyInfo());
    }

    public void update(CompanyDto companyDto) {
        Company company = getCompany(companyDto.getCompanyName());
        company.setCompanyInfo(company.getCompanyInfo());
        companyRepo.save(company);
    }

    public void delete(String companyName) {
        Company company = getCompany(companyName);
        companyRepo.delete(company);
    }
}
