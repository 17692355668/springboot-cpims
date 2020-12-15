package com.javakc.springbootcpims.common.datasource.mybatis;

import com.javakc.springbootcpims.common.redis.RedisComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @program:springboot-cpims
 * @description:建立MybatisCache与Ioc关联
 * @create:2020-10-29
 */

@Component
public class MybatisCacheTransfer {

    public MybatisCacheTransfer(@Autowired RedisTemplate<String,Object> redisTemplate) {
        MybatisCache.redisTemplate=redisTemplate;
    }
}
