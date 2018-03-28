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
import com.huawei.tdt.common.util.CommonUtil;

/**
 * 通过Redis存储和验证token的实现类.
 */
@Component
@Primary
public class RedisTokenManager implements TokenManager
{
    /**
     * redis模板.
     */
    private RedisTemplate<String, String> redis;

    /**
     * token过期时间，单位是秒.
     */
    @Value("${tdt.tokenExpireTime}")
    private long tokenExpireTime;

    /**
     * 设置redis模板.
     *
     * @param redisTemplate redis模板
     */
    @Autowired
    public void setRedis(RedisTemplate<String, String> redisTemplate)
    {
        this.redis = redisTemplate;
        // 泛型设置成Long后必须更改对应的序列化方案
        // redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    /**
     * 创建token.
     *
     * @param userId 指定用户的id
     * @return token对象
     */
    public Token createToken(String userId)
    {
        // 使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        Token model = new Token(userId, token);
        // 存储到redis并设置过期时间
        redis.boundValueOps(getTokenRedisKey(userId)).set(token, tokenExpireTime, TimeUnit.SECONDS);
        return model;
    }

    /**
     * 获取token.
     *
     * @param authentication 加密后的字符串
     * @return token
     */
    public Token getToken(String authentication)
    {
        return CommonUtil.getToken(authentication);
    }

    /**
     * 检查token.
     *
     * @param model token
     * @return 检查是否通过
     */
    public boolean checkToken(Token model)
    {
        if (model == null)
        {
            return false;
        }
        String token = redis.boundValueOps(getTokenRedisKey(model.getUserId())).get();
        if (token == null || !token.equals(model.getToken()))
        {
            return false;
        }
        // 如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redis.boundValueOps(getTokenRedisKey(model.getUserId())).expire(tokenExpireTime, TimeUnit.SECONDS);
        return true;
    }

    /**
     * 删除用户的token.
     *
     * @param userId 登录用户的id
     */
    public void deleteToken(String userId)
    {
        redis.delete(getTokenRedisKey(userId));
    }

    /**
     * 获取token在redis中的key.
     *
     * @param userId 用户id
     * @return token在redis中的key
     */
    private String getTokenRedisKey(String userId)
    {
        return "tdt:user:" + userId + ":token";
    }
}
