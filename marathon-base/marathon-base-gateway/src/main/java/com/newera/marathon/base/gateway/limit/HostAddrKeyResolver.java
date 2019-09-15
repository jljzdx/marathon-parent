package com.newera.marathon.base.gateway.limit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component("hostAddrKeyResolver")
@Slf4j
public class HostAddrKeyResolver implements KeyResolver {
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        String host = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        log.info("HostAddrKeyResolver host="+host);
        return Mono.just(host);
    }
}
