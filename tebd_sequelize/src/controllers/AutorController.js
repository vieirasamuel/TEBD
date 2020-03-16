const Artigo = require('../models/Artigo');
const Participante = require('../models/Participante');
const Utils = require('../utils');
const sequelize = require('../database').source;

module.exports = {

  async index(req, res) {
    const { artigo_id } = req.params;

    const artigo = await Artigo.findByPk(artigo_id, {
      include: { association: 'participante' },
    });

    return res.json(artigo);

  },

  async store(req, res) {
    const { artigo_id,participante_id } = req.params;

    const participante = await Participante.findByPk(participante_id);
    const artigo = await Artigo.findByPk(artigo_id);

    if(!artigo){
      return res.status(400).json({ error: 'Não foi possível achar artigo' });
    }

    if(!participante){
      return res.status(400).json({ error: 'Não foi possível participante' });
    }

    await artigo.addParticipante(participante);

    return res.json(participante);

  },

  async createAutores(req, res){
    const participantes = await Participante.findAll();
    const artigos = await Artigo.findAll();

    try{
      await sequelize.transaction(async (t) => {
  
        for (let i = 0; i < 10000; i++) {      
          let participante_escolhido = participantes[Utils.getRandomInt(0,participantes.length-1)];
          let artigo_escolhido = artigos[Utils.getRandomInt(0,artigos.length-1)];
    
          let artigo = await Artigo.findByPk(artigo_escolhido.id);
          let participante = await Participante.findByPk(participante_escolhido.id);
    
          await artigo.addParticipante(participante, {transaction: t});
        }
      });
      return res.json({msg: "Autores criados"});
    } catch (error) {
      console.log(error.original.sqlMessage);
      return res.status(500).json({msg: "Erro"});
    }


  }


}