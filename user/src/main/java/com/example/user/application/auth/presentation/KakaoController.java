package com.example.user.application.auth.presentation;

import com.example.user.application.auth.presentation.dto.req.OAuthCodeLoginInput;
import com.example.user.application.auth.presentation.dto.res.Token;
import com.example.user.application.auth.service.implementation.kakao.KakaoAuthLinkService;
import com.example.user.application.auth.service.implementation.kakao.KakaoAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class KakaoController {
    private final KakaoAuthLinkService kakaoAuthLinkService;
    private final KakaoAuthService kakaoAuthService;

    @QueryMapping
    public String getKakaoAuthLink() {
        return kakaoAuthLinkService.execute();
    }

    @MutationMapping
    public Token oauthKakaoLogin(@Argument(name = "oauthLoginInput") OAuthCodeLoginInput oauthLoginInput) {
        return kakaoAuthService.execute(oauthLoginInput);
    }
}
