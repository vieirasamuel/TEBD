const Artigo = require('../models/Artigo');
const faker = require('faker/locale/pt_BR');

module.exports = {
  async index(req, res) {
    const artigos = await Artigo.findAll();

    return res.json(artigos);
  },

  async store(req, res) {
    const { titulo, resumo } = req.body;

    const artigo = await Artigo.create({ titulo, resumo });

    return res.json(artigo);
  },

  async createArtigos(req, res) {
    let artigos = [];

    for (let i = 0; i < 10000; i++) {
      let artigoObj = {
        titulo: faker.lorem.sentence(),
        resumo: faker.lorem.paragraph()
      };
      artigos.push(artigoObj);
    }

    try {
      const artigosCriados = await Artigo.bulkCreate(artigos);
      //return res.json({msg: "Artigos criados"});
    } catch (error) {
      console.log(error.original.sqlMessage);
      //return res.status(500).json({msg: "Erro"});
    }
  }
};
