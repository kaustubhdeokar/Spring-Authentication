package com.example.dealsplus.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "company")
@Getter
@Setter
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long companyId;
    private String name;
    private String companyInfo;

    public Company() {
    }

    public Company(String companyName, String companyInfo) {
        this.name = companyName;
        this.companyInfo = companyInfo;
    }
}
