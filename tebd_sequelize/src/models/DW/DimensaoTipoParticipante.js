const { Model, DataTypes } = require('sequelize');

class DimensaoTipoParticipante extends Model {
  static init(sequelize) {
    super.init({
      tipo_id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
      },
      tipo_descricao: DataTypes.STRING,
    }, {
      sequelize,
      tableName: 'dimensao_tipo_participante',
    })
  }

  static associate(models) {
    this.hasMany(models.FatoParticipante, { foreignKey: 'avaliador', as: 'tipoParticipante' });
  }
}

module.exports = DimensaoTipoParticipante;