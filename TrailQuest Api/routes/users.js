const express = require('express');
const userController = require('../controllers/users');
const auth = require('../middlewares/auth');
let router = express.Router();

/* GET users listing. */
/*router.get('/', function(req, res, next) {
  res.json({users: [{name: 'Timmy'}]});
});*/

router.post('/register', userController.signUp);
router.post('/login', userController.signIn);
router.get('/list', auth.isAuth, userController.list);

module.exports = router;
