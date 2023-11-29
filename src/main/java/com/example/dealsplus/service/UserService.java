package com.example.dealsplus.service;

import com.example.dealsplus.exception.CustomException;
import com.example.dealsplus.model.Role;
import com.example.dealsplus.model.User;
import com.example.dealsplus.repo.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    public final RoleService roleService;
    public final UserDetailsServiceImpl userDetailsService;
    private final PermissionService permissionService;
    private final UserRepo userRepo;

    public UserService(RoleService roleService, UserDetailsServiceImpl userDetailsService, PermissionService permissionService, UserRepo userRepo) {
        this.roleService = roleService;
        this.userDetailsService = userDetailsService;
        this.permissionService = permissionService;
        this.userRepo = userRepo;
    }

    public void checkForAuthorization(String... applicablePermissions) {
        if (checkIfAdmin()) {
            return; // authorized.
        }

        if (Arrays.stream(applicablePermissions).anyMatch(permission -> permissionService.getPermissionFromUser().contains(permission))) {
            return; // authorized.
        }
        throw new CustomException("Authorization fail");
    }

    public boolean checkIfAdmin() {
        Set<SimpleGrantedAuthority> rolesForUser = getRolesForUser(getNameForCurrentUser());
        return rolesForUser.contains(new SimpleGrantedAuthority("ADMIN"));
    }

    public Set<SimpleGrantedAuthority> getRolesForUser(String username) {
        UserDetails userDetails = getCurrentUserDetails(username);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthority())).collect(Collectors.toSet());
    }

    private UserDetails getCurrentUserDetails(String username) {
        return userDetailsService.loadUserByUsername(username);
    }

    public static String getNameForCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return principal.getUsername();
    }

    public User getUserFromRepo(String username) {
        Optional<User> optionalUser = userRepo.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new CustomException("User not present.");
        }
        return optionalUser.get();
    }

    public void addRoleForUser(String role, String user) {
        User userFromRepo = getUserFromRepo(user);
        Role roleFromRepo = roleService.getRoleFromRepo(role);
        if (!userFromRepo.getRoles().contains(roleFromRepo)) {
            userFromRepo.getRoles().add(roleFromRepo);
            userRepo.save(userFromRepo);
        } else {
            System.out.println("Role already assigned to user.");
        }
    }

}
