package com.vukat.spotifymusicqueueproducer.model;

import lombok.Data;

import java.util.List;

@Data
public class GeniusResponse {

    private List<GeniusHit> hits;
}
