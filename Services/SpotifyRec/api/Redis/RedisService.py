import redis
import pickle


class RedisService:

    def __init__(self):
        self.redis = redis.Redis(host='localhost', port=6379, db=0)


    def setTracks(self,username,tracks):
        pickled_tracks = pickle.dumps(tracks)
        self.redis.set(username,pickled_tracks)


    def getTracks(self,username):
        result = self.redis.get(username)
        if result == None:
            return None
        else:
            return pickle.loads(result)
