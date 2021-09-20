package com.atguigu.webadmin.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test")
@Component
@ConfigurationProperties(prefix = "car")
@Data
public class SaicCar implements Car {
    private String carName;
    private String model;
}
