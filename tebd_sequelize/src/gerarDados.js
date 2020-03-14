const ArtigoController = require('./controllers/ArtigoController');
const CongressoController = require('./controllers/CongressoController');
const ParticipanteController = require('./controllers/ParticipanteController');
const AutorController = require('./controllers/AutorController');
const AvaliacaoController = require('./controllers/AvaliacaoController');

var i = 0;
console.log('\n\nIniciando povoamento do banco...\n\n');
start();
async function start() {
  do {
    console.log('\n\ninitializing Congressos insertion.');
    await CongressoController.createCongressos();
    console.log('Congressos insertion finished.\n\n');
    await sleep(3000);
    console.log('\n\ninitializing Participantes insertion.');
    await ParticipanteController.createParticipantes();
    console.log('Participantes insertion finished.\n\n');
    await sleep(3000);
    console.log('\n\ninitializing Artigos insertion.');
    await ArtigoController.createArtigos();
    console.log('Artigos insertion finished.\n\n');
    await sleep(3000);
    console.log('\n\ninitializing Autores insertion.');
    await AutorController.createAutores();
    console.log('Autores insertion finished.\n\n');
    await sleep(3000);
    console.log('\n\ninitializing Avaliacoes insertion.');
    await AvaliacaoController.createAvaliacoes();
    console.log('Avaliações insertion finished.\n\n');
    await sleep(3000);
    i++;
    console.log(`\nfinished ${i} loop`);
    await sleep(3000);
  } while (i < 100);
}

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}
