package com.example.dealsplus.service;

import com.example.dealsplus.model.Role;
import com.example.dealsplus.model.RoleEntity;
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

    public Role getRole(String role) {

        Optional<Role> optionalRole = roleRepo.findByName(role);
        if(optionalRole.isEmpty()){
            insertRoleIfNotPresent(role);
        }
        else{
            return optionalRole.get();
        }
        return roleRepo.findByName(role).get();
    }

    public void insertRoleIfNotPresent(String name){

            Role role = new Role(name);
            roleRepo.save(role);

    }

//    public Role getRole(String roleName) {
//        return roleRepo.findByName(roleName);
//    }
}
