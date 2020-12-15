package com.javakc.springbootcpims.common.datasource.mybatis;

import com.javakc.springbootcpims.common.redis.RedisComponent;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program:springboot-cpims
 * @description:Mybatis二级缓存自定义封装实现类
 * @create:2020-10-29
 */
public class MybatisCache implements Cache {

    private String id;
    public static RedisTemplate<String,Object> redisTemplate;
    private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();


    public MybatisCache(String id) {
        this.id = id;
    }


    @Override
    public String getId() {
        return id;
    }


    /**
     * 向Redis中写入查询数据
     * @param o sql
     * @return result
     */
    @Override
    public void putObject(Object o, Object o1) {
        Lock lock=readWriteLock.writeLock();
        lock.lock();
        redisTemplate.boundHashOps(id).put(o.toString(),o1);
        lock.unlock();
    }





    /**
     * 从Redis读取数据
     * @param o sql
     * @return return
     */
    @Override
    public Object getObject(Object o) {
        Object object=redisTemplate.boundHashOps(id).get(o.toString());
        return object;
    }


    /**
     * 从Redis中删除数据
     * @param o sql
     * @return true/false
     */
    @Override
    public Object removeObject(Object o) {
        return null;
    }


    @Override
    public void clear() {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}

