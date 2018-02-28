package com.huawei.tdt.gateway.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {

    @Value("${app.product.session.timeout:30}")
    private int sessionTimeoutInMinuts;

    @Value("${app.product.session.redisnamespace:}")
    private String redisnamespace;

    @Value("${app.product.cookiename:DEFAULTSESSION}")
    private String cookieName;
    
    @Autowired
    private RedisOperationsSessionRepository sessionRepository;
    
    @Bean  
    public JedisConnectionFactory connectionFactory() {  
            return new JedisConnectionFactory();  
    }
    
    @Bean
    public CookieHttpSessionStrategy cookieHttpSessionStrategy(){
        CookieHttpSessionStrategy strategy=new CookieHttpSessionStrategy();
        DefaultCookieSerializer cookieSerializer=new DefaultCookieSerializer();
        cookieSerializer.setCookieName(cookieName);//cookies名称
        strategy.setCookieSerializer(cookieSerializer);
        return strategy;
    }
    
    @PostConstruct
    private void afterPropertiesSet() {
        sessionRepository.setDefaultMaxInactiveInterval(sessionTimeoutInMinuts * 60);
        sessionRepository.setRedisKeyNamespace(redisnamespace);
    }
}
