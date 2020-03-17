const { Model, DataTypes } = require('sequelize');

class DimensaoEndereco extends Model {
  static init(sequelize) {
    super.init({
      endereco_id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
      },
      endereco_descricao: DataTypes.STRING,
    }, {
      sequelize,
      tableName: 'dimensao_endereco',
    })
  }

  static associate(models) {
    this.hasMany(models.FatoParticipante, { foreignKey: 'endereco_id', as: 'endereco' });
  }
}

module.exports = DimensaoEndereco;