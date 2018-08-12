package com.huawei.allinone.common.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.BoundHashOperations;
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
        if (null == redisTemplate.opsForList().index(key, 0)) {
            redisTemplate.opsForList().leftPush(key, values);
        } else {
            // 当Key不存在时会报错
            redisTemplate.opsForList().set(key, 0, values);
        }
    }

    @Override
    public List<?> getList(String key) {
        Object obj = redisTemplate.opsForList().index(key, 0);
        if (null != obj) {
            if (obj instanceof List) {
                return (List<?>)obj;
            }
        }

        return new ArrayList<>(0);
    }

    @Override
    public void pushList(String key, List<?> values) {
        redisTemplate.opsForList().leftPushAll(key, values);
    }

    @Override
    public List<?> popList(String key) {
        Object obj = redisTemplate.opsForList().leftPop(key);
        if (null != obj) {
            if (obj instanceof List) {
                return (List<?>)obj;
            }
        }

        return new ArrayList<>(0);
    }

    @Override
    public void saveMap(String key, Map<?, ?> values) {
        redisTemplate.opsForHash().putAll(key, values);
    }

    @Override
    public Map<?, ?> getMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public Object getMapValue(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void saveMapWithTimeout(String key, Map<?, ?> values) {
        BoundHashOperations operation = redisTemplate.boundHashOps(key);
        operation.putAll(values);
        /**
         * 设置超时时间必须放在放置值之后
         */
        operation.expire(10, TimeUnit.SECONDS);
    }

    @Override
    public Object getBoundMapValue(String key, String hashKey) {
        return redisTemplate.boundHashOps(key).get(hashKey);
    }
}
