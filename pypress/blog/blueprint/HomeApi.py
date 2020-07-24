from flask import (
    request, g, redirect, url_for,
    flash, render_template,
    Blueprint, session
)
from blog.model import Article, Category
from blog.db import db_session

bp = Blueprint('home', __name__)


@bp.route('/')
def index():
    categories = db_session.query(Category).all()
    return render_template('home/index.html', categories=categories)