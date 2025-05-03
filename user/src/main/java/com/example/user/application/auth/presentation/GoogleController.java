package com.example.user.application.auth.presentation;

import com.example.user.application.auth.presentation.dto.req.OAuthTokenLoginInput;
import com.example.user.application.auth.presentation.dto.res.Token;
import com.example.user.application.auth.service.implementation.google.GoogleAuthLinkService;
import com.example.user.application.auth.service.implementation.google.GoogleAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class GoogleController {
    private final GoogleAuthLinkService googleLinkService;
    private final GoogleAuthService googleAuthService;

    @QueryMapping
    public String getGoogleAuthLink() {
        return googleLinkService.execute();
    }

    @MutationMapping
    public Token oauthGoogleLogin(@Argument(name = "oauthLoginInput") OAuthTokenLoginInput oauthLoginInput) {
        return googleAuthService.execute(oauthLoginInput);
    }
}
