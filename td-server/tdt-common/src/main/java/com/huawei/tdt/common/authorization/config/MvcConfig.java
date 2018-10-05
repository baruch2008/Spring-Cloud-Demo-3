package com.huawei.tdt.common.authorization.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.huawei.tdt.common.authorization.intercepter.AuthorizationInterceptor;

/**
 * 配置类，增加自定义拦截器和解析器
 */
//@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor);
    }

}
