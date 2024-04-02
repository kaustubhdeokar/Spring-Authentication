package com.example.dealsplus.controller;

import com.example.dealsplus.dto.AuthenticationResponse;
import com.example.dealsplus.dto.LoginRequestDto;
import com.example.dealsplus.dto.RefreshTokenRequest;
import com.example.dealsplus.dto.RegisterUserDto;
import com.example.dealsplus.model.User;
import com.example.dealsplus.model.VerificationToken;
import com.example.dealsplus.service.AuthService;
import com.example.dealsplus.service.RefreshTokenService;
import com.example.dealsplus.service.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService service;
    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterUserDto registerUserDto) {
        try {
            service.signup(registerUserDto);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Duplicate username/email.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Registration done. Check mail to verify signup.", HttpStatus.OK);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        VerificationToken verificationToken = service.verifyToken(token);
        service.enableUserHavingToken(verificationToken);
        return new ResponseEntity<>("account verified.", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody LoginRequestDto registerUserDTO) {
        return new ResponseEntity<>(service.loginUser(registerUserDTO.getUsername(), registerUserDTO.getPassword()), HttpStatus.OK);
    }

    @PostMapping("/refresh/token")
    public ResponseEntity<AuthenticationResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        AuthenticationResponse authenticationResponse = service.refreshToken(refreshTokenRequest);
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body("Logged out.");
    }

    @GetMapping("/get-roles/{username}")
    public ResponseEntity<String> getRolesForUser(@PathVariable String username) {
        service.getRolesForUser(username);
        return ResponseEntity.status(HttpStatus.OK).body("Roles queried.");
    }

    @PostMapping("/resetpassword/{email}")
    //for reset password -
    public ResponseEntity<String> initiateResetPassword(@PathVariable String email){
        service.resetPasswordForEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body("Please check your mail for reset password instructions.");
    }

    @PostMapping("/completeresetpassword/{token}/{newpassword}")
    public void completeResetPassword(@PathVariable String token, @PathVariable String newpassword){
        User userByToken = service.getUserByToken(token);
        service.setPasswordForUser(userByToken, newpassword);
    }


}
