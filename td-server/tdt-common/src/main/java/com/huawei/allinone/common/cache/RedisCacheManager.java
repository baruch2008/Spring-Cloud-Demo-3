package com.huawei.allinone.common.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void saveList(String key, List<?> values) {
        redisTemplate.opsForList().leftPushAll(key, values);
    }

    @Override
    public List<?> getList(String key) {
        Object obj = redisTemplate.opsForList().leftPop(key);
        if (null != obj) {
            if (obj instanceof List) {
                return (List<?>)obj;
            }
        }

        return new ArrayList<>(0);
    }

    @Override
    public void saveMap(String key, Map<?, ?> value) {
        redisTemplate.opsForHash().putAll(key, value);
    }

    @Override
    public Map<?, ?> getMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public Object getMapValue(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }
}
