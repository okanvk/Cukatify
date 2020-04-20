package com.vukat.spotifymusicqueueproducer.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Track {

    private String link;
    private String name;
    private String uri;
    private String id;
    private String artistName;
    private String albumImg;
    private String lyrics;
    private String lyricsUrl;
    private String definition;
}
