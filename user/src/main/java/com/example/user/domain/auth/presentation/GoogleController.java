package com.example.user.domain.auth.presentation;

import com.example.user.domain.auth.presentation.dto.req.OAuthTokenLoginInput;
import com.example.user.domain.auth.presentation.dto.res.Token;
import com.example.user.domain.auth.service.implementation.google.GoogleAuthLinkService;
import com.example.user.domain.auth.service.implementation.google.GoogleAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/google")
@RestController
public class GoogleController {
    private final GoogleAuthLinkService googleLinkService;
    private final GoogleAuthService googleAuthService;

    @GetMapping
    public String getGoogleAuthLink() {
        return googleLinkService.execute();
    }

    @PostMapping
    public Token login(@RequestBody @Valid OAuthTokenLoginInput oauthTokenLoginInput) {
        return googleAuthService.execute(oauthTokenLoginInput);
    }
}
