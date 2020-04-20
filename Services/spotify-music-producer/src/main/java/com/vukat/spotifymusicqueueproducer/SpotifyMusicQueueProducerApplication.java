package com.vukat.spotifymusicqueueproducer;

import com.vukat.spotifymusicqueueproducer.exception.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpotifyMusicQueueProducerApplication {

    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    public RestTemplate getRestTemplate() {
        return this.builder.errorHandler(new RestTemplateResponseErrorHandler()).build();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpotifyMusicQueueProducerApplication.class, args);
    }

}
