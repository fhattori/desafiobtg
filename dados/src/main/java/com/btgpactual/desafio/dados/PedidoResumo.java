package com.btgpactual.desafio.dados;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("clientes")
public class PedidoResumo {

	private String id;
	private double precoDoPedido;

	public PedidoResumo(String id, double precoDoPedido) {
		super();
		this.id = id;
		this.precoDoPedido = precoDoPedido;
	}
		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getPrecoTotal() {
		return precoDoPedido;
	}

	public void setPrecoTotal(double precoTotal) {
		this.precoDoPedido = precoTotal;
	}
	
}
