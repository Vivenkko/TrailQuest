const mongoose = require('mongoose');
const Schema = mongoose.Schema;

let stopsSchema = new Schema({
    titulo: String,
    descripcion: String,
    autor: {type: Schema.ObjectId, ref: 'User'},
    puntuacion: Number,
    dificultad:    { type: String, enum:
            ['Fácil', 'Medio', 'Difícil', 'Desafiante']
    },
    favorito: Boolean
});

module.exports =
    mongoose.model('Stop', stopsSchema);