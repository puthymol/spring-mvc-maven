package com.softvider.test;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class TestRedis {

    @Test
    public void testRedis(){

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(100);
        config.setMaxTotal(100);
        config.setTestOnBorrow(false);
        config.setTestOnReturn(false);

        String redisHost = "18.138.81.206";
        int redisPort = 16379;
        String auth = "1234";
        JedisPool pool = new JedisPool(config, redisHost, redisPort, 3000, auth);
        pool.getResource().set("puthy", "12001");
        pool.getResource().expire("puthy", 30);
        System.out.println("Connected to Redis");
    }
}
