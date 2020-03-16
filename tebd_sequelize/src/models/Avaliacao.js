const { Model, DataTypes } = require('sequelize');

class Avaliacao extends Model {
  static init(sequelize) {
    super.init({
      nota: DataTypes.DECIMAL(10,2),
      comentario: DataTypes.INTEGER,
    }, {
      sequelize,
      tableName: 'avaliacao',
    })
  }

  static associate(models) {
    this.belongsTo(models.Artigo, { foreignKey: 'fk_artigo', as: 'artigo' });
    this.belongsTo(models.Participante, { foreignKey: 'fk_participante', as: 'participante' });
  }
}

module.exports = Avaliacao;