DROP TABLE IF EXISTS T_USER;
DROP TABLE IF EXISTS T_ARTICLE;
DROP TABLE IF EXISTS T_TAG;
DROP TABLE IF EXISTS T_CATEGORY;
DROP TABLE IF EXISTS R_ARTICLE_TAG;
DROP TABLE IF EXISTS R_ARTICLE_CATEGORY;

CREATE TABLE T_USER (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  username TEXT UNIQUE NOT NULL,
  password TEXT NOT NULL,
  enable NUMERIC NOT NULL DEFAULT 1
);

CREATE TABLE T_ARTICLE (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  author_id INTEGER NOT NULL,
  created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  title TEXT NOT NULL,
  content TEXT NOT NULL,
  star INTEGER NOT NULL DEFAULT 0,
  inspected INTEGER NOT NULL DEFAULT 0,
  FOREIGN KEY (author_id) REFERENCES T_USER (id)
);

CREATE TABLE T_TAG (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT UNIQUE NOT NULL
);

CREATE TABLE R_ARTICLE_TAG (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    article_id INTEGER NOT NULL,
    tag_id INTEGER NOT NULL,
    FOREIGN KEY (article_id) REFERENCES T_ARTICLE (id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES T_TAG (id) ON DELETE CASCADE
);

CREATE TABLE T_CATEGORY (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT UNIQUE NOT NULL
);

CREATE TABLE R_ARTICLE_CATEGORY (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    article_id INTEGER NOT NULL,
    category_id INTEGER NOT NULL,
    FOREIGN KEY (article_id) REFERENCES T_ARTICLE (id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES T_CATEGORY (id) ON DELETE CASCADE
);

