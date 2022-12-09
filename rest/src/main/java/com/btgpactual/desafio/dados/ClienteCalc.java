package com.btgpactual.desafio.dados;

public class ClienteCalc {
	static public int getQuantidadePedidos(Cliente cliente) {
    	return cliente.getPedidos().size();
    }
	
    static public double getValorTotalPedidos(Cliente cliente) {
    	double valorTotal = 0;
    	for(PedidoResumo pedido : cliente.getPedidos()){
    		valorTotal = valorTotal + pedido.getPrecoTotal();
    	}
    	return valorTotal;
    }
}
