const express = require('express');
const path = require('path');
const cookieParser = require('cookie-parser');
const bodyParser = require('body-parser');
const logger =  require('morgan'); // Para realizar el logging de la aplicación
const mongoose = require('mongoose');
const auth = require('./middlewares/auth');
const config = require('./config');

// En un futuro, lo refactorizaremos
//mongoose.connect('mongodb://192.168.99.100:27017/trialquest',
mongoose.connect(config.MONGODB_URI,
    { useMongoClient: true });
mongoose.Promise = global.Promise;
//

// Requerimos todos los routes
const users = require('./routes/users');
const trials = require('./routes/trials');

let app = express();

app.use(logger('dev')); // Para "inicializar" el logging

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());

app.use('/api/v1/auth', users);
app.use('/api/v1/trials', auth.isAuth, trials);

module.exports = app;
