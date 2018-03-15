const Event = require('../models/events');
const User = require('../models/users');
const Moment = require('moment');

// POST Crear evento
module.exports.addEvent = (req, res) => {
    User.findOne({_id: req.user}, (err, user) => {
        if (user === undefined || user === null) {
            return res.status(404).jsonp({error: 404, mensaje: 'Event can not be null or undefined'});
        }

        if(user.isAdmin){
            let newEvent = new Event({
                title: req.body.title,
                description: req.body.description,
                picture: req.body.picture,
                location: req.body.location,
                city: req.body.city,
                country: req.body.country,
                following: req.body.following,
                date: Moment(req.body.date, "DD/MM/YYYY"),
                //attendants: mongoose.Types.ObjectId(user._id)
            });

            newEvent.attendants.push(user._id);

            newEvent.save(function (err, event) {
                if (err) {
                    return res.status(500).jsonp({error: 500, mensaje: `${err.message}`});
                } else {
                    return res.status(200).jsonp(event);
                }
            });
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
        event.following = req.body.following;
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
            Event.populate(result, {path: "attendants", select: '-_id displayName'}, (err, listEvents) =>{
                res.status(200).jsonp(listEvents);
            });
        } else {
            return res.sendStatus(404);
        }
    });

};


// GET Listar eventos seguidos
module.exports.listFollowing = (req, res) =>  {

    Event.find({following: true}).exec((err, result) => {
        if (err) {
            return res.status(500).jsonp({error: 500, mensaje: err.message});
        }

        if (result && result.length) {
            Event.populate(result, {path: "attendants", select: '-_id displayName'}, (err, listEvents) =>{
                res.status(200).jsonp(listEvents);
            });
        } else {
            return res.sendStatus(404);
        }
    });

};


// GET Listar eventos por ciudad
module.exports.listByCity = (req, res) =>  {

    Event.find({city: req.body.city}).exec((err, result) => {
        if (err) {
            return res.status(500).jsonp({error: 500, mensaje: err.message});
        }

        if (result && result.length) {
            Event.populate(result, {path: "attendants", select: '-_id displayName'}, (err, listEvents) =>{
                res.status(200).jsonp(listEvents);
            });
        } else {
            return res.sendStatus(404);
        }
    });

};