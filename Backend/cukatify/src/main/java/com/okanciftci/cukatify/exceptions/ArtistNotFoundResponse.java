package com.okanciftci.cukatify.exceptions;

public class ArtistNotFoundResponse {
    private String artistName;

    public ArtistNotFoundResponse(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
