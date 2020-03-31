from flask import Blueprint, request, jsonify, abort

from api.Services.MainRecommenderService import RecommenderService
from api.jwt import decode_jwt

from api.config import get_logger

_logger = get_logger(logger_name=__name__)

rec_app = Blueprint('rec_app', __name__)

rec_service = RecommenderService()


@rec_app.route('/recommend', methods=['GET'])
def recommend():
    try:
        token = request.headers.get('Authorization')[7:]
        print("1")
        username = decode_jwt(token)

        posts = rec_service.recommend_posts(username)

        return jsonify({"posts" : posts})

    except:
        return jsonify({"posts" : []})
