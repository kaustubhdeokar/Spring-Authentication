package com.example.dealsplus.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDto {

    private String username;
    private String structureName;
    private String roleName;

}
