package com.kinghk.util;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

public class RedissonUtil {

    public static RedissonClient config(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://192.168.0.178:6379")
                .setPassword("sinldo.com")
                .setDatabase(0);
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
