package com.newera.marathon.base.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class ForwardController {
    /**
     * 当路由找不到时，返回友好提示
     * @return
     */
    @PostMapping("/not/found/route")
    public Mono<Map<String,String>> notFoundRoute(){
        log.info("not found route ..................");
        Map<String,String> map = new HashMap<>();
        map.put("code","-404");
        map.put("data","route definition not found");
        return Mono.just(map);
    }
    /**
     * 当服务调用失败时，返回友好提示
     * @return
     */
    @PostMapping("/hystrix/fallback")
    public Mono<Map<String,String>> hystrixFallback(){
        log.info("service not available ..................");
        Map<String,String> map = new HashMap<>();
        map.put("code","-100");
        map.put("data","service not available");
        return Mono.just(map);
    }
}
