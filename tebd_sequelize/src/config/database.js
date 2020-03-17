module.exports = {
  databases: {
    dw: {
      dialect: 'mysql',
      host: '172.21.0.2',
      username: 'root',
      password: 'Jv@123!',
      database: 'tebd_dw',
      define: {
        timestamps: false,
        underscored: true,
        freezeTableName: true,
      },
    },
    source: {
      dialect: 'mysql',
      host: 'localhost',
      username: 'root',
      password: 'password',
      database: 'tebd',
      define: {
        timestamps: false,
        underscored: true,
        freezeTableName: true,
      },
    },
  }
};