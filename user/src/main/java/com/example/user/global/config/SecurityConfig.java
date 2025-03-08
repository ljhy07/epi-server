package com.example.user.global.config;

import com.example.user.global.jwt.filter.JwtTokenFilter;
import com.example.user.global.exception.security.UserSecurityExceptionFilter;
import com.example.user.global.jwt.exception.CustomAccessDeniedException;
import com.example.user.global.jwt.exception.UnauthenticatedAccessException;
import com.example.user.global.jwt.util.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, ObjectMapper objectMapper) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.httpBasic(AbstractHttpConfigurer::disable);

        http.formLogin(AbstractHttpConfigurer::disable);

        http.logout(AbstractHttpConfigurer::disable);

        http.cors((cors) -> cors
                        .configurationSource(request -> {
                                    CorsConfiguration configuration = new CorsConfiguration();
                                    configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
                                    configuration.setAllowedMethods(Collections.singletonList("*"));
                                    configuration.setAllowCredentials(true);
                                    configuration.setAllowedHeaders(Collections.singletonList("*"));
                                    configuration.setMaxAge(3600L);
                                    configuration.setExposedHeaders(Arrays.asList("Bearer", "Authorization"));

                                    return configuration;
                        }));

        http
            .authorizeHttpRequests((auth) -> auth
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/favicon.ico", "/mails", "/verify", "/api/**").permitAll()
                .anyRequest().hasRole("USER"));

        http.exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(((request, response, e) -> {
                            throw new UnauthenticatedAccessException();
                        }))
                        .accessDeniedHandler((request, response, e) -> {
                            throw new CustomAccessDeniedException();
                        }));

        http.addFilterAfter(new UserSecurityExceptionFilter(objectMapper), CorsFilter.class);

        http.addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        http.addFilterBefore(new UserSecurityExceptionFilter(objectMapper), JwtTokenFilter.class);

        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
