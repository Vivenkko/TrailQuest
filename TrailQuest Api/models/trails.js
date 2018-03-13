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
    author: { type: Schema.ObjectId, ref: 'User'},
    difficulty:    { type: String, enum:
            ['Easy', 'Average', 'Hard', 'Challenging']
    },
    locations: { type: String },
    stops: { type: Schema.ObjectId, ref: 'Stop'}
});

module.exports =
    mongoose.model('Trail', trailSchema);