from pymongo import MongoClient


class TrackRepository:

    def __init__(self):
        client = MongoClient()
        client = MongoClient('localhost', 27017)
        db = client.cukatify
        self.track_collection = db.Track


    def save_multiple_tracks(self,tracks):
        self.track_collection.insertMany(tracks)


    def find_by_genre_and_music_labels(self,genre_label,audio_features_label):
        tracks = []
        for data in self.track_collection.find\
                    ({"genre_labels": genre_label, "audio_features_label": audio_features_label},{"album_uri": 0,"track_uri": 0}):
            if data is None:
                continue
            tracks.append(data)
        return tracks


