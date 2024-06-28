package com.api_clientes.api_clientes.services;

import com.api_clientes.api_clientes.entities.Cliente;
import com.api_clientes.api_clientes.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Flux<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Mono<Cliente> getClienteById(String id) {
        return clienteRepository.findById(id);
    }

    public Flux<Cliente> getClientesByTipo(String tipo) {
        return clienteRepository.findByType(tipo);
    }

    public Mono<Cliente> createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Mono<Cliente> updateCliente(String id, Cliente cliente) {
        return clienteRepository.findById(id)
                .flatMap(existingCliente -> {
                    existingCliente.setName(cliente.getName());
                    existingCliente.setType(cliente.getType());
                    // Actualizar otros campos según sea necesario
                    return clienteRepository.save(existingCliente);
                });
    }

    public Mono<Void> deleteCliente(String id) {
        return clienteRepository.deleteById(id);
    }
}