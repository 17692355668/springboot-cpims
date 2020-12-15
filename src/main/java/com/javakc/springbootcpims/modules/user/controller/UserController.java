package com.javakc.springbootcpims.modules.user.controller;

import com.javakc.springbootcpims.modules.user.entity.User;
import com.javakc.springbootcpims.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program:springboot-cpims
 * @description:用户模块表现层实现类
 * @create:2020-11-01
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;


    @PostMapping
    public void insert(@RequestBody User entity)
    {

        //取出明文密码
        String loginPass=entity.getLoginPass();
        //加密明文密码
        String newPass=passwordEncoder.encode(loginPass);
        //写入加密密码
        entity.setLoginPass(newPass);
        userService.insert(entity);
    }

}
