const express = require('express');
const ArtigoController = require('./controllers/ArtigoController');
const CongressoController = require('./controllers/CongressoController');
const ParticipanteController = require('./controllers/ParticipanteController');
const AutorController = require('./controllers/AutorController');

const routes = express.Router();

routes.post('/artigo', ArtigoController.store);
routes.get('/artigo', ArtigoController.index);

routes.post('/congresso', CongressoController.store);
routes.get('/congresso', CongressoController.index);

routes.post('/congresso/:congresso_id/participante', ParticipanteController.store);
routes.get('/congresso/:congresso_id/participante', ParticipanteController.index);

routes.post('/artigo/:artigo_id/:participante_id/participante', AutorController.store);
routes.get('/artigo/:artigo_id/autores', AutorController.index);



module.exports = routes;