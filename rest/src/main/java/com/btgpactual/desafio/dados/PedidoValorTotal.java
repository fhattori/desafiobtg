package com.btgpactual.desafio.dados;

import java.util.ArrayList;
import java.util.List;

public class PedidoValorTotal{

	private String id;

	private String codigoCliente;
	private List<Item> itens;
	private double valorTotal;
	
	public PedidoValorTotal(){
		super();
		this.id = null;
		this.codigoCliente = null;
		this.setItens(new ArrayList<Item>());
		this.setValorTotal(0.0);
	}

	public PedidoValorTotal(Pedido pedido) {
		super();
		this.id = pedido.getId();
		this.codigoCliente = pedido.getCodigoCliente();
		this.itens = pedido.getItens();
		this.setValorTotal(PedidoCalc.getPrecoTotal(pedido));
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
		this.setValorTotal(PedidoCalc.getPrecoTotal(this.getItens()));
	}

	public void addItem(Item item){
		this.itens.add(item);
		this.setValorTotal(PedidoCalc.getPrecoTotal(this.getItens()));
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
}
