package com.example.user.domain.auth.service.implementation.kakao;

import com.example.user.domain.auth.presentation.dto.req.OAuthCodeLoginInput;
import com.example.user.domain.auth.presentation.dto.res.Token;
import com.example.user.domain.user.domain.User;
import com.example.user.domain.user.domain.value.LoginType;
import com.example.user.domain.user.domain.value.Role;
import com.example.user.domain.user.presentation.dto.res.UserResponse;
import com.example.user.domain.user.service.CommandUserService;
import com.example.user.domain.user.service.QueryUserService;
import com.example.user.global.config.properties.AuthProperties;
import com.example.user.global.feign.auth.kakao.KakaoAuthClient;
import com.example.user.global.feign.auth.kakao.KakaoInformationClient;
import com.example.user.global.feign.auth.kakao.dto.res.KakaoAuthResponse;
import com.example.user.global.jwt.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class KakaoAuthService {

    private final AuthProperties authProperties;
    private final KakaoAuthClient kakaoAuthClient;
    private final KakaoInformationClient kakaoInformationClient;
    private final CommandUserService commandUserService;
    private final QueryUserService queryUserService;
    private final JwtUtils jwtUtils;

    @Transactional
    public Token execute(OAuthCodeLoginInput codeRequest) {
        KakaoAuthResponse accessToken = kakaoAuthClient.getAccessToken(
                authProperties.getKakaoClientId(),
                authProperties.getKakaoRedirectUrl(),
                codeRequest.code());

        Map<String, Object> response = kakaoInformationClient.getUserInformation("Bearer " + accessToken.getAccess_token());

        Map<String, Object> kakao_account = (Map<String, Object>) response.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile");

        String nickname = (String) profile.get("nickname");
//        String profile_img = (String) profile.get("profile_image_url");
        String email = (String) kakao_account.get("email");

        UserResponse user = queryUserService.getUserByEmail(email);

        if (user == null) {
            commandUserService.saveOAuth(
                    User.oauthUserCreateBuilder()
                            .name(nickname)
                            .email(email)
                            .loginType(LoginType.GOOGLE)
                            .role(Role.User)
                            .build()
            );
        }

        return new Token(
                jwtUtils.getRefreshToken(email),
                jwtUtils.getAccessToken(email)
        );
    }
}
