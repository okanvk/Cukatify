package com.vukat.spotifymusicconsumer.repository;

import com.vukat.spotifymusicconsumer.entity.Track;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TrackRepository extends MongoRepository<Track,String> {

    List<Track> findByOrderByCreatedAtDesc();



}
