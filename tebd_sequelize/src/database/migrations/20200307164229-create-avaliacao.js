'use strict';

module.exports = {
  up: (queryInterface, Sequelize) => {
    return queryInterface.createTable('avaliacao', {
      id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true,
        allowNull: false,
      },
      participante_id: {
        type: Sequelize.INTEGER,
        allowNull: false,
        references: { model: 'participante', key: 'id' },
        onUpdate: 'RESTRICT',
        onDelete: 'RESTRICT',
      },
      artigo_id: {
        type: Sequelize.INTEGER,
        allowNull: false,
        references: { model: 'artigo', key: 'id' },
        onUpdate: 'RESTRICT',
        onDelete: 'RESTRICT',
      },
      nota: {
        type: Sequelize.INTEGER,
        allowNull: false,
      },
      comentario: {
        type: Sequelize.STRING,
        allowNull: true,
      },
    })
  },

  down: (queryInterface, Sequelize) => {
    return queryInterface.dropTable('avaliacao');
  }
};
