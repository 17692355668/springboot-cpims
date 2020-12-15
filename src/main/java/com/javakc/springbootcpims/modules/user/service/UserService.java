package com.javakc.springbootcpims.modules.user.service;

import com.javakc.springbootcpims.common.base.service.BaseService;
import com.javakc.springbootcpims.modules.user.dao.UserDao;
import com.javakc.springbootcpims.modules.user.entity.User;
import org.springframework.stereotype.Service;

/**
 * @program:springboot-cpims
 * @description:用户模块逻辑层实现类
 * @create:2020-11-01
 */
@Service
public class UserService extends BaseService<UserDao, User> {

}
