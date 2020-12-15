package com.javakc.springbootcpims.modules.user.entity;

import com.javakc.springbootcpims.common.base.entity.Base;
import lombok.Getter;
import lombok.Setter;

/**
 * @program:springboot-cpims
 * @description:RBAC用户模块实现类
 * @create:2020-10-31
 */
@Getter
@Setter
public class User extends Base {

    /** 姓名 */
    private String name ;
    /** 用户编号 */
    private String userNumber ;
    /** 用户状态 */
    private Integer userState ;
    /** 登录名称 */
    private String loginName ;
    /** 登录密码 */
    private String loginPass ;
    /** 手机号码 */
    private String phone ;
    /** 电子邮箱 */
    private String email ;
   


}
