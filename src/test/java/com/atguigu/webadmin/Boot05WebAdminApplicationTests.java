package com.atguigu.webadmin;

import com.sun.xml.internal.ws.resources.XmlmessageMessages;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;

@SpringBootTest
@Slf4j
class Boot05WebAdminApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        Long aLong = jdbcTemplate.queryForObject("select count(*) from tbl_employee", Long.class);
        log.info("共{}条",aLong);
    }

    @Test
    void redisTest(){
        ValueOperations<String, String> stringValueOperations = redisTemplate.opsForValue();
        stringValueOperations.set("test","springboot");

    }
    @Test
    @DisplayName("断言测试")
    void assertTest(){
        if( 2!=2){
            Assertions.fail("测试失败");
        }

    }
}
