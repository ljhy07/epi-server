package com.apigateway.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class RewritePathFilter extends AbstractGatewayFilterFactory<RewritePathFilter.Config> {

    public RewritePathFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        // Custom Pre Filter

        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            String path = request.getURI().getPath();

            log.info("RewritePath PRE filter : request id -> {}", request.getId());

            ServerHttpRequest mutatedRequest = request.mutate()
                    .path("/graphql")
                    .build();

            return chain.filter(exchange.mutate().request(mutatedRequest).build()).then(Mono.fromRunnable(() -> {
                log.info("RewritePath POST filter : response code -> {}", response.getStatusCode());
            }));
        };

    }

    @Data
    public static class Config {
        String id;
        String uri;
        String predicatesPath;
    }
}
