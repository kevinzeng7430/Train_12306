package com.kz.common.intercepter;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Date;

@Component
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String logId = DateUtil.format(new Date(), "yyyyMMddHHmmssSSS") + RandomUtil.randomNumbers(3);
        MDC.put("logId", logId);
        return true;
    }
    // 增加日志流水号
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.clear();
    }
}
