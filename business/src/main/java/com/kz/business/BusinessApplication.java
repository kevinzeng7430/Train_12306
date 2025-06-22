package com.kz.business;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
@Slf4j
public class BusinessApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BusinessApplication.class);
        Environment env = app.run(args).getEnvironment();
        log.info("Business Application started successfully!");
        log.info("地址:\thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }
}
