package com.vukat.spotifymusicconsumer.service;

import com.vukat.spotifymusicconsumer.entity.Track;

import java.util.List;

public interface TrackService {

    void addTrack(Track track);

    List<Track> getLast5Tracks();

}
