const express = require('express');
const stopsController = require('../controllers/stops');

let router = express.Router();

router.get('/stops/list', stopsController.findAllStops);

router.post('/stops/create/:id', stopsController.addStop);
router.get('/stops/read/:id', stopsController.findById);
router.put('/stops/update/:id', stopsController.editStop);
router.delete('/stops/delete/:id', stopsController.deleteStop);

module.exports = router;