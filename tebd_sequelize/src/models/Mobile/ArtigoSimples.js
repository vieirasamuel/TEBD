const { Model, DataTypes } = require('sequelize');

class ArtigoSimples extends Model {
  static init(sequelize) {
    super.init({
      artigo: DataTypes.STRING,
    }, {
      sequelize,
      tableName: 'artigo_mob',
    })
  }

  static associate(models) {
    
  }
}

module.exports = ArtigoSimples;