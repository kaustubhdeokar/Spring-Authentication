package com.example.dealsplus.security;

import com.example.dealsplus.model.User;
import io.jsonwebtoken.Claims;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Builder
@Getter
@Setter
public class TokenData {

    private User user;
    private Claims claims;
    private boolean valid;
    private List<GrantedAuthority> authorities;

}
