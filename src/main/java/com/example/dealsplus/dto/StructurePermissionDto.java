package com.example.dealsplus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StructurePermissionDto {

    private String structureName;

    private String permission;

    private String username;
}
