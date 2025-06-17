package com.kz.gateway.config;

import com.kz.gateway.Util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoginMemberFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().value();
        log.info("LoginMemberFilter: {}", path);
        if(path.contains("/admin")
                || path.contains("/hello")
                || path.contains("/member/member/login")
                || path.contains("/member/member/send-code")){
            log.info("不需要验证: {}", path);
            return chain.filter(exchange);
        }else{
            log.info("需要验证: {}", path);
        }

        //获取token校验
        String token = exchange.getRequest().getHeaders().getFirst("token");
        log.info("开始会员验证: {}", token);
        if(token == null || token.isEmpty()){
            log.info("token为空，拒绝访问: {}", token);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        //校验token是否有效
        boolean isValid = JwtUtil.validate(token);
        if(isValid){
            log.info("token验证通过: {}", token);
            return chain.filter(exchange);
        }else{
            log.info("token验证失败: {}", token);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
