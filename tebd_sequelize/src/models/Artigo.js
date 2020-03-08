const { Model, DataTypes } = require('sequelize');

class Artigo extends Model {
  static init(sequelize) {
    super.init({
      titulo: DataTypes.STRING,
      resumo: DataTypes.TEXT,
    }, {
      sequelize,
      tableName: 'artigo',
    })
  }

  static associate(models){
    this.hasOne(models.Avaliacao, { foreignKey: 'artigo_id', as: 'avaliacao' });
    this.belongsToMany(models.Participante, { foreignKey: 'artigo_id', through: 'autor', as: 'participante' })
  }

}

module.exports = Artigo;