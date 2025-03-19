package com.example.user.domain.auth.service.implementation.naver;

import com.example.user.domain.auth.presentation.dto.req.OAuthCodeLoginInput;
import com.example.user.domain.auth.presentation.dto.res.Token;
import com.example.user.domain.user.domain.User;
import com.example.user.domain.user.domain.value.LoginType;
import com.example.user.domain.user.domain.value.Role;
import com.example.user.domain.user.presentation.dto.res.UserResponse;
import com.example.user.domain.user.service.CommandUserService;
import com.example.user.domain.user.service.QueryUserService;
import com.example.user.global.config.properties.auth.NaverProperties;
import com.example.user.global.feign.auth.naver.NaverAuthClient;
import com.example.user.global.feign.auth.naver.NaverInformationClient;
import com.example.user.global.feign.auth.naver.dto.res.NaverAuthResponse;
import com.example.user.global.jwt.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class NaverAuthService {

    private final NaverProperties naverProperties;
    private final NaverAuthClient naverAuthClient;
    private final NaverInformationClient naverInformationClient;
    private final CommandUserService commandUserService;
    private final QueryUserService queryUserService;
    private final JwtUtils jwtUtils;

    @Transactional
    public Token execute(OAuthCodeLoginInput codeRequest) {
        NaverAuthResponse accessToken = naverAuthClient.getAccessToken(
                naverProperties.getClientId(),
                naverProperties.getSecretId(),
                naverProperties.getRedirectUrl(),
                codeRequest.code()
        );

        Map<String, Object> response = naverInformationClient.getUserInformation("Bearer " + accessToken.getAccess_token());
        Map<String, Object> info = (Map<String, Object>) response.get("response");

        String nickname = (String) info.get("nickname");
//        String profile_img = (String) info.get("profile_image");
        String email = (String) info.get("email");

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

        return jwtUtils.getToken(email);
    }
}
