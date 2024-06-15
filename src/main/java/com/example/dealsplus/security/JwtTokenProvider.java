package com.example.dealsplus.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.function.Supplier;

@Service
@Getter
public class JwtTokenProvider {

    private static String jwtSecret = "c17be0b65f0824272ace47e24bc0503275024e1d7b63763f4c8ab79a53ee2ce3";
    private static SecretKey getSecretKey() {
        byte[] decodedKey = Base64.getDecoder().decode(jwtSecret);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");
    }
    public static final long JWT_EXPIRATION = 432_000_000;
    private static final Key key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret));
    public String generateToken(Authentication authentication) {
        return generateToken(authentication.getName());
    }

    public String generateToken(String name) {
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + JWT_EXPIRATION);

        String token = Jwts.builder()
                .subject(name)
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(getSecretKey())
                .compact();

        System.out.println("New token :"+ token);
        return token;
    }


    public String getUsernameFromJWTToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    public boolean validateJwtToken(HttpServletResponse response, String token) throws IOException {
        try {
            Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception ex) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return true;
    }

}