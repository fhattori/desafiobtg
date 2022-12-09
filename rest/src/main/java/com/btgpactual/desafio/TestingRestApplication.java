package com.btgpactual.desafio;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.btgpactual.desafio.dados.Cliente;
import com.btgpactual.desafio.dados.ClienteCalc;
import com.btgpactual.desafio.dados.Item;
import com.btgpactual.desafio.dados.Pedido;
import com.btgpactual.desafio.producer.MessageProducer;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableRabbit
public class TestingRestApplication implements CommandLineRunner{
	private static final Logger LOGGER = LoggerFactory.getLogger(TestingRestApplication.class);
	
	@Autowired
	MessageProducer producer;

	public static void main(String[] args) {
		SpringApplication.run(TestingRestApplication.class, args);
	}
	
	public String printClienteDetails(Cliente cliente) {
        System.out.println(
        "Cliente id: " + cliente.getId() + 
        ", \nNomeCliente: " + cliente.getNomeCliente() +
        ", \nQuantidadePedidos: " + ClienteCalc.getQuantidadePedidos(cliente) +
        ", \nValorTotalPedidos: " + ClienteCalc.getValorTotalPedidos(cliente)
        );
        
        return "";
    }

	public String printPedidoDetails(Pedido pedido) {
        System.out.println(
        "Pedido id: " + pedido.getId() + 
        ", \nCodigoCliente: " + pedido.getCodigoCliente() +
        ", \nItens: "
        );
        List<Item>itens=pedido.getItens();
        itens.forEach(item->System.out.println(printItemDetails(item)));
        
        return "";
    }
    
    public String printItemDetails(Item item) {
        System.out.println(
        "  Produto: " + item.getProduto() + 
        ", \n  Quantidade: " + item.getQuantidade() +
        ", \n  Preco: " + item.getPreco()
        );        
        return "";
    }
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("--------------------------------------------");
		printPedidoDetails(producer.getPedido("1001"));
		System.out.println("--------------------------------------------");
		producer.addPedido(new Pedido("2002", "4"));
		List<Pedido> pedidos = producer.getAllPedido();
		for(Pedido pedido : pedidos) {
			printPedidoDetails(pedido);
		}
		System.out.println("--------------------------------------------");
		printClienteDetails(producer.getCliente("1"));	
		System.out.println("--------------------------------------------");
		producer.addCliente(new Cliente("3","teste"));
		List<Cliente> clientes = producer.getAllCliente();
		for(Cliente cliente : clientes) {
			printClienteDetails(cliente);
		}
		System.out.println("--------------------------------------------");
	}
}
