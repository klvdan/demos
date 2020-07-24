from gevent.pywsgi import WSGIServer
from blog import create_app
import logging

if __name__ == '__main__':
    app = create_app()
    server = WSGIServer(('', 9999), app)
    logging.info('Listening on port 9999, Acess http://127.0.0.1:9999')
    server.serve_forever()