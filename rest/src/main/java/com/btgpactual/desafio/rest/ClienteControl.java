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
import com.btgpactual.desafio.dados.ClienteValorTotal;
import com.btgpactual.desafio.producer.MessageProducer;


@RestController
class ClienteControl {

	@Autowired
	MessageProducer producer;

	@GetMapping("/clientes")
	List<Cliente> all() {
		return producer.getAllCliente();
	}

	@PostMapping("/clientes")
	void novoCliente(@RequestBody Cliente newCliente) {
		producer.addCliente(newCliente);
	}

	@GetMapping("/clientes/{id}")
	Cliente one(@PathVariable String id) {
		return producer.getCliente(id);
	}
	
	@DeleteMapping("/clientes/{id}")
	void deleteOne(@PathVariable String id) {
		producer.deleteCliente(id);
	}
	
	@GetMapping("/valortotal-clientes")
	List<ClienteValorTotal> allValorTotal() {
		List<Cliente> clientes = producer.getAllCliente();
		List<ClienteValorTotal> valorTotalClientes = new ArrayList<ClienteValorTotal>();
		for(Cliente cliente: clientes){
			valorTotalClientes.add(new ClienteValorTotal(cliente));
		}
		return valorTotalClientes;
	}
	
	@GetMapping("/valortotal-clientes/{id}")
	ClienteValorTotal oneValorTotal(@PathVariable String id) {
		return new ClienteValorTotal(producer.getCliente(id));
	}
	
	class PedidoNotFound extends RuntimeException {
		private static final long serialVersionUID = 1L;

		PedidoNotFound(String id) {
			super("Pedido não encontrado: " + id);
		}
	}
}
