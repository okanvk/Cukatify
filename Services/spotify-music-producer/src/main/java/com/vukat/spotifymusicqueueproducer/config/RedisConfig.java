package com.vukat.spotifymusicqueueproducer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
class RedisConfig {


    @Bean
    public Jedis getJedis(){
        return new Jedis("localhost");
    }


}