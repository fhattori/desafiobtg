package com.btgpactual.desafio.consumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class BrokerConfigurer {
	
    public static final String ADD_PEDIDO_MESSAGE_QUEUE = "add-pedido";
    public static final String GET_PEDIDO_MESSAGE_QUEUE = "get-pedido";    
    public static final String ADD_CLIENTE_MESSAGE_QUEUE = "add-cliente";
    public static final String GET_CLIENTE_MESSAGE_QUEUE = "get-cliente";
    public static final String REPLY_MESSAGE_QUEUE = "reply-queue";
    public static final String BTG_EXCHANGE = "btg-exchange";
    
    //Criando queues para pedidos
    @Bean
    Queue addPedidoQueue() {
        return new Queue(ADD_PEDIDO_MESSAGE_QUEUE);
    }
    
    @Bean
    Queue getPedidoQueue() {
        return new Queue(GET_PEDIDO_MESSAGE_QUEUE);
    }
    
    @Bean
    Queue replyQueue() {
        return new Queue(REPLY_MESSAGE_QUEUE);
    }
    
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(BTG_EXCHANGE);
    }
    
    //Criando queues para clientes
    @Bean
    Queue addClienteQueue() {
        return new Queue(ADD_CLIENTE_MESSAGE_QUEUE);
    }
    
    @Bean
    Queue getClienteQueue() {
        return new Queue(GET_CLIENTE_MESSAGE_QUEUE);
    }
        
    //Binding queues to exchange
    @Bean
    Binding addPedidoBinding() {
        return BindingBuilder.bind(addPedidoQueue())
                .to(topicExchange())
                .with(ADD_PEDIDO_MESSAGE_QUEUE);
    }
    
    @Bean
    Binding getPedidoBinding() {
        return BindingBuilder.bind(getPedidoQueue())
                .to(topicExchange())
                .with(GET_PEDIDO_MESSAGE_QUEUE);
    }
    
    @Bean
    Binding replyGetPedidoBinding() {
        return BindingBuilder.bind(replyQueue())
                .to(topicExchange())
                .with(REPLY_MESSAGE_QUEUE);
    }
    
    @Bean
    Binding addClienteBinding() {
        return BindingBuilder.bind(addClienteQueue())
                .to(topicExchange())
                .with(ADD_CLIENTE_MESSAGE_QUEUE);
    }
    
    @Bean
    Binding getClienteBinding() {
        return BindingBuilder.bind(getClienteQueue())
                .to(topicExchange())
                .with(GET_CLIENTE_MESSAGE_QUEUE);
    }
    
    //Setup ObjectMapper    
    @Bean
    public ObjectMapper scmsObjectMapper() {
        ObjectMapper responseMapper = new ObjectMapper();
        return responseMapper;
    }
}