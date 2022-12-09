package com.btgpactual.desafio.dados;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("pedidos")
public class Item {

	private String produto;
	private double quantidade;
	private double preco;
	
	public Item() {
		super();
		this.produto = null;
		this.quantidade = 0.0;
		this.preco = 0.0;
	}

	public Item(String produto, double quantidade, double preco) {
		super();
		this.produto = produto;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
}
