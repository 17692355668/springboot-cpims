package com.javakc.springbootcpims.modules.role.service;

import com.javakc.springbootcpims.common.base.service.BaseService;
import com.javakc.springbootcpims.modules.role.dao.RoleDao;
import com.javakc.springbootcpims.modules.role.entity.Role;
import org.springframework.stereotype.Service;

/**
 * @program:springboot-cpims
 * @description:权限模块逻辑层实现类
 * @create:2020-11-01
 */
@Service
public class RoleService extends BaseService<RoleDao, Role> {

}
