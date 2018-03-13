// POST Crear parada
module.exports.addStop = (req, res) => {

    let newStop = new Stop({
        title: req.body.title,
        description: req.body.description,
        picture: req.body.picture,
        location: req.body.location
    });

    newStop.save(function (err, stop) {
        if (err) {
            return res.status(500).jsonp({error: 500, mensaje: `${err.message}`})
        }

        return res.sendStatus(200).jsonp(stop)

    });

};


// GET Buscar parada por id
module.exports.findById = (req, res) => {

    Stop.findById(req.params.id, (err, stop) => {
        if (err) {
            return res.status(404).jsonp({error: 404, mensaje: 'No existe ninguna parada con ese ID'})
        }

        return res.sendStatus(200).jsonp(stop);
    });

};


// PUT Editar una parada
module.exports.editStop = (req, res) => {

    Stop.findById(req.params.id, function (err, stop) {
        stop.title = req.body.title;
        stop.description = req.body.description;
        stop.picture = req.body.picture;
        stop.location = req.body.location;

        Stop.save(req.params.id, (err, stop) => {
            if (err) {
                return res.status(404).jsonp({error: 404, mensaje: 'No existe ninguna parada con ese ID'})
            }

            return res.sendStatus(200).jsonp(stop);
        });
    });

};


// DELETE Eliminar una parada
module.exports.deleteStop = (req, res) => {

    Stop.findById(req.params.id, (err, stop) => {
        if (trail === undefined)
            return res.sendStatus(404);

        stop.remove((err) => {
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
