package com.vukat.spotifymusicqueueproducer.service;


import com.vukat.spotifymusicqueueproducer.exception.GeniusTokenExpiredException;
import com.vukat.spotifymusicqueueproducer.exception.SpotifyTokenExpiredException;

import com.vukat.spotifymusicqueueproducer.model.SpotifyResponse;
import com.vukat.spotifymusicqueueproducer.model.Track;
import com.vukat.spotifymusicqueueproducer.model.User;

import java.io.IOException;

public interface TrackService {

    SpotifyResponse findCurrenlyListeningTrack(String token) throws SpotifyTokenExpiredException;

    Track assignTrack(SpotifyResponse response);

    Track findCurrentlyListeningTrackLyricsUrl(Track track) throws GeniusTokenExpiredException;

    Track findTrackLyrics(Track track) throws IOException;


}
