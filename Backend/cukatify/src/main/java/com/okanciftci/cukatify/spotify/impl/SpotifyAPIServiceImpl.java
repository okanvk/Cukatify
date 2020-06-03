package com.okanciftci.cukatify.spotify.impl;

import com.okanciftci.cukatify.spotify.abstr.SpotifyAPIService;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.User;
import com.wrapper.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class SpotifyAPIServiceImpl implements SpotifyAPIService {

    @Override
    public User getUserCredentials(String accessToken){
        try {
            SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
            GetCurrentUsersProfileRequest getCurrentUsersProfileRequest = api.getCurrentUsersProfile()
                    .build();
            User spotifyUser = getCurrentUsersProfileRequest.execute();

            return spotifyUser;
        }catch (IOException | SpotifyWebApiException | ParseException e){
            log.error("Error: " + e.getMessage());
        }
        return null;
    }

}
