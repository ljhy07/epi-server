package com.example.user.application.email.service.scheduler;

import com.example.user.application.email.domain.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@Transactional
@RequiredArgsConstructor
public class EmailScheduler {

    private final EmailRepository emailRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void cleanupExpiredTokens() {
        emailRepository.deleteByExpiryDateBefore(LocalDateTime.now());
    }
}
