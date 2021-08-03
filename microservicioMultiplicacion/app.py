from datetime import datetime

from flask import json
import flask
import os

app = flask.Flask(__name__)


@app.route('/multiplicacion/<numero1>/por/<numero2>')
def multiplicar(numero1, numero2):
    result: int = int(numero1) * int(numero2)
    return str(result)
