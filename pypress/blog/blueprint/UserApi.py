from flask.views import MethodView
from .AuthApi import login_required


class UserApi(MethodView):
    """API权限限定"""
    decorators = [login_required]

    """
    RESTFul接口说明
    /users/       GET         获取所有用户
    /users/       POST        创建新用户
    /users/<id>   GET         获取一个用户
    /users/<id>   PUT         更新一个用户
    /users/<id>   DELETE      删除一个用户
    """

    def get(self, uid):
        if uid is None:
            pass
        else:
            pass

    def post(self):
        pass

    def put(self, uid):
        pass

    def delete(self, uid):
        pass
