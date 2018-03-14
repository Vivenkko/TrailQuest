const express = require('express');
const trailsController = require('../controllers/trails');

let router = express.Router();

router.get('/list', trailsController.findAllTrails);
//router.get('/filterMine', trailsController.listMyTrails);
//router.get('/filterByDifficulty', trailsController.filterByDifficulry);
router.get('/ranking', trailsController.ranking);
router.get('/favorites', trailsController.favorites);

router.post('/create/:id', trailsController.addTrail);
router.get('/read/:id', trailsController.findById);
router.put('/update/:id', trailsController.editTrail);
router.delete('/delete/:id', trailsController.deleteTrail);

module.exports = router;