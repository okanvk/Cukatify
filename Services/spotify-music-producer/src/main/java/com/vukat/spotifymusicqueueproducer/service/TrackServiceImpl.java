package com.vukat.spotifymusicqueueproducer.service;

import com.vukat.spotifymusicqueueproducer.exception.GeniusTokenExpiredException;
import com.vukat.spotifymusicqueueproducer.exception.SpotifyTokenExpiredException;
import com.vukat.spotifymusicqueueproducer.model.*;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public SpotifyResponse findCurrenlyListeningTrack(String token) throws SpotifyTokenExpiredException {

        HttpHeaders headers = getHttpHeaders(token);
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
        String token = "aenUVHhDiLuhFxTM6RddhqZ01mgzUrxp1HUBdWDDJ3JSV2NUYZVDzukKXGMpCm0D";

        HttpHeaders headers = getHttpHeaders(token);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(GENIUS_FIND_LYRICS_URL)
                .queryParam("q", track.getName() + " " + track.getArtistName());

        ResponseEntity<GeniusGeneralResponse> response = restTemplate.exchange(uriBuilder.build().toString(), HttpMethod.GET, entity, GeniusGeneralResponse.class);

        GeniusGeneralResponse generalResponse = response.getBody();

        List<GeniusResult> result = generalResponse.getResponse().getHits()
                .stream()
                .map(GeniusHit::getResult)
                .filter(geniusResult -> geniusResult.getPrimary_artist().getName().toLowerCase().equals(track.getArtistName().toLowerCase()))
                .collect(Collectors.toList());


        if(result == null || result.size() < 1) {
            return track;
        }

        GeniusResult geniusResult = result.get(0);

        track.setLyricsUrl(geniusResult.getUrl());
        track.setDefinition(geniusResult.getFull_title());

        return track;

    }

    @Override
    public Track findTrackLyrics(Track track) throws IOException {

        try {
            if(track.getLyricsUrl() == null || track.getLyricsUrl().equals(""))
                return track;

            String html = Jsoup.connect(track.getLyricsUrl()).get().html();

            Document doc = Jsoup.parse(html,"", Parser.xmlParser());

            String lyrics = doc.select("div.lyrics").text();


            if(lyrics == null || lyrics.equals("")){

                Elements elements = doc.select("div[class~=Lyrics__Container.*$]");
                for(Element e : elements){
                    lyrics += e.text();
                }
            }
            track.setLyrics(lyrics);
            return track;
        }catch (Exception e){
            log.warn("Can't find lyrics");
            return track;
        }

    }
}

