package com.javakc.springbootcpims.common.datasource.driud;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: springboot-cpims
 * @description: druid数据源监控
 * @author: zhou hong gang
 * @create: 2020-10-20 10:19
 */
@Configuration
public class DruidConfig {

    /**
     * ServletRegistrationBean 注册servlet
     * @return ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
        //初始化登陆参数
        Map<String,String> initParams = new HashMap<>();
        //访问账号
        initParams.put("loginUsername", "admin");
        //访问密码
        initParams.put("loginPassword", "123456");
        /*
         * allow: ip白名单
         *    initParas.put("allow",""); 这个值为空或没有就允许所有人访问，ip白名单
         *    initParas.put("allow","localhost");  只允许本机访问，多个ip用逗号,隔开
         * deny: ip黑名单
         *    initParas.put("deny","192.168.1.109"); 拒绝192.168.1.109访问
         *
         * 如果deny和allow同时存在优先deny
         */
        initParams.put("allow", "");
        //禁用HTML页面的Reset按钮
        initParams.put("resetEnable", "false");
        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * FilterRegistrationBean 注册filter
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter(){
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
        //设置阿里巴巴的过滤器
        bean.setFilter(new WebStatFilter());
        bean.addUrlPatterns("/*");

        //把不需要监控的过滤掉,这些不进行统计
        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        return bean;
    }

}
