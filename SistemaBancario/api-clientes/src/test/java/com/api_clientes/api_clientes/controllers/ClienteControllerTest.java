package com.api_clientes.api_clientes.controllers;

import com.api_clientes.api_clientes.entities.Cliente;
import com.api_clientes.api_clientes.enums.ClienteType;
import com.api_clientes.api_clientes.services.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
@WebFluxTest(controllers = ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId("1");
        cliente.setName("John Doe");
        cliente.setType(ClienteType.PERSONAL);
    }

    @Test
    void testGetAllClientes() {
        Mockito.when(clienteService.getAllClientes()).thenReturn(Flux.just(cliente));

        webTestClient.get().uri("/clientes")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Cliente.class)
                .hasSize(1)
                .contains(cliente);
    }

    @Test
    void testGetClienteById() {
        Mockito.when(clienteService.getClienteById(anyString())).thenReturn(Mono.just(cliente));

        webTestClient.get().uri("/clientes/{id}", "1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Cliente.class)
                .isEqualTo(cliente);
    }

    @Test
    void testGetClientesByTipo() {
        Mockito.when(clienteService.getClientesByTipo(anyString())).thenReturn(Flux.just(cliente));

        webTestClient.get().uri("/clientes/tipo/{tipo}", "PERSONAL")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Cliente.class)
                .hasSize(1)
                .contains(cliente);
    }

    @Test
    void testCreateCliente() {
        Mockito.when(clienteService.createCliente(any(Cliente.class))).thenReturn(Mono.just(cliente));

        webTestClient.post().uri("/clientes")
                .bodyValue(cliente)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Cliente.class)
                .isEqualTo(cliente);
    }

    @Test
    public void testUpdateCliente() {
        Mockito.when(clienteService.updateCliente(anyString(), any(Cliente.class))).thenReturn(Mono.just(cliente));

        webTestClient.put().uri("/clientes/{id}", "1")
                .bodyValue(cliente)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Cliente.class)
                .isEqualTo(cliente);
    }

    @Test
    public void testDeleteCliente() {
        Mockito.when(clienteService.deleteCliente(anyString())).thenReturn(Mono.empty());

        webTestClient.delete().uri("/clientes/{id}", "1")
                .exchange()
                .expectStatus().isOk();
    }
}
