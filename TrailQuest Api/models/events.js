const mongoose = require('mongoose');
const Schema = mongoose.Schema;

let eventSchema = new Schema({
    title: String,
    description: String,
    city: String,
    country: String,
    picture: String,
    date: [{ type: Date, default: Date.now }],
    following: Boolean,
    attendants: {type: Schema.ObjectId, ref: 'User'}
});

module.exports =
    mongoose.model('Event', eventSchema);