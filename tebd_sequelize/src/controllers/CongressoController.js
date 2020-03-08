const Congresso = require('../models/Congresso');

module.exports = {
  async index(req,res) {
    const congresso = await Congresso.findAll();

    return res.json(congresso);
  },

  async store(req, res) {
    const { nome_congresso } = req.body;

    const congresso = await Congresso.create({ nome_congresso });
    
    return res.json(congresso);
  }
};