const bcrypt = require('bcrypt-nodejs');
const service = require('../services');
const User = require('../models/users');
const imgur = require('imgur');


// POST Sign Up
module.exports.signUp = (req, res) => {

    if (req.files) {
        let fichero = req.files.photo;
        let imgB64 = fichero.data.toString('base64');

        imgur.setClientId('8a985ad7fa520e8');

        imgur.uploadBase64(imgB64).then(function (json) {
            userSignUp(req, res, json.data.link);
        }).catch(function (err) {
            userSignUp(req, res, null);
        })
    }

};


function userSignUp(req,res,img) {

    let user = new User({
        email: req.body.email,
        displayName: req.body.displayName,
        password: req.body.password,
        isAdmin: req.body.isAdmin
    });

    if (img != null) {
        user.avatar = img;
    } else {
        const md5 = crypto.createHash('md5').update(user.email).digest('hex');
        user.avatar = `https://gravatar.com/avatar/${md5}?s=200&d=retro`
    }

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

}

// POST Login
module.exports.login = (req, res) => {

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

    if (req.files) {
        let fichero = req.files.photo;
        let imgB64 = fichero.data.toString('base64');

        imgur.setClientId('8a985ad7fa520e8');

        imgur.uploadBase64(imgB64).then(function (json) {
            userEdit(req, res, json.data.link, user);
        }).catch(function (err) {
            userEdit(req, res, null, user);
        })
    }

    });
};

function userEdit(req, res, img, user) {



        user.email = req.body.email;
        user.displayName = req.body.displayName;
        user.password = req.body.password;
        user.isAdmin = req.body.isAdmin;

        if (img != null) {
            user.avatar = img;
        } else {
            user.avatar = req.user.avatar;
        }

        user.save(function(err, user) {
            if (err) {
                return res.status(500).jsonp({error: 500, mensaje: `${err.message}`});
            }
            return res.status(200).jsonp(user);
        });
}
