package com.example.user.global.config.properties.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@NoArgsConstructor
@ConfigurationProperties("spring.auth.google")
public class GoogleProperties {

    private String baseUrl;
    private String clientId;
    private String secretId;
    private String redirectUrl;
}
