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


