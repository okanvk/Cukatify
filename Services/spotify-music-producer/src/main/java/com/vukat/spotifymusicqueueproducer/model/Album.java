package com.vukat.spotifymusicqueueproducer.model;

import lombok.Data;

import java.util.List;

@Data
public class Album {

    private List<Image> images;

    private String name;

    private String release_date;


}
