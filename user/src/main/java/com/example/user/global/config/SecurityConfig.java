package com.example.user.global.config;

import com.example.user.global.jwt.exception.CustomAccessDeniedException;
import com.example.user.global.jwt.exception.UnauthenticatedAccessException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
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
                                    configuration.setExposedHeaders(Arrays.asList("Set-Cookie", "Authorization"));

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

        

        return http.build();
    }
}
