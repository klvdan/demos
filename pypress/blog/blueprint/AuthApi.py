import functools
from flask import (
    request, g, redirect, url_for,
    flash, render_template,
    Blueprint, session
)
from werkzeug.security import check_password_hash, generate_password_hash
from blog.model import User
from blog.db import db_session

bp = Blueprint('auth', __name__, url_prefix='/auth')


@bp.route('/register', methods=['GET', 'POST'])
def register():
    if request.method == 'POST':
        """register"""
        username = request.form['username']
        password = request.form['password']
        err = None

        if not username:
            err = 'Username is required!'
        elif not password:
            err = 'Password is required!'
        elif db_session.query(User)\
                .filter_by(username=username)\
                .first() is not None:
            err = 'User {} is already registered!'.format(username)

        if err is None:
            user = User(username=username, password=generate_password_hash(password))
            db_session.add(user)
            db_session.commit()
            return redirect(url_for('auth.login'))
        flash(err)

    return render_template('auth/register.html')


@bp.route('/login', methods=['GET','POST'])
def login():
    if request.method == 'POST':
        """login"""
        username = request.form['username']
        password = request.form['password']
        err = None
        user = db_session.query(User)\
            .filter_by(username=username) \
            .first()

        if not username:
            err = 'Username is required!'
        elif not password:
            err = 'Password is required!'
        elif user is None:
            err = 'User {} is not exists!'.format(username)
        elif not check_password_hash(user.password, password):
            err = 'Password is incorrect!'

        if err is None:
            session.clear()
            session['uid'] = user.id
            return redirect(url_for('home'))

        flash(err)
    return render_template('auth/login.html')


@bp.before_app_request
def load_logged_in_user():
    uid = session.get('uid')

    if uid is None:
        g.user = None
    else:
        g.user = db_session.query(User) \
                    .filter_by(id=uid) \
                    .first()


@bp.route('/logout')
def logout():
    session.clear()
    return redirect(url_for('home'))


def login_required(fn):
    @functools.wraps(fn)
    def wrapper(**kwargs):
        if not g.user:
            return redirect(url_for('auth.login'))
        return fn(**kwargs)
    return wrapper
