package com.api_clientes.api_clientes.entities;

import com.api_clientes.api_clientes.enums.ClienteType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ClienteTest {

    @Test
    public void testClienteEntity() {

        // Se crea instancia de cliente
        Cliente cliente = new Cliente();
        cliente.setId("1");
        cliente.setName("Juan Perez");
        cliente.setType(ClienteType.PERSONAL);

        // Verificar los valores
        assertThat(cliente.getId()).isEqualTo("1");
        assertThat(cliente.getName()).isEqualTo("Juan Perez");
        assertThat(cliente.getType()).isEqualTo(ClienteType.PERSONAL);

    }
}
