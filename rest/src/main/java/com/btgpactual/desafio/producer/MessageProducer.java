package com.btgpactual.desafio.producer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.btgpactual.desafio.dados.Cliente;
import com.btgpactual.desafio.dados.Pedido;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MessageProducer {
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageProducer.class);

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	private ObjectMapper objectMapper = null;
	
	public void addPedido(Pedido pedido) {
		try {
			byte[] pedidobyte = objectMapper.writeValueAsBytes(pedido);
			Message message = MessageBuilder.withBody(pedidobyte).build();
			rabbitTemplate.setReplyTimeout(100000);
			rabbitTemplate.send(BrokerConfigurer.BTG_EXCHANGE, BrokerConfigurer.ADD_PEDIDO_MESSAGE_QUEUE, message);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addCliente(Cliente cliente) {
		try {
			byte[] clientebyte = objectMapper.writeValueAsBytes(cliente);
			Message message = MessageBuilder.withBody(clientebyte).build();
			rabbitTemplate.setReplyTimeout(100000);
			rabbitTemplate.send(BrokerConfigurer.BTG_EXCHANGE, BrokerConfigurer.ADD_CLIENTE_MESSAGE_QUEUE, message);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deletePedido(String id) {
		Message newMessage = MessageBuilder.withBody(id.getBytes()).build();
		rabbitTemplate.setReplyTimeout(100000);
		rabbitTemplate.send(BrokerConfigurer.BTG_EXCHANGE, BrokerConfigurer.DELETE_PEDIDO_MESSAGE_QUEUE, newMessage);
	}
	
	public void deleteCliente(String id) {
		Message newMessage = MessageBuilder.withBody(id.getBytes()).build();
		rabbitTemplate.setReplyTimeout(100000);
		rabbitTemplate.send(BrokerConfigurer.BTG_EXCHANGE, BrokerConfigurer.DELETE_CLIENTE_MESSAGE_QUEUE, newMessage);
	}

	public Pedido getPedido(String id) {
		Pedido response = null;
		try {
			Message newMessage = MessageBuilder.withBody(id.getBytes()).build();
			rabbitTemplate.setReplyTimeout(100000);
			Message result = rabbitTemplate.sendAndReceive(BrokerConfigurer.BTG_EXCHANGE, BrokerConfigurer.GET_PEDIDO_MESSAGE_QUEUE, newMessage);
			if (result != null) {
				// message correlationId
				String correlationId = newMessage.getMessageProperties().getCorrelationId();

				HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
				// Message returned correlation id
				String msgId = (String) headers.get("spring_returned_message_correlation");
				if (msgId.equals(correlationId)) {
					response = objectMapper.readValue(result.getBody(), Pedido.class);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public List<Pedido> getAllPedido() {
		List<Pedido> response = null;
		try {
			Message newMessage = MessageBuilder.withBody("all".getBytes()).build();
			rabbitTemplate.setReplyTimeout(100000);
			Message result = rabbitTemplate.sendAndReceive(BrokerConfigurer.BTG_EXCHANGE, BrokerConfigurer.GET_PEDIDO_MESSAGE_QUEUE, newMessage);
			if (result != null) {
				// message correlationId
				String correlationId = newMessage.getMessageProperties().getCorrelationId();

				HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
				// Message returned correlation id
				String msgId = (String) headers.get("spring_returned_message_correlation");
				if (msgId.equals(correlationId)) {
					response = objectMapper.readValue(result.getBody(), new TypeReference<List<Pedido>>(){});
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public Cliente getCliente(String id) {
		Cliente response = null;
		try {
			Message newMessage = MessageBuilder.withBody(id.getBytes()).build();
			rabbitTemplate.setReplyTimeout(100000);
			Message result = rabbitTemplate.sendAndReceive(BrokerConfigurer.BTG_EXCHANGE, BrokerConfigurer.GET_CLIENTE_MESSAGE_QUEUE, newMessage);
			if (result != null) {
				// message correlationId
				String correlationId = newMessage.getMessageProperties().getCorrelationId();

				HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
				// Message returned correlation id
				String msgId = (String) headers.get("spring_returned_message_correlation");
				if (msgId.equals(correlationId)) {
					response = objectMapper.readValue(result.getBody(), Cliente.class);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public List<Cliente> getAllCliente() {
		List<Cliente> response = null;
		try {
			Message newMessage = MessageBuilder.withBody("all".getBytes()).build();
			rabbitTemplate.setReplyTimeout(100000);
			Message result = rabbitTemplate.sendAndReceive(BrokerConfigurer.BTG_EXCHANGE, BrokerConfigurer.GET_CLIENTE_MESSAGE_QUEUE, newMessage);
			if (result != null) {
				// message correlationId
				String correlationId = newMessage.getMessageProperties().getCorrelationId();

				HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
				// Message returned correlation id
				String msgId = (String) headers.get("spring_returned_message_correlation");
				if (msgId.equals(correlationId)) {
					response = objectMapper.readValue(result.getBody(), new TypeReference<List<Cliente>>(){});
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
}
