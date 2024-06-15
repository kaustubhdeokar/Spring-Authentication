package com.example.dealsplus.repo;

import com.example.dealsplus.model.UserRoleMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepo extends JpaRepository<UserRoleMapping, Long> {



}
