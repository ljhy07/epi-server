package com.example.user.application.auth.service.implementation.kakao;

import com.example.user.application.auth.presentation.dto.req.OAuthCodeLoginInput;
import com.example.user.application.auth.presentation.dto.res.Token;
import com.example.user.application.user.domain.User;
import com.example.user.application.user.domain.value.LoginType;
import com.example.user.application.user.domain.value.Role;
import com.example.user.application.user.presentation.dto.res.UserResponse;
import com.example.user.application.user.service.MutationUserService;
import com.example.user.application.user.service.QueryUserService;
import com.example.user.global.config.properties.auth.KakaoProperties;
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

    private final KakaoProperties kakaoProperties;
    private final KakaoAuthClient kakaoAuthClient;
    private final KakaoInformationClient kakaoInformationClient;
    private final MutationUserService mutationUserService;
    private final QueryUserService queryUserService;
    private final JwtUtils jwtUtils;

    @Transactional
    public Token execute(OAuthCodeLoginInput codeRequest) {
        KakaoAuthResponse accessToken = kakaoAuthClient.getAccessToken(
                kakaoProperties.getClientId(),
                kakaoProperties.getRedirectUrl(),
                codeRequest.code());

        Map<String, Object> response = kakaoInformationClient.getUserInformation("Bearer " + accessToken.getAccess_token());

        Map<String, Object> kakao_account = (Map<String, Object>) response.get("kakao_account");
//        Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile");

//        String profile_img = (String) profile.get("profile_image_url");
        String email = (String) kakao_account.get("email");

        UserResponse user = queryUserService.getUserByEmail(email);

        if (user == null) {
            mutationUserService.saveOAuth(
                    User.oauthUserCreateBuilder()
                            .email(email)
                            .loginType(LoginType.GOOGLE)
                            .role(Role.User)
                            .build()
            );
        }

        return jwtUtils.getToken(email);
    }
}
