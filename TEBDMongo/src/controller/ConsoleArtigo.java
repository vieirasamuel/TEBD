package controller;

import java.util.*;

import model.Artigo;
import mysql.*;

import java.sql.*;


public  class ConsoleArtigo{
	
	private BDjdbc conexao = new BDjdbc();
	private ArtigoBD artigoBD;
	public static final int CONSULTAR = 1;
	
	
	public ConsoleArtigo() throws Exception{
     conexao.setConexao();
     artigoBD = new ArtigoBD(conexao);
		
	}


	
	public int criaMenuPrincipal(){
		int opcao;
		System.out.println("Menu de Opcoes:");
		System.out.println("-------------------");
		System.out.println("1. Consultar Pedido");
		System.out.println("2. Sair do Programa");
		System.out.println("-------------------");
		return opcao = Console.readInt();
	}
	
	
	public void ConsultarArtigo() throws Exception{
		int opcao;
		System.out.println("Consultar Pedido:");
		System.out.println("-------------------");
		System.out.println(artigoBD.ConsultarArtigoBD());
	
	}
	
	public static void main(String args[]){
		
		try {
			ConsoleArtigo cp = new ConsoleArtigo();
			int opcao;
			while((opcao = cp.criaMenuPrincipal()) != 2){
				if(opcao == ConsoleArtigo.CONSULTAR)
					cp.ConsultarArtigo();
				else System.out.println("Escolha uma opcao correta.");
			}
		}catch(Exception e){
			e.printStackTrace();
			}	
	}
}
