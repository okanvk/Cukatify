package com.vukat.spotifymusicconsumer.service;

import com.vukat.spotifymusicconsumer.entity.Track;
import com.vukat.spotifymusicconsumer.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        List<Track> tracks =  trackRepository.findByOrderByCreatedAtDesc();

        Set<Track> set = new HashSet<Track>();
        int i = 0;
        while(i <= 20){
            set.add(tracks.get(i));
            i+=1;
        }
        return set.stream().limit(5).collect(Collectors.toList());
    }
}
