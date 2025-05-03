package com.example.user.application.user.domain.value;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public enum LoginType{
    NORMAL("LOGIN_NORMAL"),
    KAKAO("LOGIN_KAKAO"),
    NAVER("LOGIN_NAVER"),
    GOOGLE("LOGIN_GOOGLE"),
    APPLE("LOGIN_APPLE");

    private final String loginType;
}
