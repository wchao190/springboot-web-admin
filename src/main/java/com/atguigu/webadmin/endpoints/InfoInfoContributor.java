package com.atguigu.webadmin.endpoints;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class InfoInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("infoEx", Collections.singletonMap("key","value"));
    }
}
