package com.api_clientes.api_clientes.controllers;

import com.api_clientes.api_clientes.entities.Cliente;
import com.api_clientes.api_clientes.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public Flux<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    public Mono<Cliente> getClienteById(@PathVariable String id) {
        return clienteService.getClienteById(id);
    }

    @GetMapping("/tipo/{tipo}")
    public Flux<Cliente> getClientesByTipo(@PathVariable String tipo) {
        return clienteService.getClientesByTipo(tipo);
    }

    @PostMapping
    public Mono<Cliente> createCliente(@Validated @RequestBody Cliente cliente) {
        return clienteService.createCliente(cliente);
    }

    @PutMapping("/{id}")
    public Mono<Cliente> updateCliente(@PathVariable String id, @Validated @RequestBody Cliente cliente) {
        return clienteService.updateCliente(id, cliente);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCliente(@PathVariable String id) {
        return clienteService.deleteCliente(id);
    }
}
