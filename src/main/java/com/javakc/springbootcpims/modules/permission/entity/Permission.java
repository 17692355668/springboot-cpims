package com.javakc.springbootcpims.modules.permission.entity;

import com.javakc.springbootcpims.common.base.entity.Base;
import lombok.Getter;
import lombok.Setter;

/**
 * @program:springboot-cpims
 * @description:RBAC权限模块实现类
 * @create:2020-10-31
 */
@Getter
@Setter
public class Permission extends Base {

    /** 权限名称 */
    private String name ;
    /** 权限编号 */
    private String permissionCode ;


}
