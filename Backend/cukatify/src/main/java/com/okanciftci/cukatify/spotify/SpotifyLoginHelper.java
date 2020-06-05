package com.okanciftci.cukatify.spotify;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;

@Slf4j
@Component
public class SpotifyLoginHelper {

    private static final String clientId = "844bc5b0b0f84f83bd226642b3108896";
    public static final String staticPass = "A|12";
    private static final String clientSecret = "60bb43794bd94fd6a08a9996451ad258";
    private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8090/spotify/callback");

    public URI getSpotifyURI() {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setRedirectUri(redirectUri)
                .build();
        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
                .scope("user-read-email user-read-private user-top-read user-read-recently-played playlist-modify-public user-read-playback-position user-library-read playlist-modify-private user-read-currently-playing playlist-read-collaborative playlist-read-private user-follow-read")
                .build();
        try {
            final URI authorizationCodeCredentials = authorizationCodeUriRequest.execute();
            return authorizationCodeCredentials;
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
        }
        return null;
    }

    public String getSpotifyAccessToken(String code) {

        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setRedirectUri(redirectUri)
                .build();
        AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code)
                .build();

        try {
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

            System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
            return authorizationCodeCredentials.getAccessToken();

        } catch (IOException | SpotifyWebApiException | ParseException e) {
           log.error("Error: " + e.getMessage());
        }
        return null;
    }

}
