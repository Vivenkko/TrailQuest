const express = require('express');
const trailsController = require('../controllers/trails');

let router = express.Router();

router.get('/list', trailsController.listTrails);
router.get('/filterMine', trailsController.listMyTrails);
router.post('/filterByDifficulty', trailsController.filterByDifficulty);
router.get('/ranking', trailsController.ranking);
router.get('/favorites', trailsController.listFavorites);
router.post('/makeFav/:id', trailsController.makeFavorite);
router.delete('/removeFav/:id', trailsController.removeFavorite);

router.post('/create', trailsController.addTrail);
router.get('/read/:id', trailsController.findById);
router.put('/update/:id', trailsController.editTrail);
router.delete('/delete/:id', trailsController.deleteTrail);

module.exports = router;