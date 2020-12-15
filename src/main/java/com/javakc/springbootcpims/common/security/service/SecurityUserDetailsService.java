package com.javakc.springbootcpims.common.security.service;

import com.javakc.springbootcpims.modules.user.dao.UserDao;
import com.javakc.springbootcpims.modules.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program:springboot-cpims
 * @description:执行登录的查询相关功能
 * @create:2020-11-02
 */
@Service("securityUserDetailsService")
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userDao.loadUserByUsername(username);

        if(StringUtils.isEmpty(user))
        {
            throw new UsernameNotFoundException("该用户不存在");
        }
        else if(1 == user.getUserState())
        {
            throw new DisabledException("账号已禁用");
        }
        else if(2 == user.getUserState())
        {
            throw new LockedException("账号已锁定");
        }
        //查询用户权限+
        List<GrantedAuthority> authorities = new ArrayList<>();
        //封装用户权限
        userDao.loadPermissionByUsername(username).forEach(authority -> {
            authorities.add(new SimpleGrantedAuthority(authority));
        });
        return new org.springframework.security.core.userdetails.User(username, user.getLoginPass(), authorities);
    }
}
