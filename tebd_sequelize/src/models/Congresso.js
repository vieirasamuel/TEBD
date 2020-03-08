const { Model, DataTypes } = require('sequelize');

class Congresso extends Model {
  static init(sequelize) {
    super.init({
      nome_congresso: DataTypes.STRING,
    }, {
      sequelize,
      tableName: 'congresso',
    })
  }

  static associate(models){
    this.hasMany(models.Participante, {foreignKey: 'congresso_id', as: 'participantes'});
  }
}

module.exports = Congresso;