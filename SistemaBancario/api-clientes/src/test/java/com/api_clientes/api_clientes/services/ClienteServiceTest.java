package com.api_clientes.api_clientes.services;

import com.api_clientes.api_clientes.entities.Cliente;
import com.api_clientes.api_clientes.enums.ClienteType;
import com.api_clientes.api_clientes.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId("1");
        cliente.setName("Juan Perez");
        cliente.setType(ClienteType.PERSONAL);
    }

    @Test
    void testGetAllClientes() {
        Mockito.when(clienteRepository.findAll()).thenReturn(Flux.just(cliente));

        Flux<Cliente> clientes = clienteService.getAllClientes();

        StepVerifier.create(clientes)
                .expectNext(cliente)
                .verifyComplete();
    }

    @Test
    void testGetClienteById() {
        Mockito.when(clienteRepository.findById(anyString())).thenReturn(Mono.just(cliente));

        Mono<Cliente> clienteMono = clienteService.getClienteById("1");

        StepVerifier.create(clienteMono)
                .expectNext(cliente)
                .verifyComplete();
    }

    @Test
    void testGetClientesByTipo() {
        Mockito.when(clienteRepository.findByType(anyString())).thenReturn(Flux.just(cliente));

        Flux<Cliente> clientes = clienteService.getClientesByTipo("PERSONAL");

        StepVerifier.create(clientes)
                .expectNext(cliente)
                .verifyComplete();
    }

    @Test
    void testCreateCliente() {
        Mockito.when(clienteRepository.save(any(Cliente.class))).thenReturn(Mono.just(cliente));

        Mono<Cliente> clienteMono = clienteService.createCliente(cliente);

        StepVerifier.create(clienteMono)
                .expectNext(cliente)
                .verifyComplete();
    }

    @Test
    void testUpdateCliente() {
        Mockito.when(clienteRepository.findById(anyString())).thenReturn(Mono.just(cliente));
        Mockito.when(clienteRepository.save(any(Cliente.class))).thenReturn(Mono.just(cliente));

        Cliente updatedCliente = new Cliente();
        updatedCliente.setName("Juan Perez");
        updatedCliente.setType(ClienteType.EMPRESARIAL);

        Mono<Cliente> clienteMono = clienteService.updateCliente("1", updatedCliente);

        StepVerifier.create(clienteMono)
                .expectNextMatches(c -> c.getName().equals("Juan Perez") && c.getType() == ClienteType.EMPRESARIAL)
                .verifyComplete();
    }

    @Test
    void testDeleteCliente() {
        Mockito.when(clienteRepository.deleteById(anyString())).thenReturn(Mono.empty());

        Mono<Void> voidMono = clienteService.deleteCliente("1");

        StepVerifier.create(voidMono)
                .verifyComplete();
    }
}
