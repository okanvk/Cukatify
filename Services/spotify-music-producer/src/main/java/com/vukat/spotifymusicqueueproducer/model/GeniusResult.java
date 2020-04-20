package com.vukat.spotifymusicqueueproducer.model;

import lombok.Data;

@Data
public class GeniusResult {

    private String url;
    private String full_title;
    private GeniusArtist primary_artist;
}
