package com.javakc.springbootcpims.modules.dictionary.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @program:springboot-cpims
 * @description:缓存预热监听器
 * @create:2020-10-30
 */

@WebListener
public class InitDictionaryListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {


    }
}
