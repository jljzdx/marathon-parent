//package com.newera.marathon.base.gateway.limit;
//
//import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
///**
// * 接口限流：获取请求地址的uri作为限流key
// */
//@Component("uriKeyResolver")
//public class UriKeyResolver implements KeyResolver {
//    @Override
//    public Mono<String> resolve(ServerWebExchange exchange) {
//        return Mono.just(exchange.getRequest().getPath().value());
//    }
//}
