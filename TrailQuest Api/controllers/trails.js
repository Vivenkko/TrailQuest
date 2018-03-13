const Trail = require('../models/trails');
const User = require('../models/users');
const Stop = require('../models/stops');

//GET Obtener todos los trails
module.exports.findAllTrails = (req, res) => {

    Trail.find((err, trails) => {
        if (err)
            return res
                .status(500)
                .jsonp({
                    error: 500,
                    mensaje: `${err.message}`
                });

        if (trails && trails.length) {
            User.populate('trails', {path: "author", select: 'displayName'}, (err, trails) =>{
                res
                    .status(200)
                    .jsonp(trails);
            });
        } else {
            res.sendStatus(404);
        }
    });
};


// GET Lista de mis trails
module.exports.listMyTrails = (req, res) => {
    User.findById({_id: req.user}, {trailList: true}, (err, user) => {
        if (err) return res.status(404).jsonp({
            error: 404,
            mensaje: 'No existe ese usuario'
        });

        User.populate(user, {
            path: "trailList",
            select: '_id title description city country picture valuation favorite distance difficulty locations stops'
        },(err, user) => {
            res.status(200).jsonp(user)
        });
    });
};


//POST Nuevo trail
module.exports.addTrail = (req, res) => {

    User.findOne({_id: req.user}, (err, user) => {
        if (err) return res.status(404).jsonp({
            error: 404,
            mensaje: 'No existe ese usuario'
        });

        let newTrail = new Trail({
           title: req.body.title,
           description: req.body.description,
           city: req.body.city,
           country: req.body.country,
           picture: req.body.picture,
           valuation: req.body.valuation,
           favorite: req.body.favorite,
           distance: req.body.distance,
           author: mongoose.Types.ObjectId(user._id),
           difficulty: req.body.difficulty,
           locations: req.body.locations,
           stops: mongoose.Types.ObjectId(stop._id)
        });

        newTrail.save(function(err, trail) {
            if (err)
                return res
                    .status(500)
                    .jsonp({
                        error: 500,
                        mensaje: `${err.message}`
                    });
            User.populate(trail, {
                path: "author",
                select: 'displayName'
            }, (err, trail) => {
                res.status(200).jsonp(trail)
            });
        })
    });
};


// GET Obtener un trail
module.exports.findById = (req, res) => {

    Trail.findById(req.params.id, (err, trail) => {
        if (err)
            return res
                .status(404)
                .jsonp({
                    error: 404,
                    mensaje: 'No existe un trail con ese ID'
                });

        User.populate(trail, {
            path: "author",
            select: '_id displayName email avatar'
        }, (err, trail)=>{
            res.status(200).jsonp(trail)
        });
    });
};


// PUT Editar un trail
module.exports.editTrail = (req, res) => {

    Trail.findById(req.params.id, function (err, trail) {
        trail.title = req.body.title;
        trail.description = req.body.description;
        trail.valuation = req.body.valuation;
        trail.favorite = req.body.favorite;
        trail.difficulty = req.body.difficulty;
        trail.city = req.body.city;
        trail.country = req.body.country;
        trail.picture = req.body.picture;
        trail.distance = req.body.distance;
        trail.difficulty = req.body.difficulty;
        trail.locations = req.body.locations;
        trail.stops = req.body.stops;

        trail.save(function(err, trail) {
            if (err)
                return res
                    .status(500)
                    .jsonp({
                        error: 500,
                        mensaje: `${err.message}`
                    });
            User.populate(trail, {
                path: "autor",
                select: '_id displayName email avatar'
            }, (err, trail)=>{
                res.status(200).jsonp(trail)
            });
        })
    });
};


// DELETE Borrar un trail
module.exports.deleteTrail = (req, res) => {

    Trail.findById(req.params.id, (err, trail) => {
        if (trail === undefined)
            return res.sendStatus(404);

        trail.remove((err) => {
            if (err)
                return res
                    .status(500)
                    .jsonp({
                        error: 500,
                        mensaje: `${err.message}`
                    });
            res.sendStatus(204);
        });
    });
};


// GET Listar por valoración
module.exports.ranking = (req, res) => {

    Trail.find((err, trails) => {
        if (err)
            return res
                .status(500)
                .jsonp({
                    error: 500,
                    mensaje: `${err.message}`
                });

        if (trails && trails.length) {
            User.populate(trails, {path: "author", select: 'displayName'}, (err, trails) => {
                res
                    .status(200)
                    .jsonp(trails);
            });
        } else {
            res.sendStatus(404);
        }
    });
};


// GET Listar favoritos
module.exports.findFavorites = (req, res) => {

    Trail.find({favorite: req.params.favorite}, (err, trails) => {
        if (err)
            return res
                .status(500)
                .jsonp({
                    error: 500,
                    mensaje: `${err.message}`
                });

        if (trails && trails.length && favorite === true) {
            User.populate('trails', {path: "author", select: 'displayName'}, (err, trails) =>{
                res
                    .status(200)
                    .jsonp(trails);
            });
        } else {
            res.sendStatus(404);
        }

        return res.sendStatus(200).jsonp(trails);
    });
};


// GET Listar por recorrido



// GET Listar por dificultad