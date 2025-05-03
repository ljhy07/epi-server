package com.example.user.application.auth.domain.repository;

import com.example.user.application.auth.domain.Refresh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshRepository extends JpaRepository<Refresh, Long> {
    void deleteByRefreshToken(String refreshToken);

    Refresh findByUserId(Long userId);

    Optional<Refresh> findByRefreshToken(String refreshToken);
}
