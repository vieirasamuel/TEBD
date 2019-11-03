package com.mycompany.tebdmaven;

public class Item{
	
	private String descricao;
	private int quantidade;
	private double valorUnitario;
	
	public Item(String newDescricao, int newQuantidade, double newValor){
		this.descricao = newDescricao;
		this.quantidade = newQuantidade;
		this.valorUnitario = newValor;
	}
	public String getDescricao(){
		return this.descricao;
	}
	
	public double getValorTotalItem(){
		return this.quantidade * this.valorUnitario;
	}
	
	public String toString(){
		return "Descricao: " + this.descricao + " - " + "Qtd:" +
		this.quantidade + " - " + "Valor: " + this.valorUnitario + "\n";
	}

	
	
	public int getQuantidade(){
		return this.quantidade;
	}

	public double getValorUnitario(){
		return this.valorUnitario;
	}	
}