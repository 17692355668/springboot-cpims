package com.javakc.springbootcpims.modules.user.dao;

import com.javakc.springbootcpims.common.base.dao.BaseDao;
import com.javakc.springbootcpims.modules.user.entity.User;

import java.util.List;

public interface UserDao extends BaseDao<User,Integer> {
    User loadUserByUsername(String username);
    List<String> loadPermissionByUsername(String username);
}
