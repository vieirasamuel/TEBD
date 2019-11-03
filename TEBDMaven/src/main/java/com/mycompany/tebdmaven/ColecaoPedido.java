package com.mycompany.tebdmaven;

import java.util.*;
import java.sql.*;


public  class ColecaoPedido{
	
	private BDjdbc conexao = new BDjdbc();
	private PedidoBD pedidoBD;
	public static final int ADICIONAR = 1;
	public static final int CONSULTAR = 2;
	public static final int REMOVER = 3;
	
/**
* Metodo Construtor que gera uma conexao com o Banco de Dados
*/
    public ColecaoPedido() throws Exception{
		 
    /* conexao.setLogin("sa");
     conexao.setSenha("123");
     String url = DriverUtilities.makeURL("localhost:1433", "sae", DriverUtilities.MSSQL);
     conexao.setDataSource(url);
     conexao.setConexao();
     conexao.getConexao().setCatalog("sae");*/
     conexao.setConexao();
     pedidoBD = new PedidoBD(conexao);
		
    }
/**
* Metodo que monta o menu principal e 
* obtem uma opcao do usuario
*/
	public int criaMenuPrincipal(){
		int opcao;
		System.out.println("Menu de Opcoes:");
		System.out.println("-------------------");
		System.out.println("1. Adicionar Pedido");
		System.out.println("2. Consultar Pedido");
		System.out.println("3. Remover Pedido  ");
		System.out.println("4. Sair do Programa");
		System.out.println("-------------------");
		return opcao = Console.readInt();
	}
/**
* Metodo que adiciona itens relacionado com o seu pedido
*/	
	public void AdicionarItems(Pedido p) throws Exception {
		String opcao;
		do
		{
			System.out.println("Adicionar Items:");
			System.out.println("-------------------");
			System.out.print("Descricao do Item:");
			String descricao = Console.readLine();
			System.out.print("Quantidade do Item:");
			int quantidade = Console.readInt();
			System.out.print("Valor Unitario:");
			double valorunitario = Console.readDouble();
		    pedidoBD.AdicionarItemBD(new Item(descricao,quantidade,valorunitario),p.getID().intValue());
			p.addItem(descricao, quantidade, valorunitario);
			System.out.print("Deseja Adicionar mais um Item? [S|N]: ");
			opcao = Console.readLine();
		} while (opcao.compareTo("S") == 0);
	}
	

/**
* 
*/	
	public void AdicionarPedido()throws Exception{
		Pedido p;
		String opcao;
		do {
			System.out.println("Adicionar Pedido:");
			System.out.println("-------------------");
			System.out.print("ID do Pedido:");
			Integer id = new Integer(Console.readInt());
			System.out.print("Descricao do Pedido:");
			String descricao = Console.readLine();
			
						
			p = new Pedido(id, descricao);
			conexao.getConexao().setAutoCommit(false);
			// Gravando pedido no  Banco de Dados
			pedidoBD.AdicionarPedidoBD(p);
			
			this.AdicionarItems(p);

			
			conexao.getConexao().commit();
			//conexao.getConexao().setAutoCommit(true);
			
			System.out.print("Deseja Adicionar mais um Pedido? [S|N]: ");
			opcao = Console.readLine();
		} while(opcao.compareTo("S") == 0);

	}
	
	
	
	
	public void RemoverPedido()throws Exception{
		int opcao;
		System.out.println("Remover Pedido:");
		System.out.println("-------------------");
		System.out.print("Digite o id do Pedido:");
		Integer id = new Integer(Console.readInt());
		pedidoBD.RemoverPedidoBD(id);
	}
	

	
	public void ConsultarPedido() throws Exception{
		int opcao;
		System.out.println("Consultar Pedido:");
		System.out.println("-------------------");
		System.out.print("Digite o id do Pedido:");
		Integer id = new Integer(Console.readInt());
		
		System.out.println(pedidoBD.ConsultarPedidoBD(id));
	
	}
	
	public static void main(String args[]){
		
		try {
			ColecaoPedido cp = new ColecaoPedido();
			int opcao;
			while((opcao = cp.criaMenuPrincipal()) != 4){
				if(opcao == ColecaoPedido.ADICIONAR)
					cp.AdicionarPedido();
				else if(opcao == ColecaoPedido.CONSULTAR)
					cp.ConsultarPedido();
				else if(opcao == ColecaoPedido.REMOVER)
					cp.RemoverPedido();
				else System.out.println("Escolha uma opcao correta.");
			}
		}catch(Exception e){
			e.printStackTrace();
			}	
	}
}