package com.example.dealsplus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    private String companyName;
    private String companyInfo;

    @Override
    public String toString() {
        return "companyName: " + companyName + ", companyInfo: " + companyInfo + ".";
    }

}
