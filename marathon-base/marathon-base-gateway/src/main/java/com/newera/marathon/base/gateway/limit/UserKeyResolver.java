//package com.newera.marathon.base.gateway.limit;
//
//import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Component("userKeyResolver")
//public class UserKeyResolver implements KeyResolver {
//    @Override
//    public Mono<String> resolve(ServerWebExchange exchange) {
//        return Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
//    }
//}
