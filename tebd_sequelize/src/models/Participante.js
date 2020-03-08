const { Model, DataTypes } = require('sequelize');

class Participante extends Model {
  static init(sequelize) {
    super.init({
      nome_participante: DataTypes.STRING,
      cpf: DataTypes.STRING,
      endereco: DataTypes.STRING,
      telefone: DataTypes.STRING,
      email: DataTypes.STRING,
      empresa: DataTypes.STRING,
      numero_cartao: DataTypes.STRING,
      vencimento_cartao: DataTypes.DATEONLY,
      bandeira_cartao: DataTypes.STRING,
      avaliador: DataTypes.BOOLEAN,
    }, {
      sequelize,
      tableName: 'participante',
    })
  }

  static associate(models) {
    this.belongsTo(models.Congresso, { foreignKey: 'congresso_id', as: 'congresso' });
    this.hasMany(models.Avaliacao, { foreignKey: 'participante_id', as: 'participantes' });
    this.belongsToMany(models.Artigo, { foreignKey: 'participante_id', through: 'autor', as: 'autores' });
  }
}

module.exports = Participante;