package com.javakc.springbootcpims;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.sql.DataSource;

@SpringBootTest
class SpringbootCpimsApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    void contextLoads() {
//        System.out.println(" dataSource: "+dataSource);
//        System.out.println(" redisTemplate: "+redisTemplate);

        redisTemplate.boundHashOps("key2").put("name","value1");
    }

}
