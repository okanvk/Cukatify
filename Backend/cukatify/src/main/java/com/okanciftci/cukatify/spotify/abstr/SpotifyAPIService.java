package com.okanciftci.cukatify.spotify.abstr;

import com.wrapper.spotify.model_objects.specification.User;

public interface SpotifyAPIService {

    User getUserCredentials(String accessToken);
}
