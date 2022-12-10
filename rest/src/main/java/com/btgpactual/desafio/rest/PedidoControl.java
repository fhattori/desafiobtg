package com.btgpactual.desafio.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.btgpactual.desafio.dados.Cliente;
import com.btgpactual.desafio.dados.Pedido;
import com.btgpactual.desafio.dados.PedidoCalc;
import com.btgpactual.desafio.dados.PedidoResumo;
import com.btgpactual.desafio.dados.PedidoValorTotal;
import com.btgpactual.desafio.producer.MessageProducer;

@RestController
class PedidoControl {
	
	@Autowired
	MessageProducer producer;

	@GetMapping("/pedidos")
	List<Pedido> all() {
		return producer.getAllPedido();
	}

	@PostMapping("/pedidos")
	void novoPedido(@RequestBody Pedido newPedido) {
		producer.addPedido(newPedido);
	}

	@GetMapping("/pedidos/{id}")
	Pedido one(@PathVariable String id) {
		return producer.getPedido(id);
	}
	
	@DeleteMapping("/pedidos/{id}")
	void deleteOne(@PathVariable String id) {
		producer.deletePedido(id);
	}
	
	@GetMapping("/valortotal-pedidos")
	List<PedidoValorTotal> allValorTotal() {
		List<Pedido> pedidos = producer.getAllPedido();
		List<PedidoValorTotal> valorTotalPedidos = new ArrayList<PedidoValorTotal>();
		for(Pedido pedido: pedidos){
			valorTotalPedidos.add(new PedidoValorTotal(pedido));
		}
		return valorTotalPedidos;
	}
	
	@GetMapping("/valortotal-pedidos/{id}")
	PedidoValorTotal oneValorTotal(@PathVariable String id) {
		return new PedidoValorTotal(producer.getPedido(id));
	}
	
	class PedidoNotFound extends RuntimeException {
		private static final long serialVersionUID = 1L;

		PedidoNotFound(String id) {
			super("Pedido não encontrado: " + id);
		}
	}
}
