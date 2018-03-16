const mongoose = require('mongoose');
const Schema = mongoose.Schema;

let followingSchema = new Schema({
    user: { type: Schema.ObjectId, ref: 'User'},
    event: { type: Schema.ObjectId, ref: 'Event'}
});

module.exports =
    mongoose.model('Following', followingSchema);