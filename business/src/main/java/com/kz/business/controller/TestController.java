package com.kz.business.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        System.out.println("Hello from Business Application!");

        return "Hello from Business Application!";
    }
}
