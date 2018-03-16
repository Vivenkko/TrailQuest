const express = require('express');
const userController = require('../controllers/users');
const auth = require('../middlewares/auth');
let router = express.Router();


router.get('/list', auth.isAuth, userController.list);

router.post('/login', userController.signIn);
router.post('/register', userController.signUp);

router.get('/read', auth.isAuth, userController.findOneUser);
router.put('/update', auth.isAuth, userController.editUser);
router.delete('/delete', auth.isAuth, userController.deleteUser);

module.exports = router;
