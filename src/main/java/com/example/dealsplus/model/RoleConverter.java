package com.example.dealsplus.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<RoleEntity, String> {

    @Override
    public String convertToDatabaseColumn(RoleEntity roleEntity) {
        if (roleEntity == null) return null;
        return roleEntity.getRole();
    }


    @Override
    public RoleEntity convertToEntityAttribute(String code) {
        if (code == null)
            return null;
        return Stream.of(RoleEntity.values())
                .filter(roleEntity -> roleEntity.getRole().equals(code))
                .findFirst().orElseThrow(IllegalAccessError::new);

    }
}
