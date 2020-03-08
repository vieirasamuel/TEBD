const Artigo = require('../models/Artigo');
const Participante = require('../models/Participante');

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

  }
}