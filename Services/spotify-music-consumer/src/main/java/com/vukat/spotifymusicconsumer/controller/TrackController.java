package com.vukat.spotifymusicconsumer.controller;

import com.vukat.spotifymusicconsumer.entity.Track;
import com.vukat.spotifymusicconsumer.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tracks")
@CrossOrigin
public class TrackController {

    @Autowired
    private TrackService trackService;

    @GetMapping("/getLastFive")
    public ResponseEntity<?> getLast5Tracks(){

        List<Track> tracks = trackService.getLast5Tracks();

        return new ResponseEntity(tracks, HttpStatus.OK);
    }
}
