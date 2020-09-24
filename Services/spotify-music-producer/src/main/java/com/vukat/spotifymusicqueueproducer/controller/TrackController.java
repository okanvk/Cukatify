package com.vukat.spotifymusicqueueproducer.controller;

import com.vukat.spotifymusicqueueproducer.exception.GeniusTokenExpiredException;
import com.vukat.spotifymusicqueueproducer.exception.SpotifyTokenExpiredException;
import com.vukat.spotifymusicqueueproducer.model.GeniusGeneralResponse;
import com.vukat.spotifymusicqueueproducer.model.SpotifyResponse;
import com.vukat.spotifymusicqueueproducer.model.Track;
import com.vukat.spotifymusicqueueproducer.model.User;
import com.vukat.spotifymusicqueueproducer.service.ProducerService;
import com.vukat.spotifymusicqueueproducer.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@CrossOrigin
public class TrackController {

    @Autowired
    private ProducerService producerService;

    @Autowired
    private TrackService trackService;

    @GetMapping(value = "/enqueue/{token}/{fullName}/{username}")
    public ResponseEntity<?> enqueTrack(@PathVariable String token,@PathVariable String fullName,@PathVariable String username) {

        try {

            SpotifyResponse body = trackService.findCurrenlyListeningTrack(token);

            Track track = trackService.assignTrack(body);

            track = trackService.findCurrentlyListeningTrackLyricsUrl(track);

            track = trackService.findTrackLyrics(track);

            track.setFullName(fullName);

            track.setListenerEmail(username);

            producerService.enqueuTrack(track);

            return ResponseEntity.status(200).body(track);

        }catch (SpotifyTokenExpiredException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
        catch (GeniusTokenExpiredException e){
            return ResponseEntity.status(401).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
