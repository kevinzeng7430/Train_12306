package com.kz.common.controller;

import com.kz.common.response.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 同一拦截异常处理、数据预处理
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    /**
     * 处理所有异常
     * @param e 异常对象
     * @return 返回通用响应对象
     * @throws Exception 抛出异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResp exceptionHandler(Exception e) throws Exception{
        CommonResp commonResp = new CommonResp();
        log.error("异常信息：{}", e.getMessage());
        commonResp.setSuccess(false);
        commonResp.setMessage("系统异常，请稍后再试" + e.getMessage());
        return  commonResp;
    }

}
