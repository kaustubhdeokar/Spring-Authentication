package com.example.dealsplus.controller;

import com.example.dealsplus.dto.LoginResponseDto;
import com.example.dealsplus.dto.LoginRequestDto;
import com.example.dealsplus.dto.RefreshTokenRequestDto;
import com.example.dealsplus.dto.RegisterUserDto;
import com.example.dealsplus.model.User;
import com.example.dealsplus.model.VerificationToken;
import com.example.dealsplus.service.AuthService;
import com.example.dealsplus.service.RefreshTokenService;
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

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterUserDto registerUserDto) {
        try {
            service.signup(registerUserDto);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Duplicate username/email.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Registration done.", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginRequestDto registerUserDTO) {
        return new ResponseEntity<>(service.loginUser(registerUserDTO.getUsername(), registerUserDTO.getPassword()), HttpStatus.OK);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        VerificationToken verificationToken = service.verifyToken(token);
        service.enableUserHavingToken(verificationToken);
        return new ResponseEntity<>("account verified.", HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequestDto.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body("Logged out.");
    }

    @PostMapping("/refresh/token")
    public ResponseEntity<LoginResponseDto> refreshToken(@Valid @RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        LoginResponseDto loginResponseDto = service.refreshToken(refreshTokenRequestDto);
        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }


    @PostMapping("/resetpassword/{email}")
    //for reset password -
    public ResponseEntity<String> initiateResetPassword(@PathVariable String email) {
        service.resetPasswordForEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body("Please check your mail for reset password instructions.");
    }

    @PostMapping("/completeresetpassword/{token}/{newpassword}")
    public void completeResetPassword(@PathVariable String token, @PathVariable String newpassword) {
        User userByToken = service.getUserByToken(token);
        service.setPasswordForUser(userByToken, newpassword);
    }


}
