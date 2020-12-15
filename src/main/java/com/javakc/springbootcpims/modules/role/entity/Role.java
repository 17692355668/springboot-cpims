package com.javakc.springbootcpims.modules.role.entity;

import com.javakc.springbootcpims.common.base.entity.Base;
import lombok.Getter;
import lombok.Setter;

/**
 * @program:springboot-cpims
 * @description:RBAC角色模块实现类
 * @create:2020-10-31
 */
@Getter
@Setter
public class Role extends Base {

    /** 角色名称 */
    private String name ;
    /** 角色编码 */
    private String roleCode ;
    /** 角色备注 */
    private String roleReason ;



}
