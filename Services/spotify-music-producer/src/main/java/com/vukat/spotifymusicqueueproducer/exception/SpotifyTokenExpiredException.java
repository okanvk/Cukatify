package com.vukat.spotifymusicqueueproducer.exception;

public class SpotifyTokenExpiredException extends Exception {

    public SpotifyTokenExpiredException(String errorMessage) {
        super(errorMessage);
    }
}
