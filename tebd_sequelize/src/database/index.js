const Sequelize = require('sequelize');
const dbConfig = require('../config/database');

const Artigo = require('../models/Artigo');
const Participante = require('../models/Participante');
const Congresso = require('../models/Congresso');
const Avaliacao = require('../models/Avaliacao');

const connection = new Sequelize(dbConfig);

Artigo.init(connection);
Congresso.init(connection);
Participante.init(connection);
Avaliacao.init(connection, connection.models);

Artigo.associate(connection.models);
//Avaliacao.associate(connection.models);
Congresso.associate(connection.models);
Participante.associate(connection.models);


module.exports = connection;