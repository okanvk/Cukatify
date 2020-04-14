from api.ModelClasses.GenreLabelAttacher import GenreLabelAttacher
from api.ModelClasses.Genre2VecModel import Genre2VecModel
import numpy as np


class GenreLabelAttacherService:

    def __init__(self):
        self.attacher = GenreLabelAttacher()
        self.word2vec = Genre2VecModel()


    def predict_genre_label(self,tracks):
        for i,track in enumerate(tracks):
            words_genres = self.word2vec.preprocess_label(track['genres'])
            genre_vector = self.word2vec.getAvgFeatureVecs(words_genres)
            final_vector = []

            for item in genre_vector:
                item[np.isnan(item)] = 0
                final_vector.append(item)
            tracks[i]['genre_labels'] = self.attacher.predict_genre(final_vector)
        return tracks

