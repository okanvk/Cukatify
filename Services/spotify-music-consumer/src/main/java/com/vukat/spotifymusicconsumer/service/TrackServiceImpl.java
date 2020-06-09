package com.vukat.spotifymusicconsumer.service;

import com.vukat.spotifymusicconsumer.entity.Track;
import com.vukat.spotifymusicconsumer.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {


    @Autowired
    private TrackRepository trackRepository;

    @Override
    public void addTrack(Track track) {
        trackRepository.save(track);
    }

    @Override
    public List<Track> getLast5Tracks() {
        return trackRepository.findTop5ByOrderByCreatedAtDesc();
    }
}
