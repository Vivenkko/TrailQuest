const mongoose = require('mongoose');
const Schema = mongoose.Schema;

let eventSchema = new Schema({
    title: String,
    description: String,
    city: String,
    location: String,
    country: String,
    picture: String,
    date: { type: Date, default: Date.now() }
});

module.exports =
    mongoose.model('Event', eventSchema);