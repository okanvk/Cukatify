from pymongo import MongoClient

class PostRatingRepository:

    def __init__(self):
        client = MongoClient('localhost', 27017)
        db = client.cukatify
        self.post_rating_collection = db.Rating


    def getRatings(self):
        ratings = []
        for rating in self.post_rating_collection.find():
            ratings.append(rating)
        return ratings

    def getUserRatings(self,user_id):
        ratings = []
        for rating in self.post_rating_collection.find({"userId": str(user_id)}):
            ratings.append(rating)
            print(1)
        return len(ratings)
