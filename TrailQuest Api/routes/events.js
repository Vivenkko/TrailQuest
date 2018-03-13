const express = require('express');
const eventsController = require('../controllers/events');

let router = express.Router();

router.get('/list', eventsController.findAllEvents);
router.get('/following', eventsController.listMyEvents);

router.post('/create/:id', eventsController.addEvent);
router.get('/read/:id', eventsController.findById);
router.put('/update/:id', eventsController.editEvent);
router.delete('/delete/:id', eventsController.deleteEvent);

module.exports = router;