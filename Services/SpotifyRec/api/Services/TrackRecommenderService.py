from api.Repositories.TrackRepository import TrackRepository
from api.Services.AudioFeatureAttacherService import AudioFeatureAttacherService
from api.Services.GenreLabelAttacherService import GenreLabelAttacherService
from api.Spotify.SpotifyAPI import SpotifyAPIAccess
from sklearn.metrics.pairwise import cosine_similarity
import random
import numpy as np

token = "BQAWNxnE_JOg0RyQfVkbRniZxPPUnyBkoobunui_R3vYDdllJ1EaVLRtXF11RWc5MnmkEVwEE0KX6jMn9fRLFEWrpJI1F9ERaYUlEgCGdwTJTIWpH9O3IxghRxCOCdCjNuayEhofy36BEGP7Pm3DU-ATID_BJG_QVwJGQmjY1plrr67T6_LeoQ"


class TrackRecommender:

    def __init__(self):
        self.audio_feature_attacher = AudioFeatureAttacherService()
        self.genre_label_attacher = GenreLabelAttacherService()
        self.track_repository = TrackRepository()
        self.spotify_api = SpotifyAPIAccess()
        self.user_id = None

    def init_label_values(self, tracks):
        self.genre_label_attacher.predict_genre_label(tracks)
        self.audio_feature_attacher.predict_audio_features_label(tracks)

    def take_audio_features(self, track):
        track_musical_features = []
        track_musical_features.append(track['id'])
        track_musical_features.append(track['acousticness'])
        track_musical_features.append(track['danceability'])
        track_musical_features.append(track['duration_ms'])
        track_musical_features.append(track['energy'])
        track_musical_features.append(track['instrumentalness'])
        track_musical_features.append(track['liveness'])
        track_musical_features.append(track['loudness'])
        track_musical_features.append(track['mode'])
        track_musical_features.append(track['speechiness'])
        track_musical_features.append(track['tempo'])
        track_musical_features.append(track['valence'])

        return np.array(track_musical_features)

    def search_track_by_id(self, track_id, tracks):
        for track in tracks:
            if track['id'] == track_id:
                return track
        return None

    def recommend_tracks(self, token):

        tracks = self.spotify_api.get_most_songs_according_to_user(token)
        self.init_label_values(tracks)

        self.track_repository.save_multiple_tracks_gecici(tracks)
        recommended_songs = []
        track_uris = []

        for track in tracks:

            genre_label = int(track['genre_labels'])
            audio_label = int(track['audio_features_label'])

            related_tracks_by_genre = self.track_repository.find_by_genre_and_music_labels(genre_label, audio_label)

            if len(related_tracks_by_genre) > 20:
                related_tracks_by_genre = random.sample(related_tracks_by_genre, 20)

            cos_sim = []

            track_features = self.take_audio_features(track)

            track_features = np.delete(track_features, 0)

            for track in related_tracks_by_genre[:20]:
                candidate_track_musical_features = self.take_audio_features(track)

                candidate_track_id = candidate_track_musical_features[0]
                candidate_track_musical_features = np.delete(candidate_track_musical_features, 0)

                track_uris.append(f"spotify:track:{candidate_track_id}")

                similarity = cosine_similarity(candidate_track_musical_features.reshape(1, 11),
                                               track_features.reshape(1, 11))
                cos_sim.append((similarity, candidate_track_id))

            cos_sim.sort(reverse=True)

            for most_similar in cos_sim[:5]:
                similarity, id = most_similar

                selected_track = self.search_track_by_id(id, related_tracks_by_genre)

                selected_track['_id'] = id

                recommended_songs.append(selected_track)

        self.spotify_api.create_playlist(token, set(track_uris))

        return recommended_songs
