const mongoose = require('mongoose');
const Schema = mongoose.Schema;

let favoritesSchema = new Schema({
    user: { type: Schema.ObjectId, ref: 'User'},
    trail: { type: Schema.ObjectId, ref: 'Trail'}
});

module.exports =
    mongoose.model('Favorite', favoritesSchema);