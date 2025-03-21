package com.example.user.domain.email.domain.repository;

import com.example.user.domain.email.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    void deleteByExpiryDateBefore(LocalDateTime expiryDateBefore);

    void deleteByEmail(String email);

    Email findByEmailAndIsVerified(String email, Boolean b);

    Email findByToken(String token);
}
