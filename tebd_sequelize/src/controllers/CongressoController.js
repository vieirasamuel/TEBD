const Congresso = require('../models/Congresso');
const faker = require('faker/locale/pt_BR');

module.exports = {
  async index(req, res) {
    const congresso = await Congresso.findAll();

    return res.json(congresso);
  },

  async store(req, res) {
    const { nome_congresso } = req.body;

    const congresso = await Congresso.create({ nome_congresso });

    return res.json(congresso);
  },

  async createCongressos(req, res) {
    let congressos = [];

    for (let i = 0; i < 10; i++) {
      let congressoObj = {
        nome_congresso: faker.lorem.words()
      };
      congressos.push(congressoObj);
    }

    try {
      const congressosCriados = await Congresso.bulkCreate(congressos);
      //return res.json({ msg: 'Congressos criados' });
    } catch (error) {
      if (error) throw error;
      console.log(error.original.sqlMessage);
      //return res.status(500).json({ msg: 'Erro' });
    }
  }
};
