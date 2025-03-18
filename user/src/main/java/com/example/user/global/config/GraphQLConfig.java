package com.example.user.global.config;

import com.example.user.global.graphql.handler.GraphqlHandlerMapping;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {
    @Bean
    public GraphqlHandlerMapping graphqlHandlerMapping(ApplicationContext applicationContext) {
        return new GraphqlHandlerMapping(applicationContext);
    }
}
