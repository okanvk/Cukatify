package com.vukat.spotifymusicconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vukat.spotifymusicconsumer.entity.Track;
import com.vukat.spotifymusicconsumer.service.TrackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.List;

@Component
@Slf4j
public class ScheduledConsumer {

    @Autowired
    Jedis jedis;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TrackService trackService;

    @Scheduled(fixedRate = 1)
    public void listenRedis() throws JsonProcessingException {

        try{
            List<String> data = jedis.blpop(0,"queue");

            String json = data.get(1);

            Track track = objectMapper.readValue(json, Track.class);

            track.initDate();

            trackService.addTrack(track);

        }catch (Exception e){
            log.error(e.getMessage());
        }
    }




}
