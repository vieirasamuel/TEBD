const { Model, DataTypes } = require('sequelize');

class DimensaoEmpresa extends Model {
  static init(sequelize) {
    super.init({
      empresa_id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true,
      },
      nome_empresa: DataTypes.STRING,
    }, {
      sequelize,
      tableName: 'dimensao_empresa',
    })
  }

  static associate(models) {
    this.hasMany(models.FatoParticipante, { foreignKey: 'empresa_id', as: 'empresa' });
  }
}

module.exports = DimensaoEmpresa;