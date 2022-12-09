package com.btgpactual.desafio.dados;

import java.util.List;

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
    
    static public int getQuantidadePedidos(List<PedidoResumo> pedidos) {
    	return pedidos.size();
    }
	
    static public double getValorTotalPedidos(List<PedidoResumo> pedidos) {
    	double valorTotal = 0;
    	for(PedidoResumo pedido : pedidos){
    		valorTotal = valorTotal + pedido.getPrecoTotal();
    	}
    	return valorTotal;
    }
}
