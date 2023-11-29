package com.example.dealsplus.service;

import com.example.dealsplus.dto.LoginResponseDto;
import com.example.dealsplus.dto.RefreshTokenRequestDto;
import com.example.dealsplus.dto.RegisterUserDto;
import com.example.dealsplus.exception.CustomException;
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
    private JwtTokenProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleService roleService;

    public AuthService(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.roleService = roleService;
    }

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private VerificationTokenRepo tokenRepo;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RefreshTokenService refreshTokenService;

    private static int attempts = 0;
    private boolean bPasswordResetDone = true;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    public boolean signup(RegisterUserDto registerUserDto) throws DataIntegrityViolationException {

        if (ifUserExists(registerUserDto)) {
            throw new CustomException("User already exists");
        }

        Role basicUserRole = roleService.getRoleFromRepo("USER");

        User user = new User(registerUserDto.getUsername(),
                passwordEncoder.encode(registerUserDto.getPassword()),
                registerUserDto.getEmail());
        user.setRoles(List.of(basicUserRole));

        userRepo.save(user);

        return true;
    }

    private boolean ifUserExists(RegisterUserDto registerUserDto) {
        Optional<User> userByName = userRepo.findByUsername(registerUserDto.getUsername());
        if (userByName.isEmpty()) {
            Optional<User> userByEmail = userRepo.findByEmail(registerUserDto.getEmail());
            return !userByEmail.isEmpty();
        }
        return true;
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

    public LoginResponseDto loginUser(String username, String password) {

        User user = userRepo.findByUsername(username).orElseThrow(() -> new CustomException("Invalid username"));
        if (user.isEnabled()) {
            try {
                Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                String jwtToken = jwtProvider.generateToken(auth);
                String refreshToken = refreshTokenService.generateRefreshToken().getToken();
                Instant expiry = Instant.now().plusMillis(JwtTokenProvider.JWT_EXPIRATION);
                return new LoginResponseDto(username, jwtToken, refreshToken, expiry);
            } catch (AuthenticationServiceException e) {
                return new LoginResponseDto(null, e.getMessage(), null, null);
            } catch (DisabledException e) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "User is disabled. Please verify the user.: " + username);
            } catch (BadCredentialsException th) {
//                handleIncorrectCredentials(username, user);
                return new LoginResponseDto(null, th.getMessage(), null, null);
            }
        } else {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                    "Account is locked: " + username);
        }

    }

    private void handleIncorrectCredentials(String username, User principalUser) {
//        attempts += 1;
//        if (attempts > 2) {
//            bPasswordResetDone = false;
//            principalUser.setEnabled(false);
//            throw new CustomException(HttpStatus.BAD_REQUEST, "Account is locked, please reset the password for user : " + username);
//        }
//        throw new CustomException(HttpStatus.BAD_REQUEST,
//                "Incorrect credentials for username: " + username + ". You have " + (3 - attempts) + " attempts remaining.");
    }

    public LoginResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        refreshTokenService.validateRefreshToken(refreshTokenRequestDto.getRefreshToken());
        String token = "jwtProvider.generateToken(refreshTokenRequest.getUsername())";
        String refreshToken = refreshTokenRequestDto.getRefreshToken();
        Instant expiry = Instant.now().plusMillis(JwtTokenProvider.JWT_EXPIRATION);
        return new LoginResponseDto(refreshTokenRequestDto.getUsername(), token, refreshToken, expiry);
    }

    public User getUser(String username) {
        return userRepo.findByUsername(username).orElseThrow(() -> new CustomException("User not found with id -" + username));
    }


    public void resetPasswordForEmail(String email) {
//        Optional<User> byEmail = userRepo.findByEmail(email);
//        if (byEmail.isPresent()) {
//            User user = byEmail.get();
//            VerificationToken verificationToken = tokenRepo.findByUser(user).orElseThrow(() -> new CustomException("Not token found for user"));
//            mailService.sendEmail(new NotificationEmail("Account activation", user.getEmail(), formResetPasswordBody(verificationToken.getToken())));
//        }
        //todo: to implement.
    }

    public void setPasswordForUser(User userByToken, String newpassword) {
        userByToken.setPassword(passwordEncoder.encode(newpassword));
        userByToken.setEnabled(true);
        userRepo.save(userByToken);
    }
}
