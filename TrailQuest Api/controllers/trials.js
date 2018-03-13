const Trial = require('../models/trials');
const User = require('../models/users');

//GET Obtener todos los trials
module.exports.findAllTrials = (req, res) => {

    Trial.find((err, trials) => {
        if (err)
            return res
                .status(500)
                .jsonp({
                    error: 500,
                    mensaje: `${err.message}`
                });

        if (trials && trials.length) {
            User.populate('trials', {path: "autor", select: '_id displayName email avatar'}, (err, trials) =>{
                res
                    .status(200)
                    .jsonp(trials);
            });
        } else {
            res.sendStatus(404);
        }


    });
};

// GET Obtener un trial
module.exports.findById = (req, res) => {

    Trial.findById(req.params.id, (err, trial) => {
        if (err)
            return res
                .status(404)
                .jsonp({
                    error: 404,
                    mensaje: 'No existe un trial con ese ID'
                });

        User.populate(trial, {
            path: "autor",
            select: '_id displayName email avatar'
        }, (err, trial)=>{
            res.status(200).jsonp(trial)
        });


    });
};

//POST Nuevo trial
module.exports.addTrial = (req, res) => {

    let nuevaTrial = new Trial({
       titulo: req.body.titulo,
       descripcion: req.body.descripcion,
       autor: req.user,
       valoracion: req.body.valoracion,
       dificultad: req.body.dificultad,
       favorito: req.body.favorito
    });

    nuevaTrial.save(function(err, trial) {
       if (err)
           return res
                       .status(500)
                       .jsonp({
                           error: 500,
                           mensaje: `${err.message}`
                       });

       User.populate(trial, {
           path: "autor",
           select: '_id displayName email avatar'
       }, (err, trial)=>{
           res.status(201).jsonp(trial)
       });
    });
};


// PUT Editar un trial
module.exports.editTrial = (req, res) => {

    Trial.findById(req.params.id, function (err, trial) {
        trial.titulo = req.body.titulo;
        trial.descripcion = req.body.descripcion;
        trial.valoracion = req.body.valoracion;
        trial.favorito = req.body.favorito;
        trial.dificultad = req.body.dificultad;

        trial.save(function(err, trial) {
            if (err)
                return res
                    .status(500)
                    .jsonp({
                        error: 500,
                        mensaje: `${err.message}`
                    });
            User.populate(trial, {
                path: "autor",
                select: '_id displayName email avatar'
            }, (err, trial)=>{
                res.status(200).jsonp(trial)
            });
        })
    });
};


// DELETE Borrar un trial
module.exports.deleteTrial = (req, res) => {

    Trial.findById(req.params.id, (err, trial) => {
        if (trial === undefined)
            return res.sendStatus(404);

        trial.remove((err) => {
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