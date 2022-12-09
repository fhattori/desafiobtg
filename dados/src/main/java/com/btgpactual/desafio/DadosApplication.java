package com.btgpactual.desafio;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.btgpactual.desafio.dados.ClienteRepository;
import com.btgpactual.desafio.dados.PedidoRepository;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableMongoRepositories
@EnableRabbit
public class DadosApplication {

	@Autowired
    PedidoRepository pedidoRepo;
    
    @Autowired
    ClienteRepository clienteRepo;
	
    public static void main(String[] args) {
        SpringApplication.run(DadosApplication.class, args);
    }
}