const express = require('express');
const ArtigoController = require('./controllers/ArtigoController');
const CongressoController = require('./controllers/CongressoController');
const ParticipanteController = require('./controllers/ParticipanteController');
const AutorController = require('./controllers/AutorController');
const AvaliacaoController = require('./controllers/AvaliacaoController');

const ImportController = require('./controllers/DWControllers/Import');

const ImportMobileController = require('./controllers/MobileControllers/import');



const routes = express.Router();

routes.post('/artigo', ArtigoController.store);
routes.get('/artigo', ArtigoController.index);
routes.get('/artigo/generate', ArtigoController.createArtigos);

routes.post('/congresso', CongressoController.store);
routes.get('/congresso', CongressoController.index);
routes.get('/congresso/generate', CongressoController.createCongressos);

routes.post('/congresso/:congresso_id/participante', ParticipanteController.store);
routes.get('/congresso/:congresso_id/participante', ParticipanteController.index);
routes.get('/participante/generate', ParticipanteController.createParticipantes);


routes.post('/artigo/:artigo_id/:participante_id/participante', AutorController.store);
routes.get('/artigo/:artigo_id/autores', AutorController.index);
routes.get('/autor/generate', AutorController.createAutores);


routes.post('/avaliacao/:artigo_id/:participante_id', AvaliacaoController.store);
routes.get('/artigo/:artigo_id/avaliacao', AvaliacaoController.artigo);
routes.get('/participante/:participante_id/avaliacao', AvaliacaoController.participante);
routes.get('/avaliador/generate', AvaliacaoController.createAvaliacoes);

routes.get('/import', ImportController.index);

routes.get('/import/mobile', ImportMobileController.index);




module.exports = routes;