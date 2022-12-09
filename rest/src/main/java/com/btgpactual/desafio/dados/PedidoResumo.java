package com.btgpactual.desafio.dados;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("clientes")
public class PedidoResumo {
	
	private String codigoPedido;
	private double precoDoPedido;
	
	public PedidoResumo() {
		super();
		this.codigoPedido = null;
		this.precoDoPedido = 0.0;
	}

	public PedidoResumo(String codigoPedido, double precoDoPedido) {
		super();
		this.codigoPedido = codigoPedido;
		this.precoDoPedido = precoDoPedido;
	}
		
	public String getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(String codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	public double getPrecoTotal() {
		return precoDoPedido;
	}

	public void setPrecoTotal(double precoTotal) {
		this.precoDoPedido = precoTotal;
	}
	
}
