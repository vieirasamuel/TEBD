const Artigo = require('../models/Artigo');
const Participante = require('../models/Participante');
const Avaliacao = require('../models/Avaliacao');
const Utils = require('../utils');
const faker = require('faker/locale/pt_BR');


module.exports = {

  async artigo(req, res) {
    const { artigo_id } = req.params;

    const artigo = await Artigo.findByPk(artigo_id, {
      include: { association: 'avaliacoes' },
    });

    return res.json(artigo);

  },

  async participante(req, res) {
    const { participante_id } = req.params;

    const participante = await Participante.findByPk(participante_id, {
      include: { association: 'participantes' },
    });

    return res.json(participante);

  },

  async store(req, res) {
    const { artigo_id,participante_id } = req.params;
    const { nota,comentario } = req.body;

    const participante = await Participante.findByPk(participante_id);
    const artigo = await Artigo.findByPk(artigo_id);

    if(!artigo){
      return res.status(400).json({ error: 'Não foi possível achar artigo' });
    }

    if(!participante){
      return res.status(400).json({ error: 'Não foi possível participante' });
    }

    if(!participante.avaliador){
      return res.status(400).json({ error: 'O participante não é um revisor' });
    }

    const avaliacao = await Avaliacao.create({
      nota,
      comentario,
      artigo_id,
      participante_id,
    });

    return res.json(avaliacao);

  },

  async createAvaliacoes(req, res) {
    let avaliacoes = [];
    const participantes = await Participante.findAll({
      where: {
        avaliador: true,
      }
    });
    const artigos = await Artigo.findAll();

    for (let i = 0; i < 10000; i++) {
      let participante_escolhido = participantes[Utils.getRandomInt(0,participantes.length-1)];
      let artigo_escolhido = artigos[Utils.getRandomInt(0,artigos.length-1)];
      let avaliacoesObj = {
        nota: parseFloat(Utils.getRandomNumber(0,10)),
        comentario: faker.lorem.sentence(),
        artigo_id: artigo_escolhido.id,
        participante_id: participante_escolhido.id,
      }
      avaliacoes.push(avaliacoesObj);
    }

    try {
      const avaliacoesCriadas = await Avaliacao.bulkCreate(avaliacoes);
      return res.json({msg: "Avaliacoes criadas"});
    } catch (error) {
      console.log(error.original.sqlMessage);
      return res.status(500).json({msg: "Erro"});
    }
  }

}