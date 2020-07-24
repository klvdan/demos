import os
import tempfile

import pytest
from blog import create_app
from blog.db import init_db
import logging

logging.basicConfig(level=logging.DEBUG)

# @pytest.fixture
def test_app():
    log = logging.getLogger('test_app')
    db_fd, db_path = tempfile.mkstemp()
    log.debug("1 -> " , db_fd)
    log.debug("2 -> " , db_path)