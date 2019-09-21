//package com.newera.marathon.base.gateway.filter;
//
//import io.netty.buffer.ByteBufAllocator;
//import lombok.extern.slf4j.Slf4j;
//import org.reactivestreams.Publisher;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.cloud.gateway.route.Route;
//import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.core.io.buffer.DataBufferFactory;
//import org.springframework.core.io.buffer.DataBufferUtils;
//import org.springframework.core.io.buffer.NettyDataBufferFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.net.URI;
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//
//@Slf4j
//@Component
//public class LogGlobalFilter implements GlobalFilter, Ordered {
//    private static final String REQUEST_PREFIX = "Request Info [ ";
//
//    private static final String REQUEST_TAIL = " ]";
//
//    private static final String RESPONSE_PREFIX = "Response Info [ ";
//
//    private static final String RESPONSE_TAIL = " ]";
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        Route gatewayUrl = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
//        URI uri = gatewayUrl.getUri();
//        String instance = uri.getAuthority();
//        String URIPath = request.getURI().toString();
//        String path = request.getPath().value();
//        String method = request.getMethodValue();
//        HttpHeaders headers = request.getHeaders();
//
//        //响应
//        ServerHttpResponse response = exchange.getResponse();
//        DataBufferFactory bufferFactory = response.bufferFactory();
//        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(response) {
//            @Override
//            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
//                if (body instanceof Flux) {
//                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
//                    return super.writeWith(fluxBody.map(dataBuffer -> {
//                        byte[] content = new byte[dataBuffer.readableByteCount()];
//                        dataBuffer.read(content);
//                        String responseResult = new String(content, Charset.forName("UTF-8"));
//                        log.info(RESPONSE_PREFIX);
//                        log.info("status="+this.getStatusCode());
//                        log.info("header="+this.getHeaders());
//                        log.info("responseResult="+responseResult);
//                        log.info(RESPONSE_TAIL);
//                        return bufferFactory.wrap(content);
//                    }));
//                }
//                return super.writeWith(body);
//            }
//        };
//        log.info(REQUEST_PREFIX);
//        log.info("URI="+uri);
//        log.info("URIPath="+URIPath);
//        log.info("path="+path);
//        log.info("instance="+instance);
//        log.info("method="+method);
//        log.info("headers="+headers);
//        log.info(REQUEST_TAIL);
//        if (HttpMethod.POST.equals(request.getMethod()) || request.getQueryParams().isEmpty()) {
//            return DataBufferUtils.join(request.getBody()).map(dataBuffer -> {
//                byte[] bytes = new byte[dataBuffer.readableByteCount()];
//                dataBuffer.read(bytes);
//                DataBufferUtils.release(dataBuffer);
//                return bytes;
//            }).flatMap(bodyBytes -> {
//                String requestBody = new String(bodyBytes, StandardCharsets.UTF_8);
//                log.info("请求体参数：{}"+requestBody);
//                //exchange.getAttributes().put("body", requestBody.replaceAll("[\t\n]", ""));
//                DataBuffer dataBuffer = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT).wrap(bodyBytes);
//                Flux<DataBuffer> flux = Flux.just(dataBuffer);
//                ServerHttpRequestDecorator decoratorRequest = new ServerHttpRequestDecorator(request) {
//                    @Override
//                    public Flux<DataBuffer> getBody() {
//                        return flux;
//                    }
//                };
//                return chain.filter(exchange.mutate().request(decoratorRequest).response(decoratedResponse).build());
//            });
//        }
//        //replace response with decorator
//        return chain.filter(exchange.mutate().request(request).response(decoratedResponse).build());
//    }
//
//    @Override
//    public int getOrder() {
//        return Ordered.HIGHEST_PRECEDENCE;
//    }
//}
