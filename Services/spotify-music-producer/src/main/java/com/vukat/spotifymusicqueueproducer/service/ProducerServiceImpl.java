package com.vukat.spotifymusicqueueproducer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vukat.spotifymusicqueueproducer.model.Track;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
@Slf4j
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    Jedis jedis;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void enqueuTrack(Track track) throws JsonProcessingException {

        String json = objectMapper.writeValueAsString(track);

        jedis.rpush("queue",json);

        log.info("Data enqueued to queue");

    }
}
