package com.btgpactual.desafio.dados;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.btgpactual.desafio.dados.Cliente;
import com.btgpactual.desafio.dados.ClienteRepository;
import com.btgpactual.desafio.dados.Item;
import com.btgpactual.desafio.dados.Pedido;
import com.btgpactual.desafio.dados.PedidoRepository;
import com.btgpactual.desafio.dados.PedidoResumo;

@RunWith(SpringRunner.class)
@DataMongoTest
public class DadosApplicationTests {

    @Autowired
    PedidoRepository pedidoRepo;
    
    @Autowired
    ClienteRepository clienteRepo;
    
    double ERRO_ACEITAVEL=0.1;

    @Before
    public void setUp() throws Exception {
    	Pedido novopedido = new Pedido("35", "3");
    	novopedido.addItem(new Item("volante",10,25000));
    	novopedido.addItem(new Item("capacitor de fluxo",1,100000));
    	pedidoRepo.save(novopedido);
    	Cliente novocliente = new Cliente("3", "Fábrica de Deloreans");
    	novocliente.addPedido(new PedidoResumo(novopedido.getId(), novopedido.getPrecoTotal()));
    	clienteRepo.save(novocliente);
    }
    
    @After
    public void cleanUp() {
    	pedidoRepo.deleteById("35");
    	clienteRepo.deleteById("3");
    }

    @Test
    public void pedidoDeveSerInserido() {
        assertFalse("O novo pedido deveria ser encontrado",pedidoRepo.findAll().isEmpty());
    }
    
    @Test
    public void clienteDeveSerInserido() {
    	assertFalse("O novo cliente deveria ser encontrado",clienteRepo.findAll().isEmpty());
    }
    
    @Test
    public void conferirInfoPedido() {
    	Pedido pedido35 = pedidoRepo.findPedidoById("35");
        assertEquals("O novo pedido deveria ter 2 itens", 2, pedido35.getItens().size());
    }
    
    @Test
    public void conferirTotalPedido() {
    	Pedido pedido35 = pedidoRepo.findPedidoById("35");
        assertEquals("O total do novo pedido deveria 350000", 350000, pedido35.getPrecoTotal(),this.ERRO_ACEITAVEL);
    }
    
    @Test
    public void conferirInfoCliente() {
    	Cliente cliente3 = clienteRepo.findClienteById("3");
    	assertEquals("O novo cliente deveria ter 1 pedido", 1, cliente3.getQuantidadePedidos());
    }
    
    @Test
    public void conferirTotalCliente() {
    	Cliente cliente3 = clienteRepo.findClienteById("3");
        assertEquals("O total do novo pedido deveria 350000", 350000, cliente3.getValorTotalPedidos(),this.ERRO_ACEITAVEL);
    }
}
