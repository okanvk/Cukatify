package com.vukat.spotifymusicconsumer.repository;

import com.vukat.spotifymusicconsumer.entity.Track;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrackRepository extends MongoRepository<Track,String> {
}
