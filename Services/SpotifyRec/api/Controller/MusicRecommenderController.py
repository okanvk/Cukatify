from flask import Blueprint, request, jsonify, abort

from api.Services.TrackRecommenderService import TrackRecommender


from api.config import get_logger

_logger = get_logger(logger_name=__name__)

rec_app = Blueprint('rec_app', __name__)

rec_service = TrackRecommender()


@rec_app.route('/recommend', methods=['GET'])
def recommend():
    #try:
    token = request.headers.get('Authorization')[7:]


    tracks = rec_service.recommend_tracks(token)

    return jsonify({"tracks" : tracks})

    #except:
        #return jsonify({"tracks" : []})
