package com.btgpactual.desafio.dados;

public class PedidoCalc {
	static public double getPrecoTotal(Pedido pedido) {
		double total=0;
		for (Item item: pedido.getItens()) {
			total=total+(item.getPreco()*item.getQuantidade());
		}
		return total;
	}
}
