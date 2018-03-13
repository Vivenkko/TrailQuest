const mongoose = require('mongoose');
const Schema = mongoose.Schema;

let trialSchema = new Schema({
    titulo: String,
    descripcion: String,
    ciudad: String,
    pais: String,
    imagen: String,
    asistentes: Number,
    favorito: Boolean,
    dificultad:    { type: String, enum:
            ['Fácil', 'Medio', 'Difícil', 'Desafiante']
    },
    autor: { type: Schema.ObjectId, ref: 'User'},
    
});

module.exports =
    mongoose.model('Trial', trialSchema);