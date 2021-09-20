package com.atguigu.webadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@MapperScan("com.atguigu.webadmin.dao")
public class Boot05WebAdminApplication {

    public static void main(String[] args) {

        SpringApplication.run(Boot05WebAdminApplication.class, args);
    }
}
