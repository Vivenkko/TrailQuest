// POST Crear evento
module.exports.addEvent = (req, res) => {

    let newEvent = new Event({
        title: req.body.title,
        description: req.body.description,
    });

    newEvent.save(function (err, stop) {
        if (err) {
            return res.status(500).jsonp({error: 500, mensaje: `${err.message}`})
        }

        return res.sendStatus(200).jsonp(stop)

    });

};