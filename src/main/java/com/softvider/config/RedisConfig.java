package com.softvider.config;

import com.softvider.controller.HomeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class RedisConfig {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    private static JedisPool redisPool;

    static {
        Properties config = new Properties();
        try {
            log.debug("Loading classpath:META-INF/application.properties");
            config.load(new ClassPathResource("/META-INF/application.properties").getInputStream());

            JedisPoolConfig redisConfig = new JedisPoolConfig();
            redisConfig.setMaxIdle(100);
            redisConfig.setMaxTotal(100);
            redisConfig.setTestOnBorrow(false);
            redisConfig.setTestOnReturn(false);

            String redisHost = config.getProperty("softvider.redis.host");
            int redisPort = Integer.parseInt(config.getProperty("softvider.redis.port"));
            String auth = config.getProperty("softvider.redis.password");
            redisPool = new JedisPool(redisConfig, redisHost, redisPort, 3000, auth);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public JedisPool getRedisPool(){
        return redisPool;
    }

}
