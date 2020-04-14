import pickle
from api.constants import GENRE_LABEL_MODEL



class GenreLabelAttacher:

    def __init__(self):
        self.genre_attacher = pickle.load(open(GENRE_LABEL_MODEL, "rb"))
        print()

    def predict_genre(self, vector):
        predicted_genre = self.genre_attacher.predict(vector)
        return predicted_genre[0]
