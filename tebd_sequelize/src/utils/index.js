module.exports = {
  getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min;
  },

  getRandomNumber(min, max) {
    let valor = Math.random() * (max - min) + min;
    return valor.toFixed(1);
  }

}