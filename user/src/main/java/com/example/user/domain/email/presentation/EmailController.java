package com.example.user.domain.email.presentation;

import com.example.user.domain.email.presentation.dto.req.EmailVerificationInput;
import com.example.user.domain.email.presentation.dto.req.RequestEmailVerificationInput;
import com.example.user.domain.email.presentation.dto.res.EmailResponse;
import com.example.user.domain.email.service.EmailTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class EmailController {

    private final EmailTokenService emailTokenService;

    @MutationMapping
    public EmailResponse requestEmailVerification(
            @Argument(name = "requestEmailVerificationInput")
            RequestEmailVerificationInput requestEmailVerificationInput
    ) {
        return emailTokenService.sendVerificationEmail(requestEmailVerificationInput.email());
    }

    @QueryMapping
    public EmailResponse emailVerification(
            @Argument(name = "emailVerificationInput")
            EmailVerificationInput emailVerificationInput
    ) {
        return emailTokenService.verifyEmail(emailVerificationInput.token());
    }

}
