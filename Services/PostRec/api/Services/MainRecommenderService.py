from api.Repositories.UserPostRepository import UserPostDAO
from api.Services.PostRecommendationService import PostRecommender


class RecommenderService:

    def __init__(self):
        self._user_post_dao = UserPostDAO()
        self._post_recommender = PostRecommender()

    def recommend_posts(self, username):
        user_id = self._user_post_dao.find_user_id_by_username(username)

        str_user_id = str(user_id)

        post_ids = self._post_recommender.recommend_3_post(str_user_id)

        posts = []
        for _id in post_ids:
            post = self._user_post_dao.find_post_by_id(_id)
            post["_id"] = _id
            post["category"] = ""
            posts.append(post)
        return posts
