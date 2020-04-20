package com.vukat.spotifymusicqueueproducer.model;

import lombok.Data;

import java.util.List;

@Data
public class Item {

    private Album album;
    private List<Artist> artists;
    private String uri;
    private String name;
    private String id;
    private External_Urls external_urls;
    private Integer duration_ms;

}
