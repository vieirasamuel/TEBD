const FatoParticipante = require('../../models/DW/FatoParticipante');
const DimensaoEmpresa = require('../../models/DW/DimensaoEmpresa');
const DimensaoPagamento = require('../../models/DW/DimensaoPagamento');
const DimensaoEndereco = require('../../models/DW/DimensaoEndereco');
const DimensaoArtigo = require('../../models/DW/DimensaoArtigo');
const DimensaoTipoParticipante = require('../../models/DW/DimensaoTipoParticipante');
const Participante = require('../../models/Participante');
const Artigo = require('../../models/Artigo');
const Avaliacao = require('../../models/Avaliacao');
const sequelize = require('sequelize');
const Utils = require('../../utils');
const Sequelize = require('../../database').dw;


createTipos = async() => {
  try{
    await DimensaoTipoParticipante.findOrCreate({where: { tipo_id: 0, tipo_descricao: 'Normal' }});
    await DimensaoTipoParticipante.findOrCreate({where: { tipo_id: 1, tipo_descricao: 'Avaliador' }});
    return 'Tipos importados';
  } catch (error) {
    return error;
  }
}

importEmpresas = async() =>  {
  let registrosInseridos = [];

  const empresas = await Participante.findAll({
    attributes:[[sequelize.literal('DISTINCT `empresa`'), 'nome_empresa']],
    raw: true,
  });

  let empresasChunks = Utils.chunkArray(empresas,10000);

  try {
    await Sequelize.transaction(async (t) => {
      for (let i = 0; i < empresasChunks.length; i++) {
        const chunk = empresasChunks[i];
        await DimensaoEmpresa.bulkCreate(chunk, 
          {fields: ['nome_empresa']},
          {transaction: t},
        );
        console.log(chunk.length + ' Empresas importados');
        registrosInseridos.push(chunk.length);
      }
      console.log(registrosInseridos);
    })
    return 'Todos as empresas foram importados com sucesso'; 
  } catch (error) {
    console.log(error.original.sqlMessage);
    return 'Erro';
  }
}

importPagamentos = async() =>  {
  let registrosInseridos = [];

  const pagamento = await Participante.findAll({
    attributes:[
      [sequelize.literal('DISTINCT `bandeira_cartao`'), 'bandeira_cartao']
      ,'vencimento_cartao'
    ],
    raw: true,
  });

  let pagamentoChunks = Utils.chunkArray(pagamento,10000);

  try {
    await Sequelize.transaction(async (t) => {
      for (let i = 0; i < pagamentoChunks.length; i++) {
        const chunk = pagamentoChunks[i];
        await DimensaoPagamento.bulkCreate(chunk, 
          {fields: ['bandeira_cartao','vencimento_cartao']},
          {transaction: t},
        );
        console.log(chunk.length + ' Pagamentos importados');
        registrosInseridos.push(chunk.length);
      }
      console.log(registrosInseridos);
    })
    return 'Todos os pagamentos foram importados com sucesso'; 
  } catch (error) {
    console.log(error.original.sqlMessage);
    return 'Erro';
  }
}

importEnderecos = async() => {
  let registrosInseridos = [];

  const endereco = await Participante.findAll({
    attributes:[
      [sequelize.literal('DISTINCT `endereco`'), 'endereco_descricao']
    ],
    raw: true,
  });

  let enderecoChunks = Utils.chunkArray(endereco,10000);

  try {
    await Sequelize.transaction(async (t) => {
      for (let i = 0; i < enderecoChunks.length; i++) {
        const chunk = enderecoChunks[i];
        await DimensaoEndereco.bulkCreate(chunk, 
          {fields: ['endereco_descricao']},
          {transaction: t},
        );
        console.log(chunk.length + ' Enderecos importados');
        registrosInseridos.push(chunk.length);
      }
      console.log(registrosInseridos);
    })
    return 'Todos os enderecos foram importados com sucesso'; 
  } catch (error) {
    console.log(error.original.sqlMessage);
    return 'Erro';
  }
}

importArtigos = async() => {
  let registrosInseridos = [];

  const artigo = await Avaliacao.findAll({
    attributes:['artigo_id', [sequelize.fn('AVG', sequelize.col('nota')), 'nota_artigo'], [Sequelize.col('artigo.titulo'), 'titulo_artigo']],
    group: 'artigo_id',
    include: [
      { 
        association: 'artigo',
        attributes:[],
      },
    ],
    raw: true,
  });
  let artigoChunks = Utils.chunkArray(artigo,5000);

  try{
    await Sequelize.transaction(async (t) => {
      for (let i = 0; i < artigoChunks.length; i++) {
        const chunk = artigoChunks[i];
        await DimensaoArtigo.bulkCreate(chunk, { fields: ['artigo_id', 'titulo_artigo', 'nota_artigo'] }, { transaction: t });
        console.log(chunk.length + ' Artigos inseridos');
        registrosInseridos.push(chunk.length);
      }
      console.log(registrosInseridos);
    });
    return 'Todos os artigos foram importados com sucesso';
  } catch (error) {
    console.log(error.original.sqlMessage);
    return 'Erro';
  }
}

importParticipantes = async () => {
  let added = []; 
  const participantes = await Participante.findAll(
    {
      raw: true,
      attributes:[
        ['id', 'participante_id'],
        [Sequelize.col('autores.autor.artigo_id'), 'artigo_id'],
        'endereco',
        'empresa',
        'bandeira_cartao',
        'vencimento_cartao',
        'avaliador'
      ],
      where: { avaliador: 0},
      include: [
        {
          association: 'autores',
          attributes: [],
        }
      ]
    },
  );
  let participanteChunks = Utils.chunkArray(participantes,10000);
  
  try{
    await Sequelize.transaction(async (t) => {
      for (let i = 0; i < participanteChunks.length; i++) {
        for (let j = 0; j < participanteChunks[i].length; j++) {
          const participante = participanteChunks[i][j];
          const participante_id = participante.participante_id;
          const [ {empresa_id} ]  = await DimensaoEmpresa.findAll({
            where: { nome_empresa: participante.empresa },
            attributes: ['empresa_id'],
            raw: true,
          });
          let artigo_id = await DimensaoArtigo.findByPk(participante.artigo_id, {raw: true});
          if ( artigo_id ) {
            artigo_id = artigo_id.artigo_id;
          }
          const [ {pagamento_id} ] = await DimensaoPagamento.findAll({
            where: { 
              bandeira_cartao: participante.bandeira_cartao, 
              vencimento_cartao: participante.vencimento_cartao 
            },
            attributes: ['pagamento_id'],
            raw: true,
          });
          const [ {endereco_id} ] = await DimensaoEndereco.findAll({
            where: { endereco_descricao: participante.endereco },
            attributes: ['endereco_id'],
            raw: true,
          });
          const avaliador = 0;
          await FatoParticipante.findOrCreate({
            where:
            {
              participante_id,
              endereco_id,
              empresa_id,
              pagamento_id,
              avaliador,
              artigo_id,
            },
            transaction: t,
          });
        }
      }
    });
    return 'Todos os participantes foram importados com sucesso';
  } catch (error) {
    console.log(error.original.sqlMessage);
    return 'Erro';
  } 
  return added;
}

module.exports = {

  async index(req,res) {

    try {
      let participantes = await importParticipantes();
      res.json(participantes);
    } catch (error) {
      res.status(500).json({ msg: error});
    }

  },
};