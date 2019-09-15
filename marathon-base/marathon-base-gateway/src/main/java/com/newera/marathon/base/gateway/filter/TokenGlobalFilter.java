package com.newera.marathon.base.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class TokenGlobalFilter implements GlobalFilter, Ordered  {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("token global filter start ................");
        ServerHttpRequest request = exchange.getRequest();
        log.info("path="+request.getPath());
        log.info("uri="+request.getURI());
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst("token");
        log.info("token="+token);
        if(StringUtils.isBlank(token)){
            log.info("token is empty !");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        log.info("TokenGlobalFilter chain.filter start ................");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
