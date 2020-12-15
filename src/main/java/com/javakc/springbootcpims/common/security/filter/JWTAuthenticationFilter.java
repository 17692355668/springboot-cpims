package com.javakc.springbootcpims.common.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javakc.springbootcpims.common.base.response.ResultCode;
import com.javakc.springbootcpims.common.base.response.ResultMessage;
import com.javakc.springbootcpims.common.security.jwt.JsonWebToken;
import com.javakc.springbootcpims.modules.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @program:springboot-cpims
 * @description:登录授权过滤器
 * @create:2020-11-02
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JsonWebToken jsonWebToken;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,JsonWebToken jsonWebToken) {
        this.authenticationManager = authenticationManager;
        this.jsonWebToken=jsonWebToken;
    }

    /**
     * 收集客户端账号密码信息进行认证
     * @param request 请求
     * @param response 响应
     * @return 认证
     * @throws AuthenticationException 抛出异常
     */

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        ObjectMapper objectMapper=new ObjectMapper();

        try {
            User user=objectMapper.readValue(request.getInputStream(), User.class);

            //交由security完成认证
            /*
             *1.select * from table where name=？ and pass=?
             * 2.select pass from table where name=?
             */
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLoginName(), user.getLoginPass(), new ArrayList<>()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 登录成功的回调方法
     * @param request
     * @param response
     * @param chain
     * @param
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

        //1.生产token令牌
        //获取认证成功账号相关信息
        String username=((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername();

        //获取认证成功账号权限信息
        Claims claims = Jwts.claims();
        claims.put("authorities", authentication.getAuthorities().stream().map(s -> s.getAuthority()).collect(Collectors.toList()));

        String token=jsonWebToken.createToken(claims,username, jsonWebToken.getExpiration());
        String token1=jsonWebToken.createToken(claims,username, jsonWebToken.getExpiration()*24);
        ResultMessage.response(response,ResultMessage.failure(ResultCode.USER_LOGIN_SUCCESS,token));

    }

    /**
     * 登录失败的回调方法
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        if (failed instanceof UsernameNotFoundException)
        {
            ResultMessage.response(response,ResultMessage.failure(ResultCode.USER_NOT_EXIST));
        }
        else if (failed instanceof BadCredentialsException)
        {
            ResultMessage.response(response,ResultMessage.failure(ResultCode.USER_PASS_ERROR));
        }
        else if (failed instanceof LockedException)
        {
            ResultMessage.response(response,ResultMessage.failure(ResultCode.USER_ACCOUNT_LOCKED));
        }
        else
        {
            ResultMessage.response(response,ResultMessage.failure(ResultCode.USER_LOGIN_OTHER_ERROR));
        }
//        ResultMessage.response(response,ResultMessage.failure(ResultCode.USER_LOGIN_ERROR));
    }
}
