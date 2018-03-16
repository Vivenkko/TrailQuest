const express = require('express');
const eventsController = require('../controllers/events');

let router = express.Router();

router.get('/list', eventsController.listEvents);

router.get('/following', eventsController.listFollowing);
router.post('/listByCity', eventsController.listByCity);
router.post('/follow/:id', eventsController.follow);
router.delete('/unfollow/:id', eventsController.unfollow);

router.post('/create', eventsController.addEvent);
router.get('/read/:id', eventsController.findById);
router.put('/update/:id', eventsController.editEvent);
router.delete('/delete/:id', eventsController.deleteEvent);

module.exports = router;