package com.example.user.domain.auth.presentation;

import com.example.user.domain.auth.presentation.dto.req.OAuthCodeLoginInput;
import com.example.user.domain.auth.presentation.dto.res.Token;
import com.example.user.domain.auth.service.implementation.kakao.KakaoAuthLinkService;
import com.example.user.domain.auth.service.implementation.kakao.KakaoAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/kakao")
@RestController
public class KakaoController {
    private final KakaoAuthLinkService kakaoAuthLinkService;
    private final KakaoAuthService kakaoAuthService;

    @GetMapping
    public String getKakaoAuthLink() {
        return kakaoAuthLinkService.execute();
    }

    @PostMapping
    public Token login(@RequestBody @Valid OAuthCodeLoginInput oauthCodeLoginInput) {
        return kakaoAuthService.execute(oauthCodeLoginInput);
    }
}
