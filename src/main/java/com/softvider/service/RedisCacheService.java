package com.softvider.service;

import com.softvider.config.RedisConfig;

public class RedisCacheService {

    RedisConfig redisConfig = new RedisConfig();

    public String Home(String key, String value){
        redisConfig.getRedisPool().getResource().set(key, value);
        redisConfig.getRedisPool().getResource().expire(key, 30);

        return "{"+key+","+value+"}";
    }
}
