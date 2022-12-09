package com.btgpactual.desafio.producer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class BrokerConfigurer {
	
	public static final String ADD_PEDIDO_MESSAGE_QUEUE = "add-pedido";
    public static final String GET_PEDIDO_MESSAGE_QUEUE = "get-pedido";
    public static final String DELETE_PEDIDO_MESSAGE_QUEUE = "delete-pedido";
    public static final String ADD_CLIENTE_MESSAGE_QUEUE = "add-cliente";
    public static final String GET_CLIENTE_MESSAGE_QUEUE = "get-cliente";
    public static final String DELETE_CLIENTE_MESSAGE_QUEUE = "delete-cliente";
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
    Queue deletePedidoQueue() {
        return new Queue(DELETE_PEDIDO_MESSAGE_QUEUE);
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
    
    @Bean
    Queue deleteClienteQueue() {
        return new Queue(DELETE_CLIENTE_MESSAGE_QUEUE);
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
    Binding deletePedidoBinding() {
        return BindingBuilder.bind(deletePedidoQueue())
                .to(topicExchange())
                .with(DELETE_PEDIDO_MESSAGE_QUEUE);
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
    
    @Bean
    Binding deleteClienteBinding() {
        return BindingBuilder.bind(deleteClienteQueue())
                .to(topicExchange())
                .with(DELETE_CLIENTE_MESSAGE_QUEUE);
    }
    
    //Setup ObjectMapper    
    @Bean
    public ObjectMapper scmsObjectMapper() {
        ObjectMapper responseMapper = new ObjectMapper();
        return responseMapper;
    }
    
    //Configure reply queue
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setReplyAddress(REPLY_MESSAGE_QUEUE);
        template.setReplyTimeout(50000);
        return template;
    }
    
    @Bean
    SimpleMessageListenerContainer replyContainer(ConnectionFactory connectionFactory) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(REPLY_MESSAGE_QUEUE);
        container.setMessageListener(rabbitTemplate(connectionFactory));
        return container;
    }
}