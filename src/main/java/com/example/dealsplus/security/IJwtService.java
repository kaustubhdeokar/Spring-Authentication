package com.example.dealsplus.security;

import com.example.dealsplus.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.function.Function;
import java.util.Optional;

public interface IJwtService {
    String createToken(User user, Function<Token, String> tokenFunc);
    Optional<String> extractToken(HttpServletRequest request, String tokenType);
    void addCookie(HttpServletResponse response, User user, TokenType type);

    <T> T getTokenData(String token, Function<TokenData, T> tokenFunc);
}
