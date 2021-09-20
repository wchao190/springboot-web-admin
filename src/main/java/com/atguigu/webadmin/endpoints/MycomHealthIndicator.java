package com.atguigu.webadmin.endpoints;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class MycomHealthIndicator extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        HashMap<String,Object> map = new HashMap<>();
        if(1==1){
            builder.status(Status.UP); //健康 放行;
            map.put("count",1);
            map.put("ms",100);
        }else{
            builder.status(Status.OUT_OF_SERVICE);
            map.put("error","链接超时");
            map.put("ms",50000);
        }
        builder.withDetail("code",100).withDetails(map);
    }
}
