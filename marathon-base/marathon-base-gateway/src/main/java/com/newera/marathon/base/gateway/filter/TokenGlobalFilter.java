package com.newera.marathon.base.gateway.filter;

import com.spaking.boot.starter.core.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TokenGlobalFilter implements GlobalFilter, Ordered {
    private final RouteLocator routeLocator;
    @Value("${spring.profiles.active}")
    private String profiles;
    @Autowired
    public TokenGlobalFilter(RouteLocator routeLocator){
        this.routeLocator = routeLocator;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("TokenGlobalFilter start ................");
        ServerHttpRequest request = exchange.getRequest();
        List<String> routeIds = new ArrayList<>();
        routeLocator.getRoutes().subscribe(w->{
            routeIds.add(w.getId());
        });
        //log.info("path=" + request.getPath());///swagger-ui.html
        //log.info("uri=" + request.getURI());//http://localhost:8100/swagger-ui.html
        String path = request.getPath().toString();
        log.info("profiles >>>>>>>>>"+profiles);
        log.info("path >>>>>>>>>"+path);
        //如果请求路径包含路由id，并且请求路径不等于路由+/v2/api-docs（如：/cms/v2/api-docs），并且不是dev环境，才需要判断token
        Long count = routeIds.stream().filter(w->{
            return path.contains(w)
                    && !("/"+w+"/v2/api-docs").equalsIgnoreCase(path)
                    // TODO: 2019-09-29  
                    //&& !"dev".equalsIgnoreCase(profiles)
                    && !"cos".equalsIgnoreCase(w);
        }).count();
        if (count == 1) {
            HttpHeaders headers = request.getHeaders();
            String token = headers.getFirst("token");
            log.info("token >>>>>>>>>" + token);
            if (StringUtils.isBlank(token) || StringUtils.isBlank(JWTUtil.getUserId(token))) {
                log.info("token is empty !");
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
