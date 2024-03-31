package com.example.dealsplus.service;

import com.example.dealsplus.model.Role;
import com.example.dealsplus.repo.RoleRepo;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Getter
public class RoleService {

    private RoleRepo roleRepo;
    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
        if(roleRepo.findByName("USER")==null) {
            roleRepo.save(new Role("USER", List.of()));
        }
        if(roleRepo.findByName("ADMIN")==null) {
            roleRepo.save(new Role("ADMIN", List.of()));
        }
    }

    public Role getRole(String roleName) {
        return roleRepo.findByName(roleName);
    }
}
