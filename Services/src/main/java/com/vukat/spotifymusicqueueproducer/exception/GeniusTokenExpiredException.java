package com.vukat.spotifymusicqueueproducer.exception;

public class GeniusTokenExpiredException extends Exception {

    public GeniusTokenExpiredException(String errorMessage) {
        super(errorMessage);
    }
}

