// POST Crear parada
module.exports.addTrail = (req, res) => {

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
