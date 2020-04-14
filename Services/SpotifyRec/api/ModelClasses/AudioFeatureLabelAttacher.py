from sklearn.cluster import KMeans
import pickle

from api.constants import AUDIO_LABEL_MODEL


class AudioFeatureLabelAttacher:

    def __init__(self):
        self.audio_genre_attacher = pickle.load(open(AUDIO_LABEL_MODEL,'rb'))

    def predict_genre(self, vector):
        predicted_genre = self.audio_genre_attacher.predict(vector)
        # return first index
        return predicted_genre[0]
