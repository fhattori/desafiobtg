package com.btgpactual.desafio.dados;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PedidoRepository extends MongoRepository<Pedido, String> {
    
    @Query("{id:'?0'}")
    Pedido findPedidoById(String id);
    
    public long count();

}
