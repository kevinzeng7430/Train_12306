package com.kz.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class TestFilter1 implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("TestFilter1");
        return chain.filter(exchange);// 继续执行下一个过滤器或请求处理
        //return exchange.getResponse().setComplete(); 这是让这个请求直接结束，不再往下走
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
