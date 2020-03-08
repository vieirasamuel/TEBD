module.exports = {
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
};