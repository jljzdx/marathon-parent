package com.newera.marathon.base.gateway.limit;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 用户限流：请求路径中必须携带userId参数
 */
@Component("userKeyResolver")
@Slf4j
public class UserKeyResolver implements KeyResolver {
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        String userId = exchange.getRequest().getHeaders().getFirst("userId");
        log.info("UserKeyResolver userId="+userId);
        if(StringUtils.isNotBlank(userId)){
            return Mono.just(userId);
        }
        return Mono.just("-1");
    }
}
