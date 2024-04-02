package com.example.dealsplus.service;

import com.example.dealsplus.dto.AuthenticationResponse;
import com.example.dealsplus.dto.RefreshTokenRequest;
import com.example.dealsplus.dto.RegisterUserDto;
import com.example.dealsplus.exception.CustomException;
import com.example.dealsplus.model.NotificationEmail;
import com.example.dealsplus.model.Role;
import com.example.dealsplus.model.User;
import com.example.dealsplus.model.VerificationToken;
import com.example.dealsplus.repo.RoleRepo;
import com.example.dealsplus.repo.UserRepo;
import com.example.dealsplus.repo.VerificationTokenRepo;
import com.example.dealsplus.security.JwtTokenProvider;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Getter
public class AuthService {

    @Autowired
    MailService mailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private JwtTokenProvider jwtProvider;
    @Autowired
    private VerificationTokenRepo tokenRepo;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RefreshTokenService refreshTokenService;

    private static int attempts = 0;
    private boolean bPasswordResetDone = true;

    public boolean signup(RegisterUserDto registerUserDto) throws DataIntegrityViolationException {

        checkIfUserAlreadyExists(registerUserDto);
        List<Role> roles = new ArrayList<>();
        RoleService roleService = new RoleService(roleRepo);
        if (registerUserDto.getUsername().equalsIgnoreCase("ADMIN")) {
            Role role = roleService.getRole("ADMIN");
            roles.add(role);
        } else {
            Role role = roleService.getRole("USER");
            roles.add(role);
        }
        User user = new User(registerUserDto.getUsername(),
                passwordEncoder.encode(registerUserDto.getPassword()),
                registerUserDto.getEmail(), roles);
        userRepo.save(user);
        logger.info("{}", "User saved.");

        String verificationToken = generateVerificationToken(user);
        mailService.sendEmail(new NotificationEmail("Account activation", user.getEmail(), formMailBody(verificationToken)));
        return true;
    }

    private void checkIfUserAlreadyExists(RegisterUserDto registerDto) {
        String username = registerDto.getUsername();
        if (userRepo.existsByUsername(username)) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                    String.format("Username %s exists in the database", username));
        }
        String email = registerDto.getEmail();
        if (userRepo.existsByEmail(email)) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                    String.format("Email %s exists in the database", email));
        }

    }

    private String formMailBody(String token) {
        return "Thank you for signing up, please click on the url to activate your account:\n" +
                "http://localhost:8080/api/auth/accountVerification/" + token;
    }

    private String formResetPasswordBody(String token) {
        return "Use following link to reset the password:\n" +
                "http://localhost:8080/api/auth/completeresetpassword/" + token;
    }

    private String generateVerificationToken(User user) {
        String generatedToken = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(generatedToken, user, VerificationToken.THREE_DAYS_FROM_TODAY);
        tokenRepo.save(verificationToken);
        logger.info("{}", "Token saved.");
        return generatedToken;
    }

    public VerificationToken verifyToken(String token) {
        Optional<VerificationToken> verificationToken = tokenRepo.findByToken(token);
        return verificationToken.orElseThrow(() -> new CustomException("Invalid token"));
    }

    public User getUserByToken(String token) {
        verifyToken(token);
        //todo: how to optimize this. (Returns an object..)
        VerificationToken[] tokenObject = tokenRepo.findUserByToken(token);
        return tokenObject[0].getUser();
    }

    public void enableUserHavingToken(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepo.findByUsername(username).orElseThrow(() -> new CustomException("User not present, invalid token"));
        user.setEnabled(true);
        userRepo.save(user);
    }

    public AuthenticationResponse loginUser(String username, String password) {

        if (bPasswordResetDone) {

            try {
                Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                String jwtToken = jwtProvider.generateToken(auth);
                String refreshToken = refreshTokenService.generateRefreshToken().getToken();
                Instant expiry = Instant.now().plusMillis(jwtProvider.getExpirationDate());
                return new AuthenticationResponse(username, jwtToken, refreshToken, expiry);
            } catch (AuthenticationServiceException e) {
                return new AuthenticationResponse(null, e.getMessage(), null, null);
            } catch (DisabledException e) {
                return new AuthenticationResponse(null, "User is disabled. Please verify the user.", null, null);
            } catch (BadCredentialsException th) {
                attempts += 1;
                if (attempts > 2) {
                    bPasswordResetDone = false;
                    throw new CustomException(HttpStatus.BAD_REQUEST,
                            String.format("Account is locked, please reset the password: ", username));
                }
                throw new CustomException(HttpStatus.BAD_REQUEST,
                        String.format("Incorrect credentials for username: ", username) + ". You have " + (3 - attempts) + " attempts remaining.");
            }

        } else {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                    String.format("Account is locked, please reset the password: ", username));
        }

    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateToken(refreshTokenRequest.getUsername());
        String refreshToken = refreshTokenRequest.getRefreshToken();
        Instant expiry = Instant.now().plusMillis(jwtProvider.getExpirationDate());
        return new AuthenticationResponse(refreshTokenRequest.getUsername(), token, refreshToken, expiry);
    }

    public User getUser(String username) {
        return userRepo.findByUsername(username).orElseThrow(() -> new CustomException("User not found with id -" + username));
    }


    public void getRolesForUser(String username) {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new CustomException("User not found with id -" + username));
        Optional<List<Object[]>> rolesByUserid = userRepo.findRolesByUserid(user.getUserid());
        for (Object role : rolesByUserid.get()) {
            System.out.println(role);
        }
    }

    public void resetPasswordForEmail(String email) {
        Optional<User> byEmail = userRepo.findByEmail(email);
        if (byEmail.isPresent()) {
            User user = byEmail.get();
            VerificationToken verificationToken = tokenRepo.findByUser(user).orElseThrow(() -> new CustomException("Not token found for user"));
            mailService.sendEmail(new NotificationEmail("Account activation", user.getEmail(), formResetPasswordBody(verificationToken.getToken())));
        }
    }

    public void setPasswordForUser(User userByToken, String newpassword) {
        userByToken.setPassword(passwordEncoder.encode(newpassword));
        userRepo.save(userByToken);
        bPasswordResetDone = true;
    }
}
