package com.huawei.tdt.common.authorization.manager.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.huawei.tdt.common.authorization.manager.TokenManager;
import com.huawei.tdt.common.authorization.model.Token;

/**
 * 通过Redis存储和验证token的实现类
 */
@Component
@Primary
public class RedisTokenManager implements TokenManager {

    private RedisTemplate<String, String> redis;

    @Value("${tdt.tokenExpireTime}")
    private long tokenExpireTime;

    @Autowired
    public void setRedis(RedisTemplate<String, String> redis) {
        this.redis = redis;
        // 泛型设置成Long后必须更改对应的序列化方案
        // redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    public Token createToken(String userId) {
        // 使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        Token model = new Token(userId, token);
        // 存储到redis并设置过期时间
        redis.boundValueOps(getTokenRedisKey(userId)).set(token, tokenExpireTime, TimeUnit.SECONDS);
        return model;
    }

    public Token getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
        // 使用userId和源token简单拼接成的token，可以增加加密措施
        String userId = param[0];
        String token = param[1];
        return new Token(userId, token);
    }

    public boolean checkToken(Token model) {
        if (model == null) {
            return false;
        }
        String token = redis.boundValueOps(model.getUserId()).get();
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
        // 如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redis.boundValueOps(getTokenRedisKey(model.getUserId())).expire(tokenExpireTime, TimeUnit.SECONDS);
        return true;
    }

    public void deleteToken(String userId) {
        redis.delete(userId);
    }

    private String getTokenRedisKey(String userId) {
        return "tdt:user:" + userId + ":token";
    }
}
