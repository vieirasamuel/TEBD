const { Model, DataTypes } = require('sequelize');

class DimensaoArtigo extends Model {
  static init(sequelize) {
    super.init({
      artigo_id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
      },
      titulo_artigo: DataTypes.STRING,
      nota_artigo: DataTypes.DECIMAL(10,2),
    }, {
      sequelize,
      tableName: 'dimensao_artigo',
    })
  }

  static associate(models) {
    this.hasMany(models.FatoParticipante, { foreignKey: 'artigo_id', as: 'artigo' });
  }
}

module.exports = DimensaoArtigo;