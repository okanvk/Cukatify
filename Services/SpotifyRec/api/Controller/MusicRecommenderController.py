from flask import Blueprint, request, jsonify, abort

from api.Services.TrackRecommenderService import TrackRecommender

from api.jwt import decode_jwt

from api.config import get_logger

from api.Redis.RedisService import RedisService


_logger = get_logger(logger_name=__name__)

rec_app = Blueprint('rec_app', __name__)

rec_service = TrackRecommender();

redis_service = RedisService()


@rec_app.route('/recommend', methods=['GET'])
def recommend():

    token = request.headers.get('Authorization')[7:]

    username,accessToken = decode_jwt(token)
    cached_tracks = redis_service.getTracks(username)

    if cached_tracks == None:

        tracks = rec_service.recommend_tracks(accessToken)

        redis_service.setTracks(username,tracks)

        return jsonify({"tracks" : tracks})
    else:
        return jsonify({"tracks" : cached_tracks})
