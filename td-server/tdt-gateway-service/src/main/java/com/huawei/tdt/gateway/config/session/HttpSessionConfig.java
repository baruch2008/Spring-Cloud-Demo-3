package com.huawei.tdt.gateway.config.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * Http会话配置.
 */
//@Configuration
//@EnableRedisHttpSession
public class HttpSessionConfig
{
    /**
     * 规避“魔数”.
     */
    private static final int SECONDS_PER_MINUTE = 60;

    /**
     * 会话过期时间（分钟）.
     */
    @Value("${app.product.session.timeout:30}")
    private int sessionTimeoutInMinuts;

    /**
     * 会话在redis中的key.
     */
    @Value("${app.product.session.redisnamespace:}")
    private String redisnamespace;

    /**
     * 会话在cookie中的名称.
     */
    @Value("${app.product.cookiename:DEFAULTSESSION}")
    private String cookieName;

    /**
     * （估计是）会话的连接池.
     */
    @Autowired
    private RedisOperationsSessionRepository sessionRepository;

    /**
     * 创建/获取redis连接工厂.
     *
     * @return redis连接工厂
     */
    @Bean
    public JedisConnectionFactory connectionFactory()
    {
        return new JedisConnectionFactory();
    }

    /**
     * 创建/获取cookie会话策略.
     *
     * @return cookie会话策略
     */
    //@Bean
    public CookieHttpSessionStrategy cookieHttpSessionStrategy()
    {
        CookieHttpSessionStrategy strategy = new CookieHttpSessionStrategy();
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        // cookies名称
        cookieSerializer.setCookieName(cookieName);
        strategy.setCookieSerializer(cookieSerializer);
        return strategy;
    }

    /**
     * 给会话设置完属性的后续处理.
     */
    //@PostConstruct
    public void afterPropertiesSet()
    {
        sessionRepository.setDefaultMaxInactiveInterval(sessionTimeoutInMinuts * SECONDS_PER_MINUTE);
        sessionRepository.setRedisKeyNamespace(redisnamespace);
    }
}
