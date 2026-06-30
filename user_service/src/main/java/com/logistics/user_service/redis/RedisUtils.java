package com.logistics.user_service.redis;

import com.logistics.common.RedisDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class RedisUtils {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;


    public RedisDriver saveRedis(String key ,RedisDriver redisDriver)
    {
         redisTemplate.opsForValue().set(key,redisDriver);
         return redisDriver;
    }
}
