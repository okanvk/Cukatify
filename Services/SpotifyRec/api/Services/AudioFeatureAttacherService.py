from api.ModelClasses.AudioFeatureLabelAttacher import AudioFeatureLabelAttacher
import numpy as np

class AudioFeatureAttacherService():

    def __init__(self):
        self.attacher = AudioFeatureLabelAttacher()


    def predict_audio_features_label(self,tracks):

        labels = ["acousticness", "danceability", "duration_ms", "energy", "instrumentalness",   "liveness", "loudness", "speechiness", "tempo", "valence"]

        for i,track in enumerate(tracks):
            features = []
            for label in labels:
                features.append(track[label])
            numpy_features = np.array(features).reshape(1, 10)
            predicted_value = self.attacher.predict_genre(numpy_features)

            tracks[i]['audio_features_label'] = predicted_value

        return tracks


