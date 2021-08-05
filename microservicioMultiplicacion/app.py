from datetime import datetime

import flask
from flask import json
from flask_pymongo import PyMongo
import os

app = flask.Flask(__name__)

mongodb_client = PyMongo(app, uri="mongodb://admin:mongo123@"+os.environ['MONGO_SERVER']+":27017/multiplicaciones"
                                                                                         "?authSource=admin")
db = mongodb_client.db

@app.route('/multiplicacion/<numero1>/por/<numero2>')
def multiplicar(numero1, numero2):
    result: int = int(numero1) * int(numero2)

    db.multiplicaciones.insert_one({'fecha': str(datetime.now()),
                                    'operacion': numero1 + " * " + numero2,
                                    'resultado': str(result)
                                    })

    return str(result)

@app.route('/multiplicaciones/')
def getMultiplicar ():
    multiplicaciones = db.multiplicaciones.find({}, {'_id': False})
    return flask.jsonify([resultado for resultado in multiplicaciones])