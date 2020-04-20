package com.vukat.spotifymusicqueueproducer.service;

import com.vukat.spotifymusicqueueproducer.exception.GeniusTokenExpiredException;
import com.vukat.spotifymusicqueueproducer.exception.SpotifyTokenExpiredException;
import com.vukat.spotifymusicqueueproducer.model.*;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TrackServiceImpl implements TrackService {

    @Autowired
    RestTemplate restTemplate;

    private static final String SPOTIFY_CURRENTLY_PLAYING_URL = "https://api.spotify.com/v1/me/player/currently-playing";

    private static final String GENIUS_FIND_LYRICS_URL = "https://api.genius.com/search";

    private HttpHeaders getHttpHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+token);
        return headers;
    }

    @Override
    public SpotifyResponse findCurrenlyListeningTrack(User user) throws SpotifyTokenExpiredException {

        HttpHeaders headers = getHttpHeaders(user.getToken());
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);


        ResponseEntity<SpotifyResponse> response = restTemplate.exchange(SPOTIFY_CURRENTLY_PLAYING_URL, HttpMethod.GET, entity, SpotifyResponse.class);


        return response.getBody();
    }

    @Override
    public Track assignTrack(SpotifyResponse response) {

        Track track = new Track();

        Item item = response.getItem();

        track.setAlbumImg(item.getAlbum().getImages().get(2).getUrl());
        track.setArtistName(item.getArtists().get(0).getName());
        track.setId(item.getId());
        track.setLink(item.getExternal_urls().getSpotify());
        track.setName(item.getName());
        track.setUri(item.getUri());

        return track;
    }

    @Override
    public Track findCurrentlyListeningTrackLyricsUrl(Track track) throws GeniusTokenExpiredException {

        //Genius Token -> don't use manual
        String token = "UequxdWAlPDIaKlXpzzxQF82UuKgDvreG3FGv3K4C5t4smNhB6HqY5fX3CtYIQUF";


        HttpHeaders headers = getHttpHeaders(token);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(GENIUS_FIND_LYRICS_URL)
                .queryParam("q", track.getName() + " " + track.getArtistName());

        ResponseEntity<GeniusGeneralResponse> response = restTemplate.exchange(uriBuilder.build().toString(), HttpMethod.GET, entity, GeniusGeneralResponse.class);


        GeniusGeneralResponse generalResponse = response.getBody();

        GeniusResult result = generalResponse.getResponse().getHits()
                .stream()
                .map(GeniusHit::getResult)
                .filter(geniusResult -> geniusResult.getPrimary_artist().getName().toLowerCase().equals(track.getArtistName().toLowerCase()))
                .collect(Collectors.toList())
                .get(0);

        if(result == null) {
            return track;
        }

        track.setLyricsUrl(result.getUrl());
        track.setDefinition(result.getFull_title());

        return track;

    }

    @Override
    public Track findTrackLyrics(Track track) throws IOException {

        try {
            String html = Jsoup.connect(track.getLyricsUrl()).get().html();

            Document doc = Jsoup.parse(html);

            String lyrics = doc.select("div.lyrics").text();

            track.setLyrics(lyrics);

            return track;
        }catch (Exception e){
            log.warn("Can't find lyrics");
            return track;
        }

    }
}

