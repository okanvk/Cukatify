import pymongo
from pymongo import MongoClient
from bson.objectid import ObjectId

class UserPostDAO:

        def __init__(self):
            client = MongoClient()
            client = MongoClient('localhost', 27017)
            db = client.cukatify
            self.user_collection = db.User
            self.post_collection = db.Post

    def find_user_id_by_username(self, username):
        user =self.user_collection.find_one({"username": username})
        return user["_id"]


    def find_post_by_id(self,id):
        obj_id = ObjectId(id)
        post = self.post_collection.find_one({"_id": obj_id})
        return post