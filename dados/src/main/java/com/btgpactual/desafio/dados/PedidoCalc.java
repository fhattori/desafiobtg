package com.btgpactual.desafio.dados;

import java.util.List;

public class PedidoCalc {
	static public double getPrecoTotal(Pedido pedido) {
		double total=0;
		for (Item item: pedido.getItens()) {
			total=total+(item.getPreco()*item.getQuantidade());
		}
		return total;
	}
	
	static public double getPrecoTotal(List<Item> itens) {
		double total=0;
		for (Item item: itens) {
			total=total+(item.getPreco()*item.getQuantidade());
		}
		return total;
	}
}
