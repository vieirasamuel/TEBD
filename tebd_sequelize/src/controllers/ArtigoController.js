const Artigo = require('../models/Artigo');

module.exports = {
  async index(req,res) {
    const artigos = await Artigo.findAll();

    return res.json(artigos);
  },

  async store(req, res) {
    const { titulo, resumo } = req.body;

    const artigo = await Artigo.create({ titulo, resumo });
    
    return res.json(artigo);
  }
};