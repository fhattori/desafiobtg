package com.btgpactual.desafio.dados;

import java.util.ArrayList;
import java.util.List;

public class ClienteValorTotal {

	private String id;
	private String nomeCliente;
	private List<PedidoResumo> pedidos;
	private double quantidadePedidos;
	private double valorTotalPedidos;
	
	public ClienteValorTotal() {
		super();
		this.id = null;
		this.nomeCliente = null;
		this.setPedidos(new ArrayList<PedidoResumo>());
	}

	public ClienteValorTotal(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.nomeCliente = cliente.getNomeCliente();
		this.setPedidos(cliente.getPedidos());
		this.quantidadePedidos = ClienteCalc.getQuantidadePedidos(cliente);
		this.valorTotalPedidos = ClienteCalc.getValorTotalPedidos(cliente);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public List<PedidoResumo> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<PedidoResumo> pedidos) {
		this.pedidos = pedidos;
		this.quantidadePedidos = ClienteCalc.getValorTotalPedidos(this.getPedidos());
		this.valorTotalPedidos = ClienteCalc.getValorTotalPedidos(this.getPedidos());
	}
	
	public void addPedido(PedidoResumo pedido) {
		this.pedidos.add(pedido);
		this.quantidadePedidos = ClienteCalc.getValorTotalPedidos(this.getPedidos());
		this.valorTotalPedidos = ClienteCalc.getValorTotalPedidos(this.getPedidos());
	}

	public double getQuantidadePedidos() {
		return quantidadePedidos;
	}

	public void setQuantidadePedidos(double quantidadePedidos) {
		this.quantidadePedidos = quantidadePedidos;
	}

	public double getValorTotalPedidos() {
		return valorTotalPedidos;
	}

	public void setValorTotalPedidos(double valorTotalPedidos) {
		this.valorTotalPedidos = valorTotalPedidos;
	}
}
