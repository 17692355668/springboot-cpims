package com.javakc.springbootcpims.common.security.filter;

import com.javakc.springbootcpims.common.security.jwt.JsonWebToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program:springboot-cpims
 * @description:权限授权过滤器
 * @create:2020-11-03
 */
public class JWTAuthentizationFilter extends BasicAuthenticationFilter {

    private JsonWebToken jsonWebToken;

    public JWTAuthentizationFilter(AuthenticationManager authenticationManager, JsonWebToken jsonWebToken) {

        super(authenticationManager);
        this.jsonWebToken=jsonWebToken;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //1.从请求中获取token
        String token=jsonWebToken.getToken(request);
        //1.1如果无法从请求中获取token则说明未登录
        if (StringUtils.isEmpty(token) )
        {
            //执行放行
            chain.doFilter(request, response);
            return;
        }
        //1.2验证token是否过期
        //请求头中有token则进行解析并且设置认证信息
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(token, request, response));

        super.doFilterInternal(request, response, chain);
   }

    /**
     * 账号密码认证token
     * @param token 请求携带token
     * @return 账号密码认证token
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token, HttpServletRequest request, HttpServletResponse response) {
        token = token.replace(jsonWebToken.getPrefix(), "");
        String username = jsonWebToken.getUsername(token);
        if (!StringUtils.isEmpty(username)){
            return new UsernamePasswordAuthenticationToken(username, null, jsonWebToken.getAuthenticationFromToken(request, response).getAuthorities());
        }
        return null;
    }

}
