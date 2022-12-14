package com.btgpactual.desafio.dados;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("pedidos")
public class Pedido {

	@Id
	private String id;
	@Field
	private String codigoCliente;
	private List<Item> itens;
	
	public Pedido(){
		super();
		this.id = null;
		this.codigoCliente = null;
		this.setItens(new ArrayList<Item>());
	}

	public Pedido(String id, String codigoCliente) {
		super();
		this.id = id;
		this.codigoCliente = codigoCliente;
		this.setItens(new ArrayList<Item>());
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
	}
	
	public void addItem(Item item){
		this.itens.add(item);
	}
}
