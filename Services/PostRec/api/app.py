from flask import Flask
from flask_cors import CORS

from api.config import get_logger


_logger = get_logger(logger_name=__name__)


def create_app(*, config_object) -> Flask:
    """Create a flask app instance."""

    flask_app = Flask('ml_api')
    flask_app.config.from_object(config_object)

    # import blueprints
    from api.PostRecommenderController import rec_app
    flask_app.register_blueprint(rec_app)
    _logger.debug('Application instance created')

    CORS(flask_app)

    return flask_app