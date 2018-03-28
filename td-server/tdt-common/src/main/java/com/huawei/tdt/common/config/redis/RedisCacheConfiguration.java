package com.huawei.tdt.common.config.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * RedisCacheConfiguration.java
 * @author lWX537094
 */
@Configuration
@EnableCaching
public class RedisCacheConfiguration extends CachingConfigurerSupport
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheConfiguration.class);

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.pool.max-wait}")
    private long maxWaitMillis;

    @Value("${spring.redis.pool.min-idle}")
    private int minIdle;

    @Value("${spring.redis.password}")
    private String password;

    /**
     * 实例化JedisPool
     * @return JedisPool
     */
    @Bean
    public JedisPool redisPoolFactory()
    {
        LOGGER.info("redis:" + host + ":" + port);

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setMinIdle(minIdle);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);

        return jedisPool;
    }
}
