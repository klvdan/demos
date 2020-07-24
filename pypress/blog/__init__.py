from flask import Flask
from os import makedirs, path, urandom
import pathlib


def create_app(test_config=None):
    """
    应用工厂
    :param test_config:
    :return: app
    """
    app = Flask(__name__, instance_relative_config=True)

    app.config.from_mapping(
        SECRET_KEY=urandom(20),
        DATABASE=path.join(path.abspath(__file__), 'db','instance','blog.sqlite')
    )

    if test_config is None:
        # 用户配置，非测试模式下，如果存在则加载
        app.config.from_pyfile('config.py', silent=True)
    else:
        # 加载测试配置
        app.config.from_mapping(test_config)

    try:
        instance_path = path.join(path.abspath(__file__), 'db','instance')
        db_file_path = path.join(instance_path, 'blog.sqlite')
        makedirs(instance_path)
        if not path.exists(db_file_path):
            pathlib.Path(db_file_path).touch()

    except OSError:
        pass

    @app.route('/hello')
    def hello():
        return 'Hello!'

    from . import blueprint
    blueprint.mapping_bp(app)

    from . import db
    db.init_app(app)

    return app
