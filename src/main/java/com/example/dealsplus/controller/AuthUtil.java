package com.example.dealsplus.controller;

import com.example.dealsplus.model.Role;
import com.example.dealsplus.model.User;
import com.example.dealsplus.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthUtil {

    @Autowired private UserRepo userRepo;



}
