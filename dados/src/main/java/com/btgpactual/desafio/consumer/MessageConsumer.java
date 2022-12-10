package com.btgpactual.desafio.consumer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.btgpactual.desafio.dados.Cliente;
import com.btgpactual.desafio.dados.ClienteRepository;
import com.btgpactual.desafio.dados.Pedido;
import com.btgpactual.desafio.dados.PedidoCalc;
import com.btgpactual.desafio.dados.PedidoRepository;
import com.btgpactual.desafio.dados.PedidoResumo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MessageConsumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

	@Autowired
	private PedidoRepository pedidoRepo = null;

	@Autowired
	private ClienteRepository clienteRepo = null;

	@Autowired
	private ObjectMapper objectMapper = null;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@RabbitListener(queues = BrokerConfigurer.ADD_PEDIDO_MESSAGE_QUEUE)
	public void addPedido(Message message) {
		String descrPedido = new String(message.getBody());
		Pedido pedidoNovo;
		try {
			pedidoNovo = objectMapper.readValue(descrPedido, Pedido.class);
			pedidoRepo.save(pedidoNovo);
			Cliente cliente = clienteRepo.findClienteById(pedidoNovo.getCodigoCliente());
			if(cliente == null) {
				cliente = new Cliente(pedidoNovo.getCodigoCliente(), null);
			}
			cliente.addPedido(new PedidoResumo(pedidoNovo.getId(),PedidoCalc.getPrecoTotal(pedidoNovo)));
			clienteRepo.save(cliente);
		} catch (Exception e) {
			System.err.println("Pedido não pôde ser salvo.");
			e.printStackTrace();
		}
	}
	
	@RabbitListener(queues = BrokerConfigurer.DELETE_PEDIDO_MESSAGE_QUEUE)
	public void deletePedido(Message message) {
		String descrPedido = new String(message.getBody());
		Pedido deletado = pedidoRepo.findPedidoById(descrPedido);
		if(deletado!=null) {
			Cliente cliente = clienteRepo.findClienteById(deletado.getCodigoCliente());
			if(cliente != null) {
				cliente.rmPedido(descrPedido);
				clienteRepo.save(cliente);
			}
		}
		pedidoRepo.deleteById(descrPedido);
	}

	@RabbitListener(queues = BrokerConfigurer.GET_PEDIDO_MESSAGE_QUEUE)
	public void getPedido(Message message) throws JsonProcessingException{
		String descrPedido = new String(message.getBody());
		Message build = null;
		//Se a requisição for para todos os pedidos
		if(descrPedido.equals("all")) {
			List<Pedido> pedidos = pedidoRepo.findAll();
			build = MessageBuilder.withBody(objectMapper.writeValueAsBytes(pedidos)).build();		
		}else {
			//Se a requisição for para um pedido específico
			Pedido pedido = pedidoRepo.findPedidoById(descrPedido);
			if (pedido != null) {
				build = MessageBuilder.withBody(objectMapper.writeValueAsBytes(pedido)).build();
			}
		}
		CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
		rabbitTemplate.sendAndReceive(BrokerConfigurer.BTG_EXCHANGE, BrokerConfigurer.REPLY_MESSAGE_QUEUE, build, correlationData);
	}
	
	@RabbitListener(queues = BrokerConfigurer.ADD_CLIENTE_MESSAGE_QUEUE)
	public void addCliente(Message message) {
		String descrCliente = new String(message.getBody());
		Cliente clienteNovo;
		try {
			clienteNovo = objectMapper.readValue(descrCliente, Cliente.class);
			clienteRepo.save(clienteNovo);
		} catch (Exception e) {
			System.err.println("Cliente não pôde ser salvo.");
			e.printStackTrace();
		}
	}
	
	@RabbitListener(queues = BrokerConfigurer.DELETE_CLIENTE_MESSAGE_QUEUE)
	public void deleteCliente(Message message) {
		String descrCliente = new String(message.getBody());
		clienteRepo.deleteById(descrCliente);
	}
	
	@RabbitListener(queues = BrokerConfigurer.GET_CLIENTE_MESSAGE_QUEUE)
	public void getCliente(Message message) throws JsonProcessingException{
		String descrCliente = new String(message.getBody());
		Message build = null;
		//Se a requisição for para todos os pedidos
		if(descrCliente.equals("all")) {
			List<Cliente> cliente = clienteRepo.findAll();
			build = MessageBuilder.withBody(objectMapper.writeValueAsBytes(cliente)).build();		
		}else {
			//Se a requisição for para um pedido específico
			Cliente cliente = clienteRepo.findClienteById(descrCliente);
			if (cliente != null) {
				build = MessageBuilder.withBody(objectMapper.writeValueAsBytes(cliente)).build();
			}
		}
		CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
		rabbitTemplate.sendAndReceive(BrokerConfigurer.BTG_EXCHANGE, BrokerConfigurer.REPLY_MESSAGE_QUEUE, build, correlationData);
	}
}