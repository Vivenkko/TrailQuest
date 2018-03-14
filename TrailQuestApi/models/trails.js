const mongoose = require('mongoose');
const Schema = mongoose.Schema;

let trailSchema = new Schema({
    title: String,
    description: String,
    city: String,
    country: String,
    picture: String,
    valuation: Number,
    favorite: Boolean,
    distance: Number,
    difficulty: { type: String, enum: ['Easy', 'Average', 'Hard', 'Challenging']},
    locations: [ String ],
    author: { type: Schema.ObjectId, ref: 'User'}
});

module.exports =
    mongoose.model('Trail', trailSchema);