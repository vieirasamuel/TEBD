const Congresso = require('../models/Congresso');
const Participante = require('../models/Participante');
const Utils = require('../utils');
const faker = require('faker/locale/pt_BR');


module.exports = {

  async index(req, res) {
    const { congresso_id } = req.params;

    const congresso = await Congresso.findByPk(congresso_id, {
      include: { association: 'participantes' }
    });

    return res.json(congresso);

  },

  async store(req, res) {
    const { congresso_id } = req.params;
    const { 
      nome_participante,
      cpf,
      telefone,
      email,
      empresa,
      numero_cartao,
      vencimento_cartao,
      bandeira_cartao,
      avaliador,
    } = req.body;

    const congresso = await Congresso.findByPk(congresso_id);

    if(!congresso){
      return res.status(400).json({ error: 'Não foi possível achar o congresso' });
    }

    const participante = await Participante.create({
      nome_participante,
      cpf,
      telefone,
      email,
      empresa,
      numero_cartao,
      vencimento_cartao,
      bandeira_cartao,
      avaliador,
      congresso_id,
    });

    return res.json(participante);

  },

  async createParticipantes(req, res){
    const bandeiras = ['Visa', 'Mastercard', 'American Express', 'Elo'];
    const congressos = await Congresso.findAll();
    let participantes = [];

    for (let i = 0; i < 10000; i++) {    
      let congresso_escolhido = congressos[Utils.getRandomInt(0,congressos.length-1)];
      let nome = faker.name.firstName();
      let sobrenome = faker.name.lastName();      
      let participanteObj = {
        nome_participante: `${nome} ${sobrenome}`,
        cpf: faker.finance.mask(11),
        endereco: `${faker.address.streetName()}, ${faker.address.city()}`,
        telefone: faker.phone.phoneNumberFormat(),
        email: faker.internet.email(nome, sobrenome),
        empresa: `${faker.company.companyName()} ${faker.company.companySuffix()}`,
        numero_cartao: faker.finance.mask(12),
        vencimento_cartao: faker.date.between(2020, 2027),
        bandeira_cartao:bandeiras[Utils.getRandomInt(0,bandeiras.length-1)],
        avaliador:Utils.getRandomInt(0,1),
        congresso_id:congresso_escolhido.id,
      };
      participantes.push(participanteObj);
    }

    try {
      const participantesCriados = await Participante.bulkCreate(participantes);
      return res.json({msg: "Participantes criados"});
    } catch (error) {
      console.log(error);
      return res.status(500).json({ msg: "Erro" });
    }

  }

}