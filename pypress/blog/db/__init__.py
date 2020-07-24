from sqlalchemy import create_engine
from sqlalchemy.orm import scoped_session, sessionmaker
from sqlalchemy.ext.declarative import declarative_base

import click
from flask import current_app, g
from flask.cli import with_appcontext
import logging
import os


# 创建数据库引擎

engine = create_engine(f'sqlite:///{os.path.join(os.path.dirname(__file__),"instance", "blog.sqlite")}', convert_unicode=True)
# 绑定session
db_session = scoped_session(sessionmaker(
    autocommit=False,
    autoflush=False,
    bind=engine
))

Base = declarative_base()
Base.query = db_session.query_property()


def init_db():
    """
    引入所有模型，并自动创建表结构
    :return:
    """
    logging.info(f'Database file is located: {os.path.join(os.path.abspath(__file__),"instance", "blog.sqlite")}')
    import blog.model
    Base.metadata.create_all(bind=engine)


@click.command('init-db')
@with_appcontext
def init_db_command():
    """
    初始化数据库命令
    :return:
    """
    init_db()
    click.echo('Database Initialized!')


def seed_db_command():
    """
    插入默认用户
    :return:
    """
    from blog.model import User
    u = User(username='kelvin', password='ilovedan')
    db_session.add(u)
    db_session.commit()


def shutdown_session(e=None):
    db_session.remove()


def init_app(app):
    app.teardown_appcontext(shutdown_session)
    app.cli.add_command(init_db_command)