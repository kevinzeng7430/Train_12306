package com.kz.batch.controller;

import com.kz.batch.feign.BusinessFeign;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/test")
public class TestController {
    @Resource
    BusinessFeign businessFeign;
    @GetMapping("/hello")
    public String test() {
        businessFeign.hello();
        return "Hello BatchApplication";
    }
}
