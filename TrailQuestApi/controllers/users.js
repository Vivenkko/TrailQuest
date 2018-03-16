const bcrypt = require('bcrypt-nodejs');
const service = require('../services');
const User = require('../models/users');

// POST Sign Up
module.exports.signUp = (req, res) => {

    let user = new User({
        email: req.body.email,
        displayName: req.body.displayName,
        avatar: req.body.avatar,
        password: req.body.password,
        isAdmin: req.body.isAdmin
    });

    user.save((err, result) => {
        if (err) {
            return res.status(500).jsonp({error: 500, mensaje: `${err.message}`});
        }

        return res.status(201).jsonp({
            token: service.createToken(user),
            displayName: result.displayName,
            email: result.email,
            avatar: result.avatar,
            isAdmin: result.isAdmin
        });
    });
};

// POST Login
module.exports.signIn = (req, res) => {

    User.findOne({email: req.body.email}).select('_id email +password avatar').exec((err, user) => {

        if (err) {
            return res.status(401).jsonp({error: 401, mensaje: 'Error en la autenticación'});
        }
        if (!user) {
            return res.status(404).jsonp({error: 404, mensaje: 'No existe el usuario'});
        }

        bcrypt.compare(req.body.password, user.password, (err, result) => {
            if (err) {
                return res.status(401).jsonp({error: 401, mensaje: 'Error en la autenticación'});
            }
            if (result === false) {
                return res.status(401).jsonp({error: 401, mensaje: 'Error en la autenticación'});
            } else {
                req.user = user;
                res.status(200).jsonp({mensaje: 'Login correcto', token: service.createToken(user)});
            }
        });
    });
};

// GET Listar todos los usuarios
module.exports.list = (req, res) =>  {
    User.findOne({_id: req.user}).exec((err, admin) => {
        if(admin.isAdmin){
            User.find().select('_id displayName email').exec((err, result) => {
                if (err) {
                    return res.status(500).jsonp({error: 500, mensaje: err.message});
                }

                if (result && result.length) {
                    return res.status(200).jsonp(result);
                } else {
                    return res.sendStatus(404);
                }
            });
        } else {
            return res.status(403).jsonp({error: 403, mensaje: "No tiene autorización para acceder"});
        }
    });
};

// GET Obtener un usuario
module.exports.findOneUser = (req, res) => {

    User.findById(req.params.id, (err, user) => {
        if (err) {
            return res.status(404).jsonp({error: 404, mensaje: 'No existe un usuario con ese ID'});
        } else {
            return res.status(200).jsonp(user);
        }
    });

};

// DELETE Dar de baja un usuario
module.exports.deleteUser = (req, res) => {

    User.findById(req.params.id, (err, user) => {
        if (user === undefined || user === null) {
            return res.sendStatus(404);
        }

        user.remove((err) => {
            if (err) {
                return res.status(500).jsonp({error: 500, mensaje: `${err.message}`});
            } else {
                res.sendStatus(204);
            }
        });
    });

};

// PUT Editar un usuario
module.exports.editUser = (req, res) => {

    User.findById(req.params.id, function (err, user) {
        if (user === undefined || user === null) {
            return res.sendStatus(404);
        }

        user.email = req.body.email;
        user.displayName = req.body.displayName;
        user.avatar = req.body.avatar;
        user.password = req.body.password;

        user.save(function(err, user) {
            if (err) {
                return res.status(500).jsonp({error: 500, mensaje: `${err.message}`});
            }
            return res.status(200).jsonp(user);
        });
    });

};


