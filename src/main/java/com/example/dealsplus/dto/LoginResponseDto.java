package com.example.dealsplus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private String username;
    private String authenticationToken;
    private String refreshToken;
    private Instant expiresAt;

}
