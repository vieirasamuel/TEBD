const Congresso = require('../models/Congresso');
const Participante = require('../models/Participante');

module.exports = {

  async index(req, res) {
    const { congresso_id } = req.params;

    const congresso = await Congresso.findByPk(congresso_id, {
      include: { association: 'participantes' }
    });

    return res.json(congresso);

  },

  async store(req, res) {
    const { congresso_id } = req.params;
    const { 
      nome_participante,
      cpf,
      telefone,
      email,
      empresa,
      numero_cartao,
      vencimento_cartao,
      bandeira_cartao,
      avaliador,
    } = req.body;

    const congresso = await Congresso.findByPk(congresso_id);

    if(!congresso){
      return res.status(400).json({ error: 'Não foi possível achar o congresso' });
    }

    const participante = await Participante.create({
      nome_participante,
      cpf,
      telefone,
      email,
      empresa,
      numero_cartao,
      vencimento_cartao,
      bandeira_cartao,
      avaliador,
      congresso_id,
    });

    return res.json(participante);

  }
}