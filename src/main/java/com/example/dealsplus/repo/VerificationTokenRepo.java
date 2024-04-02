package com.example.dealsplus.repo;


import com.example.dealsplus.model.User;
import com.example.dealsplus.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepo extends JpaRepository<VerificationToken, Long> {

    Optional<VerificationToken> findByToken(String token);

    VerificationToken[] findUserByToken(String token);

    Optional<VerificationToken> findByUser(User user);

}
