package com.javakc.springbootcpims;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: springboot-cpims
 * @description: 项目启动入口
 * @author: zhou hong gang
 * @create: 2020-10-20 10:01
 */
@SpringBootApplication
@MapperScan("com.javakc.springbootcpims.modules.**.dao")
public class SpringbootCpimsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCpimsApplication.class, args);
    }

}
