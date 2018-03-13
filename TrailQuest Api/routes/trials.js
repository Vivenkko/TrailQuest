const express = require('express');
const trialsController = require('../controllers/trials');

let router = express.Router();

/*router.get('/', (req, res) => {
   res
       .status(200)
       .json([{titulo: 'Saludo', descripcion: 'Hola Mundo!'}]);
});*/

router.get('/', trialsController.findAllTrials);
router.get('/:id', trialsController.findById);
router.post('/', trialsController.addTrial);
router.put('/:id', trialsController.editTrial);
router.delete('/:id', trialsController.deleteTrial);

module.exports = router;