package com.btgpactual.desafio;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableRabbit
public class RestApplication{
	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}
}
