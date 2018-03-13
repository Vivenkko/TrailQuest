const express = require('express');
const trailsController = require('../controllers/trails');

let router = express.Router();

router.get('/list', trailsController.findAllTrails);
router.get('/list_mine', trailsController.listMyTrails);

router.post('/create/:id', trailsController.addTrail);
router.get('/read/:id', trailsController.findById);
router.put('/update/:id', trailsController.editTrail);
router.delete('/delete/:id', trailsController.deleteTrail);

module.exports = router;