package com.atguigu.webadmin.config;

import com.atguigu.webadmin.bean.Student;
import com.atguigu.webadmin.interceptor.LoginInterceptor;
import com.atguigu.webadmin.interceptor.RedisInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Autowired
    RedisInterceptor redisInterceptor;

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginInterceptor())
                        .addPathPatterns("/**")
                        .excludePathPatterns("/","/login","/css/**","/fonts/**","/images/**","/js/**");
                registry.addInterceptor(redisInterceptor)
                        .addPathPatterns("/**")
                        .excludePathPatterns("/","/login","/css/**","/fonts/**","/images/**","/js/**");

            }
        };
    }

    @Bean
    @Profile("env")
    public Student getEnv(@Value("${st.name}")String name,@Value("${st.school}")String sh){
        return new Student(name,sh);
    }

    @Bean
    @Profile("test")
    public Student getTest(@Value("${st.name}")String name,@Value("${st.school}")String sh){
        return new Student(name,sh);
    }
}
