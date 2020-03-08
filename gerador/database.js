const mysql = require('mysql');
const keys = require('./keys.js');

const conn = mysql.createConnection({
  host: keys.host,
  user: keys.user,
  password: keys.password
});

conn.connect(err => {
  if (err) {
    console.log('Erro ao conectar ao banco de dados.');
    return;
  }
  console.log('Conexão estabelecida.');
});

module.exports = conn;
