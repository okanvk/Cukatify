package com.vukat.spotifymusicqueueproducer.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.vukat.spotifymusicqueueproducer.model.Track;

public interface ProducerService {

    void enqueuTrack(Track track) throws JsonProcessingException;

}
