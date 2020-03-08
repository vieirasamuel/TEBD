'use strict';

module.exports = {
  up: (queryInterface, Sequelize) => {
    return queryInterface.createTable('participante', {
      id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true,
        allowNull: false,
      },
      nome_participante: {
        type: Sequelize.STRING,
        allowNull: false,
      },
      cpf: {
        type: Sequelize.STRING,
        allowNull: false,
      },
      endereco: {
        type: Sequelize.STRING,
        allowNull: false,
      },
      telefone: {
        type: Sequelize.STRING,
        allowNull: false,
      },
      email: {
        type: Sequelize.STRING,
        allowNull: false,
      },
      empresa: {
        type: Sequelize.STRING,
        allowNull: false,
      },
      numero_cartao: {
        type: Sequelize.STRING,
        allowNull: false,
      },
      vencimento_cartao: {
        type: Sequelize.DATEONLY,
        allowNull: false,
      },
      bandeira_cartao: {
        type: Sequelize.STRING,
        allowNull: false,
      },
      avaliador: {
        type: Sequelize.BOOLEAN,
        allowNull: false,
      },
      congresso_id: {
        type: Sequelize.INTEGER,
        allowNull: false,
        references: { model: 'congresso', key: 'id' },
        onUpdate: 'RESTRICT',
        onDelete: 'RESTRICT',
      },
    })
  },

  down: (queryInterface, Sequelize) => {
    return queryInterface.dropTable('participante');
  }
};
