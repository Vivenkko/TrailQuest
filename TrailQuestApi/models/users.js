const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const bcrypt = require('bcrypt-nodejs');
const crypto = require('crypto');

const userSchema = new Schema({

    email: {type: String, unique: true, lowercase: true},
    displayName: String,
    avatar: String,
    password: { type: String, select: false },
    isAdmin: Boolean,
    token: String,
    signupDate: { type: Date, default: Date.now() },
    lastLogin: Date
});

userSchema.pre('save', function (next) {

    let user = this;

    if (!user.isModified('password')) return next();

    bcrypt.genSalt(10, (err, salt) => {

        if (err) return next();

        bcrypt.hash(user.password, salt, null, (err, hash) => {

            if (err) return next();

            user.password = hash;
            next();

        });
    })
});

module.exports = mongoose.model('User', userSchema);