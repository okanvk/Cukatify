package com.vukat.spotifymusicconsumer.service;

import com.vukat.spotifymusicconsumer.entity.Track;
import com.vukat.spotifymusicconsumer.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackServiceImpl implements TrackService {


    @Autowired
    private TrackRepository trackRepository;

    @Override
    public void addTrack(Track track) {
        trackRepository.save(track);
    }
}
