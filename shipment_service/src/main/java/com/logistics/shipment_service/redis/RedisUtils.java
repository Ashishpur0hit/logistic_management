package com.logistics.shipment_service.redis;

import com.logistics.common.RedisDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class RedisUtils {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;


    public Object getRedis(String key)
    {
        return  redisTemplate.opsForValue().get(key);
    }

    public void deleteRedis(String key) {
        redisTemplate.delete(key);
    }



}
