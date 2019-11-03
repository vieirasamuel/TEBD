package com.mycompany.tebdmaven;

/**
*Title:           Cota    o ON
*Version:         v1.0
*Data Atualiza    o 19/02/2001
*Copyright:       Copyright (c) 2000
*Author:          Eduardo Jorge / Ant  nio Oliveira Filho
*Company:         Criativa Web
*Description:     Sistema de Cota    o para Web
**/


import java.io.*;//??
import java.sql.*;


/**
  * Classe que cont  m atributos para efetuar
  * o login padr  o com qualquer Banco de Dados.
  */

public abstract class BD  {
/**
  * Objeto que permite fazer a conexao com
  * o Banco de Dados.
  */
  Connection conn;
/**
  * Atributo para identificar o login do
  * objeto BD.
  */
  String login;

/**
  * Atributo para identificar a senha do
  * objeto BD.
  */
  String senha;
/**
 * @see If.IF_BD #setSenha(String senha)
 */
  public void setSenha(String senha){
    this.senha = senha;
  }
/**
 * @see If.IF_BD #String getSenha()
 */
  public String getSenha(){
    return senha;
  }
/**
 * @see If.IF_BD #setLogin(String login)
 */
  public void setLogin(String login){
    this.login = login;
  }
/**
 * @see If.IF_BD #String getLogin()
 */
  public String getLogin(){
    return login;
  }

  public Connection getConexao(){
    return conn;
  }

}
