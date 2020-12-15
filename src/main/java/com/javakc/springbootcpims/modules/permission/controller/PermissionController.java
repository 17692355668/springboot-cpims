package com.javakc.springbootcpims.modules.permission.controller;

import com.javakc.springbootcpims.modules.permission.service.PermissService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program:springboot-cpims
 * @description:权限模块表现层实现类
 * @create:2020-11-01
 */
@RestController
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private PermissService permissService;

}
