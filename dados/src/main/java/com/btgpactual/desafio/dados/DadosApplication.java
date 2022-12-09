package com.btgpactual.desafio.dados;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableMongoRepositories
public class DadosApplication implements CommandLineRunner{
    
    @Autowired
    PedidoRepository pedidoRepo;
    
    @Autowired
    ClienteRepository clienteRepo;
    
    public static void main(String[] args) {
        SpringApplication.run(DadosApplication.class, args);
    }
    
    public String printClienteDetails(Cliente cliente) {
        System.out.println(
        "Cliente id: " + cliente.getId() + 
        ", \nNomeCliente: " + cliente.getNomeCliente() +
        ", \nQuantidadePedidos: " + cliente.getQuantidadePedidos() +
        ", \nValorTotalPedidos: " + cliente.getValorTotalPedidos()
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
    
    public void showAllPedidos() {
    	pedidoRepo.findAll().forEach(pedido -> System.out.println(printPedidoDetails(pedido)));
    }
    
    public void showAllClientes() {
    	clienteRepo.findAll().forEach(cliente -> System.out.println(printClienteDetails(cliente)));
    }

	@Override
	public void run(String... args) throws Exception {
		System.out.println("--------------------------------------------");
		System.out.println("Lista de pedidos:");
		showAllPedidos();
		System.out.println("--------------------------------------------");
		System.out.println("Lista de clientes:");
		showAllClientes();
		System.out.println("--------------------------------------------");
	}
}
