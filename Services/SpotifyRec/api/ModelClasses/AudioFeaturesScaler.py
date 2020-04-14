from sklearn.preprocessing import MinMaxScaler
from joblib import load

from api.constants import SCALER_PATH


class AudioFeaturesScaler:

    def __init__(self):
        self.minmax_scaler = load(open(SCALER_PATH, "rb"))

    def transform(self,vector):
        return self.minmax_scaler.transform(vector)