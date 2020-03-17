const Sequelize = require('sequelize');
const dbConfig = require('../config/database');

const Artigo = require('../models/Artigo');
const Participante = require('../models/Participante');
const Congresso = require('../models/Congresso');
const Avaliacao = require('../models/Avaliacao');

const DimensaoArtigo = require('../models/DW/DimensaoArtigo');
const DimensaoEmpresa = require('../models/DW/DimensaoEmpresa');
const DimensaoEndereco = require('../models/DW/DimensaoEndereco');
const DimensaoPagamento = require('../models/DW/DimensaoPagamento');
const DimensaoTipoParticipante = require('../models/DW/DimensaoTipoParticipante');
const FatoParticipante = require('../models/DW/FatoParticipante');

const connectionSource = new Sequelize(dbConfig.databases.source);
const connectionDw = new Sequelize(dbConfig.databases.dw);

Artigo.init(connectionSource);
Congresso.init(connectionSource);
Participante.init(connectionSource);
Avaliacao.init(connectionSource);

Artigo.associate(connectionSource.models);
Avaliacao.associate(connectionSource.models);
Congresso.associate(connectionSource.models);
Participante.associate(connectionSource.models);

DimensaoEmpresa.init(connectionDw);
DimensaoArtigo.init(connectionDw);
DimensaoEndereco.init(connectionDw);
DimensaoPagamento.init(connectionDw);
DimensaoTipoParticipante.init(connectionDw);
FatoParticipante.init(connectionDw);

DimensaoEmpresa.associate(connectionDw.models);
DimensaoArtigo.associate(connectionDw.models);
DimensaoEndereco.associate(connectionDw.models);
DimensaoPagamento.associate(connectionDw.models);
DimensaoTipoParticipante.associate(connectionDw.models);
FatoParticipante.associate(connectionDw.models);


module.exports = {
  source: connectionSource,
  dw: connectionDw,
};