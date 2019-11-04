package com.mycompany.tebdmaven;

import  java.sql.*;

public class PedidoBD{
  private BDjdbc conexao;
  public PedidoBD(BDjdbc conexao_){
  	     this.conexao = conexao_; 
  	
  }
/**
* Metodo que grava um item no Banco de Dados
*/	
	public void AdicionarItemBD(Item item_,int pedido_id)throws Exception{
	  PreparedStatement Stmt;
      Stmt = conexao.getConexao().prepareStatement(
      "INSERT INTO ITEM (DESCRICAO,QUANTIDADE,VALOR,PEDIDO_ID) values (?,?,?,?)");
      Stmt.setString(1,item_.getDescricao());
      Stmt.setInt(2,item_.getQuantidade());
      Stmt.setDouble(3,item_.getValorUnitario());
      Stmt.setInt(4,pedido_id);
      Stmt.executeUpdate();
      Stmt.close();
    
	}
/**
* Metodo que grava um pedido no Banco de Dados
*/	
	public void AdicionarPedidoBD(PedidoOld pedido_)throws Exception{
	  PreparedStatement Stmt;
      Stmt = conexao.getConexao().prepareStatement(
      "INSERT INTO PEDIDO (ID,DESCRICAO) VALUES (?,?)");
      Stmt.setInt(1,pedido_.getID().intValue());
      Stmt.setString(2,pedido_.getDescricao());
      Stmt.executeUpdate();
      Stmt.close();
    
	}
/**
*
*/	
	
	public void RemoverPedidoBD(Integer pedido_id)throws Exception{
	  PreparedStatement Stmt;
      Stmt = conexao.getConexao().prepareStatement(
      "DELETE FROM PEDIDO WHERE  ID=?");
      Stmt.setInt(1,pedido_id.intValue());
      Stmt.executeUpdate();
      Stmt.close();
      conexao.getConexao().commit();
    
	}
	
/**
*
*/
	
public PedidoOld ConsultarPedidoBD(Integer pedido_id)throws Exception{
		
	  
   
	  PreparedStatement Stmt;
	  ResultSet  rs;
      Stmt = conexao.getConexao().prepareStatement(
      "SELECT DESCRICAO FROM PEDIDO WHERE ID=?");
      Stmt.setInt(1,pedido_id.intValue());
      rs = Stmt.executeQuery();
      if (!(rs.next())) return null;
      
      PedidoOld p = new PedidoOld(pedido_id,rs.getString("DESCRICAO"));
      
      Stmt.close();
      rs.close();
      
      Stmt = conexao.getConexao().prepareStatement(
      "SELECT DESCRICAO,QUANTIDADE,VALOR FROM ITEM WHERE PEDIDO_ID=?");
      Stmt.setInt(1,pedido_id.intValue());
      rs = Stmt.executeQuery();
      while (rs.next()){
      	
      		
            p.addItem(rs.getString("DESCRICAO"),
            		  rs.getInt("QUANTIDADE"),
                      rs.getDouble("VALOR"));
                      
      }
      
      Stmt.close();
      rs.close();
      //conexao.getConexao().commit();
      return p;
      
	}

}