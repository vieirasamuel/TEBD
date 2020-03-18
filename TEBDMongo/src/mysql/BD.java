package mysql;

import java.sql.*;




public abstract class BD  {
  Connection conn;

  String login;
  String senha;

  public void setSenha(String senha){
    this.senha = senha;
  }

  public String getSenha(){
    return senha;
  }

  public void setLogin(String login){
    this.login = login;
  }

  public String getLogin(){
    return login;
  }

  public Connection getConexao(){
    return conn;
  }

}
