const conn = require('./database');
const faker = require('faker/locale/pt_BR');

var participante = require('./model/participante');

var participantes = [];
var artigos = [];
var autores = [];
var avaliacoes = [];
const bandeiras = ['Visa', 'Mastercard', 'American Express', 'Elo'];

gerarParticipante(5);

function gerarParticipante(quantidade) {
  for (let i = 0; i < quantidade; i++) {
    let nome = faker.name.firstName();
    let sobrenome = faker.name.lastName();
    participante.nome = `${nome} ${sobrenome}`;
    participante.endereco = `${faker.address.streetName()}, ${faker.address.city()}`;
    participante.telefone = faker.phone.phoneNumberFormat();
    participante.email = faker.internet.email(nome, sobrenome);
    participante.empresa = `${faker.company.companyName()} ${faker.company.companySuffix()}`;
    participante.numeroCartao = faker.finance.mask(12);
    participante.vencimentoCartao = `${faker.date
      .between(2020, 2027)
      .getMonth()}/${faker.date.between(2020, 2027).getFullYear()}`;
    participante.bandeiraCartao = bandeiras[Math.floor(Math.random() * 4)];
    participante.avaliador = Math.floor(Math.random() * 2);
    participante.congresso = 1;
    participantes[i] = participante;
    console.log(participantes[i]);
  }
}
