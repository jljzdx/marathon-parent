package com.newera.marathon.base.gateway.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class TestGatewayFilterFactory extends AbstractGatewayFilterFactory<TestGatewayFilterFactory.TestConfig> {
    private static final String CODE = "code";
    private static final String NAME = "name";
    private static final String VALUE = "value";
    public TestGatewayFilterFactory(){
        super(TestConfig.class);
    }
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(CODE,NAME,VALUE);
    }
    @Override
    public GatewayFilter apply(TestConfig config) {
        return (exchange, chain) -> {
            log.info("TestGatewayFilterFactory start ..................");
            //这里写业务逻辑
            return chain.filter(exchange).then(
                    Mono.fromRunnable(() -> {
                        //调用成功后
                        log.info("code："+config.getCode());
                        log.info("name："+config.getName());
                        log.info("value："+config.getValue());
                    })
            );
        };
    }

    public static class TestConfig {

        private Integer code;

        private String name;

        private String value;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
