package com.example.user.application.email.service;

import com.example.user.application.email.domain.Email;
import com.example.user.application.email.domain.repository.EmailRepository;
import com.example.user.application.email.exception.EmailTokenAlreadyVerifiedException;
import com.example.user.application.email.exception.EmailTokenNotFoundException;
import com.example.user.application.email.exception.ExpiredEmailTokenException;
import com.example.user.application.email.exception.value.EmailVerificationStatus;
import com.example.user.application.email.presentation.dto.res.EmailResponse;
import com.example.user.application.user.domain.repository.UserRepository;
import com.example.user.application.user.exception.UserExistedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmailTokenService {
    private final EmailRepository emailRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public EmailResponse sendVerificationEmail(String userEmail) {
        if (userRepository.existsByEmail(userEmail)) {
            throw new UserExistedException();
        }

        emailRepository.deleteByEmail(userEmail);

        Email email = Email.builder()
                .email(userEmail)
                .build();

        emailRepository.save(email);
        emailService.sendMail(email, email.getToken());

        return new EmailResponse(
                EmailVerificationStatus.SUCCESS,
                Boolean.TRUE
        );
    }

    public EmailResponse verifyEmail(String token) {
        Email email = emailRepository.findByToken(token);
        if (email == null) {
            throw new EmailTokenNotFoundException();
        }
        if (email.isExpired()) {
            throw new ExpiredEmailTokenException();
        }
        if (email.isVerified()) {
            throw new EmailTokenAlreadyVerifiedException();
        }

        email.verify();

        return new EmailResponse(
                EmailVerificationStatus.SUCCESS,
                Boolean.TRUE
        );
    }

    public boolean isEmailVerified(String userEmail) {
        Email email = emailRepository.findByEmailAndIsVerified(userEmail, true);
        return email != null;
    }

}
