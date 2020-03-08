const conn = require('./database');
const faker = require('faker/locale/pt_BR');

var participante = require('./model/participante');
var artigo = require('./model/artigo');
var autor = require('./model/autor');
var avaliacao = require('./model/avaliacao');

const bandeiras = ['Visa', 'Mastercard', 'American Express', 'Elo'];

function gerarParticipante() {
  let nome = faker.name.firstName();
  let sobrenome = faker.name.lastName();
  participante.nome = `${nome} ${sobrenome}`;
  participante.cpf = faker.finance.mask(11);
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
  return participante;
}

function gerarArtigo() {
  artigo.titulo = faker.lorem.sentence();
  artigo.resumo = faker.lorem.paragraph();
  return artigo;
}

function gerarAutor(indexArtigo, indexParticipante) {
  autor.artigo = Math.floor(Math.random() * indexArtigo);
  autor.participante = Math.floor(Math.random() * indexParticipante);
  return autor;
}

function gerarAvaliacao(indexArtigo, indexParticipante) {
  avaliacao.artigo = Math.floor(Math.random() * indexArtigo);
  avaliacao.participante = Math.floor(Math.random() * indexParticipante);
  avaliacao.comentario = faker.lorem.paragraph();
  avaliacao.nota = Math.floor(Math.random() * 10);
  return avaliacao;
}

module.exports.gerarAvaliacao = this.gerarAvaliacao;
module.exports.gerarArtigo = this.gerarArtigo;
module.exports.gerarAutor = this.gerarAutor;
module.exports.gerarAvaliacao = this.gerarAvaliacao;
