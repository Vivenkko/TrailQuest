const mongoose = require('mongoose');
const Schema = mongoose.Schema;

let trialSchema = new Schema({
    titulo: String,
    descripcion: String,
    ciudad: String,
    pais: String,
    imagen: String,

    asistentes: Number,
    dificultad:    { type: String, enum:
            ['Fácil', 'Medio', 'Difícil', 'Desafiante']
    },
    favorito: Boolean
});

module.exports =
    mongoose.model('Trial', trialSchema);