//package com.kz.gateway.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Slf4j
//@Component
//public class TestFilter2 implements GlobalFilter, Ordered {
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        log.info("TestFilter2");
//        // 在这里可以添加自定义的过滤逻辑
//        // 例如：检查请求头、修改请求参数等
//
//        // 继续执行下一个过滤器或请求处理
//        return chain.filter(exchange);
//        // 如果需要直接结束请求，可以使用 exchange.getResponse().setComplete();
//    }
//
//    @Override
//    public int getOrder() {
//        return 2;
//    }
//}
