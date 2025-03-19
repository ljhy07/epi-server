package com.example.user.domain.user.domain.repository;

import com.example.user.domain.user.domain.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(@Email String email);

    boolean existsByEmail(@Email String email);
}
