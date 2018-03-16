const Event = require('../models/events');
const User = require('../models/users');
const Following = require('../models/following');
const Moment = require('moment');
const mongoose = require('mongoose');

function addEvent (req, res, img) {
    let newEvent = new Event({
        title: req.body.title,
        description: req.body.description,
        picture: img,
        location: req.body.location,
        city: req.body.city,
        country: req.body.country,
        date: Moment(req.body.date, "DD/MM/YYYY")
    });

    newEvent.attendants.push(user._id);

    newEvent.save(function (err, event) {
        if (err) {
            return res.status(500).jsonp({error: 500, mensaje: `${err.message}`});
        } else {
            return res.status(200).jsonp(event);
        }
    });
}

// POST Crear evento
module.exports.addEvent = (req, res) => {
    User.findOne({_id: req.user}, (err, user) => {
        if (user === undefined || user === null) {
            return res.status(404).jsonp({error: 404, mensaje: 'Event can not be null or undefined'});
        }

        if(user.isAdmin){
            addEvent()
        } else {
            return res.status(403).jsonp({error: 403, mensaje: "No tiene autorización para acceder"});
        }
    });

};

// GET Buscar un evento por id
module.exports.findById = (req, res) => {

    Event.findById(req.params.id, (err, event) => {
        if (event === undefined || event === null) {
            return res.status(404).jsonp({error: 404, mensaje: 'Event can not be null or undefined'});
        }

        if (err) {
            return res.status(404).jsonp({error: 404, mensaje: 'No existe ningún evento con ese ID'})
        }

        return res.status(200).jsonp(event);
    });

};


// PUT Editar un evento
module.exports.editEvent = (req, res) => {

    Event.findById(req.params.id, function (err, event) {
        if (event === undefined || event === null) {
            return res.status(404).jsonp({error: 404, mensaje: 'Event can not be null or undefined'});
        }

        event.title = req.body.title;
        event.description = req.body.description;
        event.picture = req.body.picture;
        event.location = req.body.location;
        event.city = req.body.city;
        event.country = req.body.country;
        event.date = Moment(req.body.date, "DD/MM/YYYY");

        event.save(function(err, event) {
            if (err) {
                return res.status(500).jsonp({error: 500, mensaje: `${err.message}`});
            }
            return res.status(200).jsonp(event);
        });
    });

};


// DELETE Eliminar un evento
module.exports.deleteEvent = (req, res) => {

    Stop.findById(req.params.id, (err, event) => {
        if (event === undefined || event === null) {
            return res.status(404).jsonp({error: 404, mensaje: 'Event can not be null or undefined'});
        }

        event.remove((err) => {
            if (err) {
                return res.status(500).jsonp({error: 500, mensaje: `${err.message}`});
            }
            res.status(204).jsonp({mensaje: 'Evento borrado correctamente'});
        });
    });

};


// GET Listar eventos
module.exports.listEvents = (req, res) =>  {

    Event.find().exec((err, result) => {
        if (err) {
            return res.status(500).jsonp({error: 500, mensaje: err.message});
        }

        if (result && result.length) {
            return res.status(200).jsonp(result);
        } else {
            return res.sendStatus(404);
        }
    });

};


// POST Listar eventos por ciudad
module.exports.listByCity = (req, res) =>  {

    Event.find({city: req.body.city}).exec((err, result) => {
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


// POST Seguir un evento
module.exports.follow = (req, res) => {

    User.findOne({_id: req.user}).exec((err, user) => {
        if (user === undefined || user === null) {
            return res.status(404).jsonp({error: 404, mensaje: 'Event can not be null or undefined'});
        }

        if (err) {
            return res.status(404).jsonp({error: 404, mensaje: 'No existe ningún evento con ese ID'})
        }

        Event.findById(req.params.id, (err, event) => {
            if (event === undefined || event === null) {
                return res.status(404).jsonp({error: 404, mensaje: 'Event can not be null or undefined'});
            }

            if (err) {
                return res.status(404).jsonp({error: 404, mensaje: 'No existe ningún evento con ese ID'})
            }

            let following = new Following({
                user: mongoose.Types.ObjectId(user._id),
                event: mongoose.Types.ObjectId(event._id)
            });

            following.save((err, result) => {
                if (err) {
                    return res.status(500).jsonp({error: 500, mensaje: `${err.message}`});
                } else {
                    return res.status(201).jsonp(result);
                }
            });
        });
    });
};


// POST Dejar de seguir un evento
module.exports.unfollow = (req, res) => {

    Following.findById(req.params.id, (err, following) => {
        if (following === undefined || following === null) {
            return res.status(404).jsonp({error: 404, mensaje: 'Follow can not be null or undefined'});
        }

        if (err) {
            return res.status(404).jsonp({error: 404, mensaje: 'No existe ningún follow con ese ID'})
        }

        following.remove((err) => {
            if (err) {
                return res.status(500).jsonp({error: 500, mensaje: `${err.message}`});
            }
            res.sendStatus(204);
        });
    });

};


// GET Listar eventos seguidos
module.exports.listFollowing = (req, res) =>  {

    Following.find({user: req.user}).exec((err, result) => {
        if (err) {
            return res.status(500).jsonp({error: 500, mensaje: err.message});
        }

        if (result && result.length) {
           Following.populate(result, {path: "user", select: '-_id displayName'}, (err, user) => {
                Following.populate(user, {path: "event", select: '-_id title'}, (err, finalResponse) => {
                    return res.status(200).jsonp(finalResponse);
                });
            });
        } else {
            return res.sendStatus(404);
        }
    });

};