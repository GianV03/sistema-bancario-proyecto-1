package com.api_clientes.api_clientes.entities;

import com.api_clientes.api_clientes.enums.ClienteType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "clientes")
public class Cliente {
    @Id
    private String id;
    private String name;
    private ClienteType type; // "personal" o "empresarial"



}