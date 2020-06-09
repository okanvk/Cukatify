from flask import Blueprint, request, jsonify, abort

from api.Services.MainRecommenderService import RecommenderService
from api.Repositories.PostRatingRepository import PostRatingRepository
from api.Repositories.UserPostRepository import UserPostDAO
from api.jwt import decode_jwt

from api.config import get_logger

_logger = get_logger(logger_name=__name__)

rec_app = Blueprint('rec_app', __name__)

rec_service = RecommenderService()

post_rating_repo = PostRatingRepository()

user_post_dao = UserPostDAO()


@rec_app.route('/recommend/<curr_post_id>', methods=['GET'])
def recommend(curr_post_id):
    try:

        token = request.headers.get('Authorization')[7:]
        username = decode_jwt(token)

        user_id = user_post_dao.find_user_id_by_username(username)

        user_post_count = post_rating_repo.getUserRatings(user_id)

        if user_post_count < 15:
            return jsonify({"posts" : []})

        posts = rec_service.recommend_posts(username,curr_post_id)

        return jsonify({"posts" : posts})

    except:
        return jsonify({"posts" : []})
