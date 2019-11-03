package com.mycompany.tebdmaven;

import java.util.*;
public class Pedido{
	private Integer id;
	private Collection cItem = new HashSet();
	private String descricao;

	public Pedido(Integer newId, String newDescricao){
		this.id = newId;
		this.descricao = newDescricao;	
	}
	
	public Integer getID(){
		return this.id;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
	
	public void addItem(String descricao, int quantidade, double valor){
		
		this.cItem.add(new Item(descricao, quantidade, valor));
	}
	
	public double getValorTotalPedido(){
		Iterator it = this.cItem.iterator();
		double total = 0;
		while(it.hasNext())
			total += ((Item)it.next()).getValorTotalItem();
		return total;
	}
	
	public String toString(){
		String retorno;
		int count = 1;
		retorno = "Pedido:\n";
		retorno += "Id do Pedido:" + this.id + "\n";
		retorno += "Descricao do Pedido: " + this.descricao + "\n";
		retorno += "Total do Pedido: " + this.getValorTotalPedido() + "\n";
		retorno += "Itens Comprados: \n";
		Iterator it = this.cItem.iterator();
		while(it.hasNext()){
			retorno += count + ": " + it.next().toString() + "\n";
			count++;
		}
		retorno += "-------------------------";
		return retorno;
	}
}