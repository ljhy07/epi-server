package com.example.user.domain.auth.presentation;

import com.example.user.domain.auth.presentation.dto.req.OAuthTokenLoginInput;
import com.example.user.domain.auth.presentation.dto.res.Token;
import com.example.user.domain.auth.service.implementation.google.GoogleAuthLinkService;
import com.example.user.domain.auth.service.implementation.google.GoogleAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class GoogleController {
    private final GoogleAuthLinkService googleLinkService;
    private final GoogleAuthService googleAuthService;

    @QueryMapping
    public String getGoogleAuthLink() {
        return googleLinkService.execute();
    }

    @MutationMapping
    public Token oauthGoogleLogin(@Argument OAuthTokenLoginInput oauthLoginInput) {
        return googleAuthService.execute(oauthLoginInput);
    }
}
