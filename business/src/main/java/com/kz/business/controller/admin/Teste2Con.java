package com.kz.business.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business")
public class Teste2Con {
    @GetMapping("/test")
    public String hello() {
        System.out.println("Hello from Business Test");
        return "Hello from Business Application!";
    }
}
