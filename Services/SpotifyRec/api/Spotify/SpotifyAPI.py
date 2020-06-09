import requests
import json
from api.ModelClasses.AudioFeaturesScaler import AudioFeaturesScaler



class SpotifyAPIAccess:

    def find_artist_genre(self, artist_id, token):
        r = requests.get(f"https://api.spotify.com/v1/artists/{artist_id}",
                         headers={"Authorization": f"Bearer {token}"});

        genres = r.json()['genres']
        return genres

    def find_user_most_listen_songs(self, token):
        r = requests.get(f"https://api.spotify.com/v1/me/top/tracks", params={"time_range" : "medium_term"}, headers={"Authorization": f"Bearer {token}"});

        tracks = r.json()['items']

        tracks_list = []
        tracks_list_count = 0
        max_track = 10

        for track in tracks:
            first_index = 0
            data = {}

            data['album_name'] = track['album']['name']
            data['album_uri'] = track['album']['uri']
            data['artist_name'] = track['artists'][first_index]['name']
            data['artist_uri'] = track['artists'][first_index]['uri']

            data['duration_ms'] = track['duration_ms']
            data['track_name'] = track['name']
            data['track_uri'] = track['uri']
            data['track_href_play'] = track['external_urls']['spotify']
            data_id = track['artists'][first_index]['id']

            data['id'] = track['id']

            data['genres'] = " ".join(self.find_artist_genre(data_id, token))

            tracks_list_count += 1

            duplicated = False
            for track_in in tracks_list:
                if data['artist_uri'] == track_in['artist_uri']:
                    duplicated = True

            if not duplicated:
                tracks_list.append(data)
            else:
                continue

            if tracks_list_count > max_track:
                return tracks_list[:max_track]


        return tracks_list

    def find_tracks_features(self, tracks, token):
        for i, track in enumerate(tracks):
            track_id = track['id']

            r = requests.get(f"https://api.spotify.com/v1/audio-features/{track_id}",
                             headers={"Authorization": f"Bearer {token}"});

            content = r.json()
            track['danceability'] = content['danceability']
            track['energy'] = content['energy']
            track['valence'] = content['valence']
            track['acousticness'] = content['acousticness']
            track['instrumentalness'] = content['instrumentalness']
            track['liveness'] = content['liveness']
            track['loudness'] = content['loudness']
            track['mode'] = content['mode']
            track['speechiness'] = content['speechiness']
            track['tempo'] = content['tempo']

            tracks[i] = track
        return tracks

    def scale_audio_features(self,tracks):

        scaler = AudioFeaturesScaler()

        labels = ["acousticness", "danceability", "duration_ms", "energy", "instrumentalness",   "liveness", "loudness", "speechiness", "tempo", "valence"]


        for i,track in enumerate(tracks):
            track_audio_labels = []
            for label in labels:
                track_audio_labels.append(track[label])

            scaled_track_audio_labels = scaler.transform([track_audio_labels])
            label_index = 0
            for label in labels:
                track[label] = scaled_track_audio_labels[0][i]
                label_index += 1

            tracks[i] = track

            return tracks

    def get_most_songs_according_to_user(self, token):

        songs = self.find_user_most_listen_songs(token)

        featured_songs = self.find_tracks_features(songs,token)

        scaled_featured_songs = self.scale_audio_features(featured_songs)

        return scaled_featured_songs

    def create_playlist(self,token,track_uris):

        user_id = self.get_user_id(token)

        r = requests.post(f"https://api.spotify.com/v1/users/{user_id}/playlists",
                         data=json.dumps({"name" : "Cukatify Recommender System Playlist","public":False,"description" : "Thanks for using Cukatify"}),
                         headers={"Authorization": f"Bearer {token}"});

        playlist_id = r.json()['id']
        uris = ",".join(track_uris[:25])
        r = requests.post(f"https://api.spotify.com/v1/playlists/{playlist_id}/tracks",
                          params={"uris": uris},
                          headers={"Authorization": f"Bearer {token}"});




    def get_user_id(self,token):
        r = requests.get(f"https://api.spotify.com/v1/me",
                         headers={"Authorization": f"Bearer {token}"});

        return r.json()['id']
