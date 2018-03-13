const mongoose = require('mongoose');
const Schema = mongoose.Schema;

let stopsSchema = new Schema({
    title: String,
    description: String,
    picture: String,
    location: String
});

module.exports =
    mongoose.model('Stop', stopsSchema);