package com.example.dealsplus.service;

import com.example.dealsplus.exception.CustomException;
import com.example.dealsplus.model.Role;
import com.example.dealsplus.repo.RoleRepo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Getter
public class RoleService {

    final private RoleRepo roleRepo;

    @Autowired
    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public Role getRoleFromRepo(String role) {

        Optional<Role> optionalRole = roleRepo.findByName(role);
        if (optionalRole.isEmpty()) {
            throw new CustomException("Role does not exist");
        } else {
            return optionalRole.get();
        }
    }


}
