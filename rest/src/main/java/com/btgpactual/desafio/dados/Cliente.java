package com.btgpactual.desafio.dados;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("clientes")
public class Cliente {

	@Id
	private String id;
	private String nomeCliente;
	private List<PedidoResumo> pedidos;
	
	public Cliente() {
		super();
		this.id = null;
		this.nomeCliente = null;
		this.setPedidos(new ArrayList<PedidoResumo>());
	}

	public Cliente(String id, String nomeCliente) {
		super();
		this.id = id;
		this.nomeCliente = nomeCliente;
		this.setPedidos(new ArrayList<PedidoResumo>());
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
	}
	
	public void addPedido(PedidoResumo pedido) {
		this.pedidos.add(pedido);
	}
	
	public void rmPedido(String id) {
		List<PedidoResumo> new_pedidos = new ArrayList<PedidoResumo>();
		for (PedidoResumo pedido: this.pedidos) {
			if(!pedido.getCodigoPedido().equals(id)) {
				new_pedidos.add(pedido);
			}
		}
		this.pedidos = new_pedidos;
	}
}
