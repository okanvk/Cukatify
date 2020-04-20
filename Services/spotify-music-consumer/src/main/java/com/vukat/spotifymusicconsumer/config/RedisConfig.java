package com.vukat.spotifymusicconsumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class RedisConfig {

    @Bean
    public Jedis getJedis(){
        return new Jedis("localhost");
    }

}
