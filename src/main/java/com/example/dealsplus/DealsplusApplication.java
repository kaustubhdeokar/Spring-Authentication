package com.example.dealsplus;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DealsplusApplication extends SpringBootServletInitializer implements CommandLineRunner {

//    private final PasswordEncoder passwordEncoder;
//    private final UserRepo userRepo;
//    public DealsplusApplication(PasswordEncoder passwordEncoder, UserRepo userRepo) {
//        this.passwordEncoder = passwordEncoder;
//        this.userRepo = userRepo;
//    }

    public static void main(String[] args) {
        SpringApplication.run(DealsplusApplication.class, args);
    }


    public void run(String... args) throws Exception {


    }
}
