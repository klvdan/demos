from sqlalchemy import (
    Column, Integer, String, NUMERIC,
    TEXT, TIMESTAMP, ForeignKey, Table, text
)
from sqlalchemy.orm import relationship
from blog.db import Base

__all__ = [
    'User', 'Tag', 'Category',
    'association_table_article_tag',
    'association_table_article_category',
    'Article'
]

class User(Base):
    __tablename__ = 'T_USER'
    id = Column(Integer, primary_key=True)
    username = Column(String(50), unique=True, nullable=False)
    password = Column(String(120), nullable=False)
    enable = Column(NUMERIC, nullable=False, default=1)

    def __init__(self, username=None, password=None):
        self.username = username
        self.password = password

    def __repr__(self):
        return f'<User> {self.username}'


association_table_article_tag = Table(
    'R_ARTICLE_TAG', Base.metadata,
    Column('article_id', Integer, ForeignKey('T_ARTICLE.id')),
    Column('tag_id', Integer, ForeignKey('T_TAG.id'))
)


class Tag(Base):
    __tablename__ = 'T_TAG'
    id = Column(Integer, primary_key=True)
    name = Column(String(50), unique=True, nullable=False)

    def __init__(self, name=None):
        self.name = name

    def __repr__(self):
        return f'<Tag> {self.name}'


association_table_article_category = Table(
    'R_ARTICLE_CATEGORY', Base.metadata,
    Column('article_id', Integer, ForeignKey('T_ARTICLE.id')),
    Column('category_id', Integer, ForeignKey('T_CATEGORY.id'))
)


class Category(Base):
    __tablename__ = 'T_CATEGORY'
    id = Column(Integer, primary_key=True)
    name = Column(String(50), unique=True, nullable=False)

    def __init__(self, name=None):
        self.name = name

    def __repr__(self):
        return f'<Category> {self.name}'


class Article(Base):
    __tablename__ = 'T_ARTICLE'
    id = Column(Integer, primary_key=True)
    # Many To One
    author_id = Column(Integer, ForeignKey('T_USER.id'))
    author = relationship('User')
    title = Column(String(120))
    content = Column(TEXT)
    star = Column(Integer, nullable=False, default=0)
    inspected = Column(Integer, nullable=False, default=0)
    created = Column(TIMESTAMP, server_default=text('CURRENT_TIMESTAMP'))
    updated = Column(TIMESTAMP, server_default=text('CURRENT_TIMESTAMP'),
                     server_onupdate=text('CURRENT_TIMESTAMP'))
    tags = relationship('Tag', secondary=association_table_article_tag)
    categories = relationship('Category', secondary=association_table_article_category)

    def __init__(self, author_id=None, title=None, content=None):
        self.author_id = author_id
        self.title = title
        self.content = content

    def __repr__(self):
        return f'<Article> {self.title + " " + self.author}'






