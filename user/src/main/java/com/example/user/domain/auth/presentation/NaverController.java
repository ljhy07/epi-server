package com.example.user.domain.auth.presentation;

import com.example.user.domain.auth.presentation.dto.req.OAuthCodeLoginInput;
import com.example.user.domain.auth.presentation.dto.res.Token;
import com.example.user.domain.auth.service.implementation.naver.NaverAuthLinkService;
import com.example.user.domain.auth.service.implementation.naver.NaverAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/naver")
@RestController
public class NaverController {
    private final NaverAuthLinkService naverAuthLinkService;
    private final NaverAuthService naverAuthService;

    @GetMapping
    public String getNaverAuthLink() {
        return naverAuthLinkService.execute();
    }

    @PostMapping
    public Token login(@RequestBody @Valid OAuthCodeLoginInput oauthCodeLoginInput) {
        return naverAuthService.execute(oauthCodeLoginInput);
    }
}
