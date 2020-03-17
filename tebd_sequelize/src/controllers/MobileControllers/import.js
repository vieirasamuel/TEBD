const Artigo = require('../../models/Artigo');

importPapersOfAuthors = async () => {
  try {

    const artigos = await Artigo.findAll(
      {
        raw: true,
        attributes:[
          ['titulo', 'artigo'],
        ],
        include: [
          {
            association: 'avaliacoes',
            attributes: [],
            where : {nota: 10}
          }
        ]
      },
    );
    return artigos ? artigos : 'Sem resultado';
  } catch (error) {
    console.log(error.original.sqlMessage);
    return 'Erro';
  }
}

module.exports = {
  async index(req, res) {
    try {
      let resultado = await importPapersOfAuthors();
      res.json({artigos: resultado})
    } catch (error) {
      res.json('Erro')
    }
  }
}