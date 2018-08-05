package com.huawei.allinone.common.cache;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCacheManager implements ICacheManager {
    /**
     * 带泛型装配时不能使用@Autowired，否则会报错误
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void saveObject(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public Object getObject(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void deleteObject(String key) {
        redisTemplate.delete(key);
    }
}
