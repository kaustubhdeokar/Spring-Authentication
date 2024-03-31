package com.example.dealsplus.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StructureDto {

    private String structureName;

    private String privateData;

    @Override
    public String toString() {
        return "structureName='" + structureName + '\'' +
                ", privateData='" + privateData + '\'';
    }
}
