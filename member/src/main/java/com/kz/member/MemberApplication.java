package com.kz.member;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;




@SpringBootApplication
@ComponentScan("com.kz") // 扫描com.kz包及其子包下的所有组件
@Slf4j
@MapperScan("com.kz.member.mapper") // 扫描Mapper接口
public class MemberApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MemberApplication.class);
        Environment env = app.run(args).getEnvironment();
        log.info("启动成功!");
        log.info("地址:\thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }
}