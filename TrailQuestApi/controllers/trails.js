const Trail = require('../models/trails');
const User = require('../models/users');
const Favorite = require('../models/favorites');
const mongoose = require('mongoose');
const imgur = require('imgur');

// GET Listar trails
module.exports.listTrails = (req, res) =>  {

    Trail.find().exec((err, result) => {
        if (err) {
            return res.status(500).jsonp({error: 500, mensaje: err.message});
        }

        if (result && result.length) {
            Trail.populate(result, {path: "author", select: '-_id displayName'}, (err, result) =>{
                res.status(200).jsonp(result);
            });
        } else {
            return res.sendStatus(404);
        }
    });

};


// GET Lista de mis trails
module.exports.listMyTrails = (req, res) => {

    Trail.find().exec((err, result) => {
        if (err) {
            return res.status(500).jsonp({error: 500, mensaje: err.message});
        }

        if (result && result.length) {
            Trail.populate(result, {path: "author", select: '-_id displayName'}, (err, result) =>{
                if (req.params.id === author.id) {
                    return res.status(200).jsonp(result);
                }
            });
        } else {
            return res.sendStatus(404);
        }
    });

};


//POST Nuevo trail
module.exports.addTrail = (req, res) => {

    if (req.files) {
        let fichero = req.files.photo;
        let imgB64 = fichero.data.toString('base64');

        imgur.setClientId('8a985ad7fa520e8');

        imgur.uploadBase64(imgB64).then(function (json) {
            createTrail(req, res, json.data.link);
        }).catch(function (err) {
            createTrail(req, res, null);
        })
    }

};

function createTrail(req, res, img) {

    User.findOne({_id: req.user}, (err, user) => {
        if (err) {
            return res.status(404).jsonp({error: 404, mensaje: 'No existe ese usuario'});
        }

        let newTrail = new Trail({
            title: req.body.title,
            description: req.body.description,
            city: req.body.city,
            country: req.body.country,
            picture: img,
            rate: req.body.rate,
            distance: req.body.distance,
            author: mongoose.Types.ObjectId(user._id),
            difficulty: req.body.difficulty,
            locations: req.body.locations
        });

        newTrail.save(function(err, trail) {
            if (err) {
                return res.status(500).jsonp({error: 500, mensaje: `${err.message}`});
            }

            Trail.populate(trail, {path: "author", select: 'displayName'}, (err, trail) => {
                res.status(200).jsonp(trail)
            });
        })
    });

}


// GET Obtener un trail
module.exports.findById = (req, res) => {

    Trail.findById(req.params.id, (err, trail) => {
        if (err) {
            return res.status(404).jsonp({error: 404, mensaje: 'No existe un trail con ese ID'});
        }

        User.populate(trail, {path: "author", select: '_id displayName email avatar'}, (err, trail)=>{
            res.status(200).jsonp(trail)
        });
    });
};


// PUT Editar un trail
module.exports.editTrail = (req, res) => {

    if (req.files) {
        let fichero = req.files.photo;
        let imgB64 = fichero.data.toString('base64');

        imgur.setClientId('8a985ad7fa520e8');

        imgur.uploadBase64(imgB64).then(function (json) {
            updateTrail(req, res, json.data.link);
        }).catch(function (err) {
            updateTrail(req, res, null);
        })
    }

};

function updateTrail(req, res, img) {

    Trail.findById(req.params.id, function (err, trail) {
        if (trail === undefined || trail === null) {
            return res.sendStatus(404);
        }

        trail.title = req.body.title;
        trail.description = req.body.description;
        trail.rate = req.body.rate;
        trail.city = req.body.city;
        trail.country = req.body.country;
        trail.picture = img;
        trail.distance = req.body.distance;
        trail.difficulty = req.body.difficulty;
        trail.locations = req.body.locations;

        trail.save(function(err, trail) {
            if (err) {
                return res.status(500).jsonp({error: 500, mensaje: `${err.message}`});
            } else {
                return res.status(200).jsonp(trail)
            }
        })
    });

}


// DELETE Borrar un trail
module.exports.deleteTrail = (req, res) => {

    Trail.findById(req.params.id, (err, trail) => {
        if (trail === undefined || trail === null) {
            return res.sendStatus(404);
        }

        trail.remove((err) => {
            if (err) {
                return res.status(500).jsonp({error: 500, mensaje: `${err.message}`});
            }

            res.sendStatus(204);
        });
    });
};


// POST Listar por valoración
module.exports.ranking = (req, res) => {

    Trail.find().exec((err, result) => {
        if (err) {
            return res.status(500).jsonp({error: 500, mensaje: err.message});
        }

        if (result && result.length) {
            Trail.populate(result, {path: "author", select: '-_id displayName'}, (err, result) =>{
                res.status(200).jsonp(result);
            });
        } else {
            return res.sendStatus(404);
        }
    });

};


// POST Hacer favorito
module.exports.makeFavorite = (req, res) => {

    User.findOne({_id: req.user}).exec((err, user) => {
        if (user === undefined || user === null) {
            return res.status(404).jsonp({error: 404, mensaje: 'User can not be null or undefined'});
        }

        if (err) {
            return res.status(404).jsonp({error: 404, mensaje: 'No existe ningún usuario con ese ID'})
        }

        Trail.findById(req.params.id, (err, trail) => {
            if (trail === undefined || trail === null) {
                return res.status(404).jsonp({error: 404, mensaje: 'Trail can not be null or undefined'});
            }

            if (err) {
                return res.status(404).jsonp({error: 404, mensaje: 'No existe ninguna ruta con ese ID'})
            }

            let fav = new Favorite({
                user: mongoose.Types.ObjectId(user._id),
                trail: mongoose.Types.ObjectId(trail._id)
            });

            fav.save((err, result) => {
                if (err) {
                    return res.status(500).jsonp({error: 500, mensaje: `${err.message}`});
                } else {
                    return res.status(201).jsonp(result);
                }
            });
        });
    });

};


// DELETE Quitar favorito
module.exports.removeFavorite = (req, res) => {

    Favorite.findById(req.params.id, (err, fav) => {
        if (fav === undefined || fav === null) {
            return res.status(404).jsonp({error: 404, mensaje: 'Favorite can not be null or undefined'});
        }

        if (err) {
            return res.status(404).jsonp({error: 404, mensaje: 'No existe ningún favorito con ese ID'})
        }

        fav.remove((err) => {
            if (err) {
                return res.status(500).jsonp({error: 500, mensaje: `${err.message}`});
            }
            res.sendStatus(204);
        });
    });

};


// GET Listar favoritos
module.exports.listFavorites = (req, res) => {

    Favorite.find({user: req.user}).exec((err, result) => {
        if (err) {
            return res.status(500).jsonp({error: 500, mensaje: err.message});
        }

        if (result && result.length) {
            Favorite.populate(result, {path: "user", select: '-_id displayName'}, (err, user) => {
                Favorite.populate(user, {path: "trail", select: '-_id title'}, (err, finalResponse) => {
                    return res.status(200).jsonp(finalResponse);
                });
            });
        } else {
            return res.sendStatus(404);
        }
    });

};


// POST Listar por dificultad
module.exports.filterByDifficulty = (req, res) => {

    Trail.find({difficulty: req.body.difficulty}).exec((err, result) => {
        if (err) {
            return res.status(500).jsonp({error: 500, mensaje: err.message});
        }

        if (result && result.length) {
            res.status(200).jsonp(result);
        } else {
            return res.sendStatus(404);
        }
    });

};