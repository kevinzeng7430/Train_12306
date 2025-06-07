package com.kz.common.controller;

import com.kz.common.Exception.BusinessException;
import com.kz.common.response.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 同一拦截异常处理、数据预处理
 * @ ControllerAdvice：全局异常处理注解，作用于所有 @Controller，可统一处理异常、数据绑定等。
 * @ ExceptionHandler(Exception.class)：指定处理哪类异常，这里表示捕获所有 Exception 异常。
 * @ ResponseBody：返回值会自动序列化为 JSON 或 XML，直接写入 HTTP 响应体。
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    /**
     * 处理所有异常
     * @param e 异常对象
     * @return 返回通用响应对象
     *
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResp<Object> exceptionHandler(Exception e){
        CommonResp<Object> commonResp = new CommonResp<>();
        log.error("异常信息：{}", e.getMessage(), e);
        commonResp.setSuccess(false);
        commonResp.setMessage("系统异常" + e.getMessage());
        return commonResp;
    }

    /**
     * 业务异常
     * @param e 异常对象
     * @return 返回通用响应对象
     *
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public CommonResp<Object> exceptionHandler(BusinessException e){
        CommonResp<Object> commonResp = new CommonResp<>();
        log.error("业务异常:{}", e.getMessage(), e);
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getE().getDesc());
        return commonResp;
    }



}
