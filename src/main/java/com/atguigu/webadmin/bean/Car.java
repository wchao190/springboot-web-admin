package com.atguigu.webadmin.bean;

import org.springframework.stereotype.Component;

@Component
public interface Car {
    String getCarName();
    String getModel();
}
