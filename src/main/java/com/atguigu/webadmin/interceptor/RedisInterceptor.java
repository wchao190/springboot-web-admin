package com.atguigu.webadmin.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RedisInterceptor implements HandlerInterceptor {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        stringRedisTemplate.opsForValue().increment(requestURI);

        return true; // 统计站点访问频率，永远放行
    }
}
