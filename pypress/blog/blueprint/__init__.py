from .UserApi import UserApi
from . import AuthApi, HomeApi


def mapping_bp(app):
    # home
    app.register_blueprint(HomeApi.bp)
    app.add_url_rule('/', endpoint='home')
    # auth
    app.register_blueprint(AuthApi.bp)
    # users
    register_api(app, UserApi, 'user_api','/users/', pk='uid')


def register_api(app, view, endpoint, url, pk='id', pk_type='int'):
    view_func = view.as_view(endpoint)
    app.add_url_rule(url,
                     defaults={pk: None},
                     view_func=view_func,
                     methods=['GET',])
    app.add_url_rule(url,
                     view_func=view_func,
                     methods=['POST', ])
    app.add_url_rule(f'{url}<{pk_type}:{pk}>',
                     view_func=view_func,
                     methods=['GET', 'PUT', 'DELETE'])


