package com.example.user.global.jwt.filter;

import com.example.user.global.jwt.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);  // "Bearer " 제거

            try {
                Authentication authentication = jwtUtils.authorization(token);  // 인증 객체 생성
                SecurityContextHolder.getContext().setAuthentication(authentication);  // SecurityContext에 인증 설정
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 인증 실패 시 401 상태 코드 반환
            }
        }

        filterChain.doFilter(request, response);  // 필터 체인 계속 진행
    }
}
