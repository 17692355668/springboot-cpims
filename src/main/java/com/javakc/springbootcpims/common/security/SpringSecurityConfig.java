package com.javakc.springbootcpims.common.security;

import com.javakc.springbootcpims.common.base.response.ResultCode;
import com.javakc.springbootcpims.common.base.response.ResultMessage;
import com.javakc.springbootcpims.common.security.filter.JWTAuthenticationFilter;
import com.javakc.springbootcpims.common.security.filter.JWTAuthentizationFilter;
import com.javakc.springbootcpims.common.security.jwt.JsonWebToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @program:springboot-cpims
 * @description:处理项目安全认证管理
 * @create:2020-11-01
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 密码加密管理
     *
     * @return BCryptPasswordEncoder
     */
    @Bean("passwordEncoder")
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }



    /**
     * 根据登录的用户名
     * 查询用户详情实现
     */
    @Autowired
    @Qualifier("securityUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private JsonWebToken jsonWebToken;

    /**
     * 自定义处理UserNotFoundExceptions
     * @return DaoAuthenticationProvider
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //不用隐藏用户找不到异常
        provider.setHideUserNotFoundExceptions(false);
        //根据登录账号查询用户信息
        provider.setUserDetailsService(userDetailsService);
        //如果用户存在则匹配密码
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider;
    }

    /**
     * 认证管理器
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        auth.inMemoryAuthentication().withUser("admin").password("123456");
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        auth.authenticationProvider(authenticationProvider());
    }

    /**
     * 请求认证配置
     *
     * @param http HTTP安全
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
         * 跨域（application）
         */
        http.cors();
        /*
         * 当前Spring Security使用JWT实现
         * 关闭Spring Security csrf(跨站点请求伪造)
         */
        http.csrf().disable();
        /*
         * spring security会话管理
         * SessionCreationPolicy.ALWAYS         一直创建HttpSession
         * SessionCreationPolicy.NEVER          Spring Security不会创建HttpSession，但如果它已经存在则使用HttpSession
         * SessionCreationPolicy.IF_REQUIRED    Spring Security只会在需要时创建一个HttpSession
         * SessionCreationPolicy.STATELESS      Spring Security永远不会创建HttpSession，它不会使用HttpSession来获取SecurityContext
         */
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        /*
         * Spring Security配置放行与拦截规则
         * 【系统管理】【用户注册】 /user 放行
         * swagger放行
         * druid放行
         * 【其余请求】 需要认证
         */
        http.authorizeRequests()
                //系统管理】【用户注册】 /user 放行
                .antMatchers(HttpMethod.POST, "/user").permitAll()
                //swagger放行
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                //druid放行
                .antMatchers("/druid/**").permitAll()
                //其他一律需要认证
                .anyRequest().authenticated();
        http
                .exceptionHandling()
                //重写用户未登录异常处理
                    .authenticationEntryPoint((request, response, exception) -> {
                    ResultMessage.response(response, ResultMessage.failure(ResultCode.USER_NOT_LOGIN));
                    })

                //重写拒绝访问异常处理
                    .accessDeniedHandler((request, response, exception) -> {
                      ResultMessage.response(response, ResultMessage.failure(ResultCode.PERMISSION_ACCESS_DENIED));
                    });
        http
                .logout()
                    .logoutSuccessHandler(((request, response, exception)->
                            {
                                //do something

                            }));

        /*
         * Spring Security配置过滤器
         * JWTAuthenticationFilter 登陆认证过滤器 JWTAuthenticationFilter(authenticationManager():认证管理器)
         * JWTAuthorizationFilter 权限授权过滤器
         */
        http
                .addFilter(new JWTAuthenticationFilter(authenticationManager(),jsonWebToken))
                .addFilter(new JWTAuthentizationFilter(authenticationManager(),jsonWebToken));


    }

}
