package com.vukat.spotifymusicconsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import redis.clients.jedis.Jedis;

import java.util.List;

@SpringBootApplication
@EnableScheduling
public class SpotifyMusicConsumerApplication {

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpotifyMusicConsumerApplication.class, args);
    }

}
