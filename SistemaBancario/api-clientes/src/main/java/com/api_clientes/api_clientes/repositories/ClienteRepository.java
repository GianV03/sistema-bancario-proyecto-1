package com.api_clientes.api_clientes.repositories;

import com.api_clientes.api_clientes.entities.Cliente;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
public interface ClienteRepository extends ReactiveMongoRepository<Cliente, String> {
    Flux<Cliente> findByType(String type);
}