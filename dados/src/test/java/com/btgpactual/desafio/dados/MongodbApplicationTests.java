package com.btgpactual.desafio.dados;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestInstance(Lifecycle.PER_CLASS)
@DataMongoTest
public class MongodbApplicationTests {

    @Autowired
    PedidoRepository pedidoRepo;
    
    @Autowired
    ClienteRepository clienteRepo;
    
    double ERRO_ACEITAVEL=0.1;

    @BeforeAll
    public void setUp() throws Exception {
    	Pedido novopedido = new Pedido("35", "3");
    	novopedido.addItem(new Item("volante",10,25000));
    	novopedido.addItem(new Item("capacitor de fluxo",1,100000));
    	pedidoRepo.save(novopedido);
    	Cliente novocliente = new Cliente("3", "Fábrica de Deloreans");
    	novocliente.addPedido(new PedidoResumo(novopedido.getId(), PedidoCalc.getPrecoTotal(novopedido)));
    	clienteRepo.save(novocliente);
    }
    
    @AfterAll
    public void cleanUp() {
    	pedidoRepo.deleteById("35");
    	clienteRepo.deleteById("3");
    }

    @Test
    public void pedidoDeveSerInserido() {
        Assertions.assertFalse(pedidoRepo.findAll().isEmpty(),"O novo pedido deveria ser encontrado");
    }
    
    @Test
    public void clienteDeveSerInserido() {
    	Assertions.assertFalse(clienteRepo.findAll().isEmpty(),"O novo cliente deveria ser encontrado");
    }
    
    @Test
    public void conferirInfoPedido() {
    	Pedido pedido35 = pedidoRepo.findPedidoById("35");
    	Assertions.assertEquals(pedido35.getItens().size(), 2, "O novo pedido deveria ter 2 itens");
    }
    
    @Test
    public void conferirTotalPedido() {
    	Pedido pedido35 = pedidoRepo.findPedidoById("35");
    	Assertions.assertEquals(this.ERRO_ACEITAVEL, 350000, PedidoCalc.getPrecoTotal(pedido35),"O total do novo pedido deveria 350000");
    }
    
    @Test
    public void conferirInfoCliente() {
    	Cliente cliente3 = clienteRepo.findClienteById("3");
    	Assertions.assertEquals(ClienteCalc.getQuantidadePedidos(cliente3), 1, "O novo cliente deveria ter 1 pedido");
    }
    
    @Test
    public void conferirTotalCliente() {
    	Cliente cliente3 = clienteRepo.findClienteById("3");
    	Assertions.assertEquals(this.ERRO_ACEITAVEL, 350000, ClienteCalc.getValorTotalPedidos(cliente3),"O total do novo pedido deveria 350000");
    }
}
