const { Model, DataTypes } = require('sequelize');

class FatoParticipante extends Model {
  static init(sequelize) {
    super.init({
      participante_id: {
        type: DataTypes.INTEGER,
      },
    }, {
      sequelize,
      tableName: 'fato_participante',
    })
  }

  static associate(models) {
    this.belongsTo(models.DimensaoPagamento, { foreignKey: 'pagamento_id', as: 'pagamentos' });
    this.belongsTo(models.DimensaoArtigo, { foreignKey: 'artigo_id', as: 'artigos' });
    this.belongsTo(models.DimensaoEndereco, { foreignKey: 'endereco_id', as: 'enderecos' });
    this.belongsTo(models.DimensaoTipoParticipante, { foreignKey: 'avaliador', as: 'tipos' });
    this.belongsTo(models.DimensaoEmpresa, { foreignKey: 'empresa_id', as: 'empresas' });
  }
}

module.exports = FatoParticipante;