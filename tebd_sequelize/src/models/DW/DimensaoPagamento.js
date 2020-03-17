const { Model, DataTypes } = require('sequelize');

class DimensaoPagamento extends Model {
  static init(sequelize) {
    super.init({
      pagamento_id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true,
      },
      bandeira_cartao: DataTypes.STRING,
      vencimento_cartao: DataTypes.DATEONLY,

    }, {
      sequelize,
      tableName: 'dimensao_pagamento',
    })
  }

  static associate(models) {
    this.hasMany(models.FatoParticipante, { foreignKey: 'pagamento_id', as: 'pagamento' });
  }
}

module.exports = DimensaoPagamento;