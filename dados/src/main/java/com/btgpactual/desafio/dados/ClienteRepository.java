package com.btgpactual.desafio.dados;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
    
    @Query("{id:'?0'}")
    Cliente findClienteById(String id);
    
    public long count();

}
